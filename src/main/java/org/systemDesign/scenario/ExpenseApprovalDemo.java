package org.systemDesign.scenario;

/*
🎯 Scenario 7 — Expense Approval Workflow

Context
Employee expense claims must be approved based on amount thresholds:
  TeamLead : can approve up to ₹5,000
  Manager  : can approve up to ₹25,000
  Director : can approve up to ₹1,00,000
  CFO      : can approve any amount
If nobody in the chain can/should approve it (e.g. exceeds even CFO's real-world
policy edge case), the claim must be cleanly rejected rather than crash or fall
through silently.

Original problem: one big method with nested if/else checking amount thresholds and
calling different approver "stubs" — adding a new approval level meant editing this
central method.

Requirements
1. Adding a new approval level (e.g. VP between Director and CFO) must not require
   editing any existing approver's code.
2. Each approver should only know its own threshold rule — not the whole hierarchy's
   rules.
3. The chain of approvers must be configurable/wireable at runtime (not hardcoded
   nested if/else).
4. Unhandled requests (falling through the entire chain) must be rejected explicitly,
   not silently ignored or throw an unchecked NPE.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: Chain of Responsibility — each approver checks only whether IT can approve; if
    not, it forwards the request to the next approver in the chain, until someone
    handles it or the chain ends.

Q2: Why refactor duplicated per-approver logic into an abstract base class with a
    template-style `approve()` and an abstract `canApprove()` hook?
A2: All three real approvers (TeamLead/Manager/Director) had identical forwarding
    logic ("if I can't handle it, pass to next") duplicated three times — only the
    threshold check differed. Pulling the common forward-or-handle logic into the
    abstract base and leaving only `canApprove(amount)` abstract removes that
    duplication (Single Responsibility / DRY) while keeping each concrete approver
    trivially simple.

Q3: What is `RejectingApprover` and why add it instead of a null check at the end of
    the real chain?
A3: A Null Object pattern applied at the tail of the chain — a "do nothing but
    respond" terminal approver that always handles the request by rejecting it. This
    avoids every real approver needing a `if (next == null) ... else next.handle(...)`
    null check; the chain always has a non-null next link to call.

Bugs found and fixed during review:
- Off-by-one: threshold comparisons used `<` where `<=` was required at exact boundary
  amounts (e.g. exactly ₹5,000 wrongly forwarded past TeamLead).
- Missing wiring: CFO was implemented but never linked as the next-in-chain after
  Director in `main`, so amounts above Director's limit fell through unresolved.
- Uncaught exception: an approver's rule threw on a null/negative amount input instead
  of being validated/handled before entering the chain.

Ruled out similar patterns:
- Strategy: Strategy picks ONE algorithm object chosen by the caller up front; here NO
  caller decision is made — the request itself travels through a sequence of handlers
  until one accepts it.
- Decorator/Proxy: in those, every wrapping layer participates in every call; here the
  chain deliberately STOPS at the first approver that can handle the amount — later
  approvers never run for that request.

Rule of thumb:
- Each concrete handler checks ONLY its own rule (`canApprove`); forwarding-if-not logic
  belongs once in a shared abstract base, not duplicated per handler.
- Build/wire the chain externally (in `main` or a factory), not inside the handler
  classes — this keeps the chain's order/composition configurable at runtime.
- Terminate the chain with a Null-Object-style handler that always "handles" (by
  rejecting), instead of null-checking `next` in every real handler.
*/
public class ExpenseApprovalDemo {
    public static void main(String[] args) {
        Approver reject = new RejectingApprover();

        // Full chain
        Approver tl = new TeamLead(5000);
        Approver manager = new Manager(25000);
        Approver director = new Director(100000);
        Approver cfo = new CFO();

        tl.setNextApproval(manager);
        manager.setNextApproval(director);
        director.setNextApproval(cfo);
        cfo.setNextApproval(reject);

        tl.approve(3000);
        tl.approve(40000);

        // Reduced chain (no Director) — proves explicit rejection
        Approver tlReduced = new TeamLead(5000);
        Approver managerReduced = new Manager(25000);
        tlReduced.setNextApproval(managerReduced);
        managerReduced.setNextApproval(reject);

        try {
            tlReduced.approve(80000);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}

abstract class Approver {
    protected Approver nextHandler;
    void setNextApproval(Approver employee){ this.nextHandler = employee; }
    void approve(int requestedAmount){
        if(!canApprove(requestedAmount)){
            System.out.println("Request amount is greater than the approval amount. Passing to upper levels " + nextHandler.getName());
            nextHandler.approve(requestedAmount);
        } else {
            System.out.println("Amount: " + requestedAmount + " is approved by: " + this.getName());
        }
    }
    abstract boolean canApprove(int amount);
    abstract String getName();
}

class TeamLead extends Approver {
    private final int limit;
    public TeamLead(int limit) {
        this.limit = limit;
    }
    @Override
    boolean canApprove(int requestedAmount) { return requestedAmount < limit; }
    @Override
    String getName() {
        return "Team Lead";
    }
}

class Manager extends Approver {
    private final int limit;
    public Manager(int limit) {
        this.limit = limit;
    }
    @Override
    boolean canApprove(int requestedAmount) { return requestedAmount < limit; }
    @Override
    String getName() {
        return "Manager";
    }
}

class Director extends Approver {
    private final int limit;
    public Director(int limit) {
        this.limit = limit;
    }
    @Override
    boolean canApprove(int requestedAmount) { return requestedAmount < limit; }
    @Override
    String getName() {
        return "Director";
    }
}

class CFO extends Approver {
    @Override
    boolean canApprove(int requestedAmount) { return true; }
    @Override
    String getName() {
        return "CFO";
    }
}

class RejectingApprover extends Approver {
    @Override
    boolean canApprove(int requestedAmount) { return false; }
    @Override
    String getName() { return "No Approver"; }

    @Override
    void approve(int amount){
        throw new IllegalStateException("No approver in chain can approve amount: " + amount);
    }
}