package org.systemDesign.behaviouralPattern.state;

public class DocumentStateDemo {
    public static void main(String[] args) {
        Document doc = new Document("Java Design Patterns Guide");

        doc.edit();
        doc.edit();

        doc.submitForReview();
        doc.edit(); // Sends back to draft

        doc.submitForReview();
        doc.approve();

        doc.publish(); // Already published
        doc.edit(); // Creates new draft
    }
}
// State interface
interface DocumentState {
    void edit(Document doc);
    void review(Document doc);
    void approve(Document doc);
    void publish(Document doc);
}
// Context
class Document {
    private final String title;
    private DocumentState currentState;
    private final DocumentState draftState;
    private final DocumentState moderationState;
    private final DocumentState publishedState;
    public Document (String title){
        this.title = title;
        // Initialize states
        this.draftState = new DraftState();
        this.moderationState = new ModerationState();
        this.publishedState = new PublishedState();
        // Initial state
        this.currentState = draftState;
        System.out.println("Document '" + title + "' created in DRAFT state\n");
    }
    public void setState(DocumentState state) { this.currentState = state; }

    public void edit() { this.currentState.edit(this); }
    public void submitForReview() { this.currentState.review(this); }
    public void approve() { this.currentState.approve(this); }
    public void publish() { this.currentState.publish(this); }

    public String getTitle() { return this.title; }

    // Getters for states
    public DocumentState getDraftState() { return draftState; }
    public DocumentState getModerationState() { return moderationState; }
    public DocumentState getPublishedState() { return publishedState; }

}
class DraftState implements DocumentState{

    @Override
    public void edit(Document doc) {
        System.out.println("Editing document: " + doc.getTitle());
    }
    @Override
    public void review(Document doc) {
        System.out.println("Submitting '" + doc.getTitle() + "' for review");
        System.out.println("DRAFT -> MODERATION\n");
        doc.setState(doc.getModerationState());
    }
    @Override
    public void approve(Document doc) {
        System.out.println("Cannot approve - not submitted for review");
    }
    @Override
    public void publish(Document doc) {
        System.out.println("Cannot publish - not approved yet");
    }
}
class ModerationState implements DocumentState{
    @Override
    public void edit(Document doc) {
        System.out.println("Sending back to draft for editing");
        System.out.println("MODERATION -> DRAFT\n");
        doc.setState(doc.getDraftState());
    }
    @Override
    public void review(Document doc) {
        System.out.println("Document already under review");
    }
    @Override
    public void approve(Document doc) {
        System.out.println("Document '" + doc.getTitle() + "' approved!");
        System.out.println("MODERATION -> PUBLISHED\n");
        doc.setState(doc.getPublishedState());
    }
    @Override
    public void publish(Document doc) {
        System.out.println("Cannot publish - needs approval first");
    }
}
class PublishedState implements DocumentState{
    @Override
    public void edit(Document doc) {
        System.out.println("Cannot edit published document directly");
        System.out.println("Creating new draft version...");
        System.out.println("PUBLISHED -> DRAFT\n");
        doc.setState(doc.getDraftState());
    }
    @Override
    public void review(Document doc) {
        System.out.println("Document already published");
    }
    @Override
    public void approve(Document doc) {
        System.out.println("Document already approved and published");
    }
    @Override
    public void publish(Document doc) {
        System.out.println("Document already published");
    }
}