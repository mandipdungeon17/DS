package org.systemDesign.scenario;

/*
🎯 Scenario 2 — Support Ticket Lifecycle

Context
A helpdesk ticket moves through: Open -> InProgress -> Resolved -> Closed, and a
Resolved ticket can be reopened back to InProgress. Actions: assign(), resolve(),
close(), reopen().

Legal transitions per state:
  Open        : assign() -> InProgress
  InProgress  : resolve() -> Resolved
  Resolved    : close() -> Closed ; reopen() -> InProgress
  Closed      : terminal — nothing legal

Original (bad) code repeated the same 4-branch if/else chain inside every action
method (assign/resolve/close/reopen), each checking `status.equals(...)`.

Requirements
1. Adding a new stage (e.g. OnHold) must not require editing existing stage logic.
2. Illegal actions must be rejected with a clear message, not silently ignored.
3. Ticket itself must contain no if-else/switch on status.
4. main must show a full happy path and at least two rejected illegal actions.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: State — the ticket delegates every action call to its current state object, and
    each state decides (a) whether the action is legal and (b) what the next state is.

Q2: Requirement 1 (add OnHold without touching existing states) and Requirement 3
    (no if/switch in Ticket) map to which SOLID principles?
A2: Req 1 -> Open-Closed Principle (new behavior = new class, not modified classes).
    Req 3 -> Single Responsibility Principle (Ticket holds data; transition rules are a
    separate responsibility owned by the state classes).

Q3: Why does each state object need a reference back to Ticket (i.e. take Ticket as a
    parameter, and call ticket.setState(...))?
A3: Because in State, the *object itself* decides its own next state — not the caller.
    If a state only computed and returned the next state, the caller would be the one
    applying it, which collapses this back into Strategy. The back-reference is exactly
    what moves transition authority INTO the state objects.

Ruled out similar patterns:
- Strategy: in Strategy the caller picks behavior and behaviors don't know about each
  other. Here, each state independently decides its own legal transitions and knows
  which state comes next — that authority living inside the state, not the caller, is
  what makes it State, not Strategy.
- One-sentence contrast to remember: "Strategy: client picks the behaviour, behaviours
  are unaware of each other. State: the object picks its own next behaviour, and states
  know the states they lead to."

Rule of thumb:
- Put a "reject by default" behavior once in an abstract base class (or per-constant enum
  bodies), so concrete states only override the transitions they actually permit —
  avoids duplicating N rejection methods across every state class.
- Terminal states (Closed) should end up as an empty class/enum constant — that emptiness
  is a readable signal that nothing is legal from there.
- One class (or enum constant) per real business state — never split one state's legal
  actions across multiple classes named after actions instead of states.
*/
public class SupportTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        ticket.assign();   // Open -> InProgress
        ticket.resolve();  // InProgress -> Resolved
        ticket.reOpen();   // Resolved -> InProgress
        ticket.resolve();  // InProgress -> Resolved
        ticket.close();    // Resolved -> Closed

        try {
            ticket.assign();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        try {
            ticket.reOpen();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}

//interface TicketState {
//    void assign(Ticket ticket);
//    void resolve(Ticket ticket);
//    void close(Ticket ticket);
//    void reOpen(Ticket ticket);
//}
//
//class Ticket {
//    private TicketState currentState;
//    private final TicketState openTicket;
//    private final TicketState inProgressTicket;
//    private final TicketState resolvedTicket;
//    private final TicketState closedTicket;
//
//    Ticket() {
//        this.openTicket = new OpenTicket();
//        this.inProgressTicket = new InProgressTicket();
//        this.resolvedTicket = new ResolvedTicket();
//        this.closedTicket = new ClosedTicket();
//
//        this.currentState = this.openTicket;
//    }
//
//    public void setState(TicketState currentState) {
//        this.currentState = currentState;
//    }
//
//    public void assign()  { this.currentState.assign(this); }
//    public void resolve() { this.currentState.resolve(this); }
//    public void close()   { this.currentState.close(this); }
//    public void reOpen()  { this.currentState.reOpen(this); }
//
//    public TicketState getInProgressTicket() { return this.inProgressTicket; }
//    public TicketState getResolvedTicket()   { return this.resolvedTicket; }
//    public TicketState getClosedTicket()     { return this.closedTicket; }
//}
//
//class OpenTicket implements TicketState {
//    @Override
//    public void assign(Ticket ticket) {
//        ticket.setState(ticket.getInProgressTicket());
//        System.out.println("Ticket is now In Progress");
//    }
//    @Override
//    public void resolve(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void close(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void reOpen(Ticket ticket) { System.out.println("Invalid State"); }
//}
//
//class InProgressTicket implements TicketState {
//    @Override
//    public void assign(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void resolve(Ticket ticket) {
//        ticket.setState(ticket.getResolvedTicket());
//        System.out.println("Ticket is now Resolved");
//    }
//    @Override
//    public void close(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void reOpen(Ticket ticket) { System.out.println("Invalid State"); }
//}
//
//// Both close() and reOpen() live HERE now — they're both legal
//// from the same business state (Resolved), not two different states.
//class ResolvedTicket implements TicketState {
//    @Override
//    public void assign(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void resolve(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void close(Ticket ticket) {
//        ticket.setState(ticket.getClosedTicket());
//        System.out.println("Ticket is now Closed");
//    }
//    @Override
//    public void reOpen(Ticket ticket) {
//        ticket.setState(ticket.getInProgressTicket());
//        System.out.println("Ticket is now In Progress");
//    }
//}
//
//// True terminal state — every action rejected, nothing transitions out.
//class ClosedTicket implements TicketState {
//    @Override
//    public void assign(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void resolve(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void close(Ticket ticket) { System.out.println("Invalid State"); }
//    @Override
//    public void reOpen(Ticket ticket) { System.out.println("Invalid State"); }
//}

/*
Used Abstract Class for the State Transitions to avoid code duplication and provide a default implementation for invalid transitions.
Each state class extends the abstract class and overrides only the valid transitions, while the invalid transitions are handled by the base class,
throwing an exception with a descriptive message.
 */
//abstract class TicketState{
//    private final String name;
//
//    protected TicketState(String name) {
//        this.name = name;
//    }
//
//    public void assign(Ticket ticket){reject("assign");}
//    public void resolve(Ticket ticket){reject("resolve");}
//    public void close(Ticket ticket){reject("close");}
//    public void reOpen(Ticket ticket){reject("reOpen");}
//
//    private void reject(String action){
//        throw new IllegalStateException("Cannot " + action + " a ticket in " + name + " state");
//    }
//}

//class Ticket{
//    private TicketState currentState;
//    private final TicketState openState = new OpenState();
//    private final TicketState inProgressState = new InProgressState();
//    private final TicketState resolvedState = new ResolvedState();
//    private final TicketState closedState = new ClosedState();
//
//    Ticket(){
//        this.currentState = openState;
//    }
//    void setState(TicketState state){
//        this.currentState = state;
//    }
//
//    public void assign(){ this.currentState.assign(this); }
//    public void resolve(){ this.currentState.resolve(this); }
//    public void close(){ this.currentState.close(this); }
//    public void reOpen(){ this.currentState.reOpen(this); }
//
//    TicketState getInProgressState(){ return this.inProgressState; }
//    TicketState getResolvedState()   { return resolvedState; }
//    TicketState getClosedState()     { return closedState; }
//}
//
//class OpenState extends TicketState{
//    OpenState() {
//        super("OPEN");
//    }
//
//    @Override
//    public void assign(Ticket ticket){
//        ticket.setState(ticket.getInProgressState());
//        System.out.println("Ticket assigned — now In Progress");
//    }
//    // resolve, close, reopen: inherited -> rejected
//}
//class InProgressState extends TicketState{
//    InProgressState() {
//        super("IN PROGRESS");
//    }
//
//    @Override
//    public void resolve(Ticket ticket){
//        ticket.setState(ticket.getResolvedState());
//        System.out.println("Ticket assigned — now Resolved");
//    }
//}
//class ResolvedState extends TicketState{
//    ResolvedState() {
//        super("RESOLVED");
//    }
//
//    @Override
//    public void close(Ticket ticket){
//        ticket.setState(ticket.getClosedState());
//        System.out.println("Ticket assigned — now Closed");
//    }
//
//    @Override
//    public void reOpen(Ticket ticket){
//        ticket.setState(ticket.getInProgressState());
//        System.out.println("Ticket reopened — now In Progress");
//    }
//}
//class ClosedState extends TicketState{
//    ClosedState() {
//        super("CLOSED");
//    }
//}


class Ticket{
    private TicketState currentState = TicketState.OPEN;

    public void assign(){ this.currentState = this.currentState.assign(); }
    public void resolve(){ this.currentState = this.currentState.resolve(); }
    public void close(){ this.currentState = this.currentState.close(); }
    public void reOpen(){ this.currentState = this.currentState.reOpen(); }
}
enum TicketState{
    OPEN {
        @Override
        public TicketState assign(){ return IN_PROGRESS; }
    },
    IN_PROGRESS {
        @Override
        public TicketState resolve(){ return RESOLVED; }
    },
    RESOLVED {
        @Override
        public TicketState close(){ return CLOSED; }
        @Override
        public TicketState reOpen(){ return IN_PROGRESS; }
    },
    CLOSED;

    public TicketState assign() { return reject("assign"); }
    public TicketState resolve() { return reject("resolve"); }
    public TicketState close() { return reject("close"); }
    public TicketState reOpen() { return reject("reOpen"); }

    private TicketState reject(String action){
        throw new IllegalStateException("Cannot " + action + " a ticket in state " + this);
    }
}
