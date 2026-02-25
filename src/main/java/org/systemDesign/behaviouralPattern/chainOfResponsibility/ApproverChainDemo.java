package org.systemDesign.behaviouralPattern.chainOfResponsibility;

public class ApproverChainDemo {
    public static void main(String[] args) {
        // Build approval chain
        Approver teamLead = new TeamLead("John (Team Lead)");
        Approver manager = new Manager("Sarah (Manager)");
        Approver director = new Director("Mike (Director)");
        Approver ceo = new CEO("Lisa (CEO)");

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        // Test different amounts
        System.out.println("=== Request 1: $5,000 ===");
        teamLead.processRequest(new ExpenseRequest(5000, "Office Supplies"));

        System.out.println("\n=== Request 2: $25,000 ===");
        teamLead.processRequest(new ExpenseRequest(25000, "Team Building"));

        System.out.println("\n=== Request 3: $150,000 ===");
        teamLead.processRequest(new ExpenseRequest(150000, "New Server"));

        System.out.println("\n=== Request 4: $500,000 ===");
        teamLead.processRequest(new ExpenseRequest(500000, "Office Renovation"));

        System.out.println("\n=== Request 5: $2,000,000 (Exceeds all limits) ===");
        teamLead.processRequest(new ExpenseRequest(2000000, "New Building"));
    }
}

// Expense Request
record ExpenseRequest(double amount, String purpose) {
}
// Handler
abstract class Approver {
    protected Approver nextApprover;
    protected String name;
    protected double approvalLimit;
    public Approver (String name, double approvalLimit){
        this.name = name;
        this.approvalLimit = approvalLimit;
    }
    public void setNextApprover(Approver approver){
        this.nextApprover = approver;
    }
    public void processRequest(ExpenseRequest request) {
        if(request.amount() <= this.approvalLimit){
            approve(request);
        }
        else {
            if(this.nextApprover != null){
                System.out.println(name + ": Amount exceeds my limit, forwarding to " +
                        nextApprover.name);
                this.nextApprover.processRequest(request);
            }
            else {
                System.out.println(name + ": Request exceeds all approval limits. REJECTED!");
            }
        }
    }
    protected void approve(ExpenseRequest request){
        System.out.println(name + " APPROVED: $" + request.amount() +
                " for " + request.purpose());
    }
}
// Concrete Handlers
class TeamLead extends Approver {
    public TeamLead(String name) {
        super(name, 10000);
    }
}
class Manager extends Approver {
    public Manager(String name) {
        super(name, 50000);
    }
}
class Director extends Approver {
    public Director(String name) {
        super(name, 200000);
    }
}
class CEO extends Approver {
    public CEO(String name) {
        super(name, 1000000);
    }
}

