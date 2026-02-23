package org.systemDesign.behaviouralPattern.command;

import java.util.Stack;

public class TextEditorCommandDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorInvoker invoker = new EditorInvoker();
        // Write "Hello "
        System.out.println("Writing 'Hello '");
        invoker.executeCommand(new WriteCommand(editor, "Hello "));
        editor.display();
        // Write "World"
        System.out.println("\nWriting 'World'");
        invoker.executeCommand(new WriteCommand(editor, "World"));
        editor.display();
        // Write "!"
        System.out.println("\nWriting '!'");
        invoker.executeCommand(new WriteCommand(editor, "!"));
        editor.display();
        // Undo last write
        System.out.println("\nUndo:");
        invoker.undo();
        editor.display();
        // Undo again
        System.out.println("\nUndo:");
        invoker.undo();
        editor.display();
        // Redo
        System.out.println("\nRedo:");
        invoker.redo();
        editor.display();
        // New command (clears redo stack)
        System.out.println("\nWriting ' Java'");
        invoker.executeCommand(new WriteCommand(editor, " Java"));
        editor.display();
    }
}
// Command Interface
interface TextCommand {
    void execute();
    void undo();
}
// Receiver
class TextEditor {
    private final StringBuilder text;
    public TextEditor(){
        this.text = new StringBuilder();
    }
    public void write(String newText){
        text.append(newText);
    }
    public void delete(int length){
        int start = text.length() - length;
        if(start >= 0){
            text.delete(start, text.length());
        }
    }
    public String getText(){
        return text.toString();
    }
    public void display() {
        System.out.println("Text: " + text);
    }
}
// Concrete Command: Write
class WriteCommand implements TextCommand {
    private final TextEditor editor;
    private final String textToWrite;
    public WriteCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.textToWrite = text;
    }
    public void execute() {
        editor.write(textToWrite);
    }
    public void undo() {
        editor.delete(textToWrite.length());
    }
}
// Concrete Command: Delete
class DeleteCommand implements TextCommand {
    private final TextEditor editor;
    private final int deleteLength;
    private String deletedText;
    public DeleteCommand(TextEditor editor, int length) {
        this.editor = editor;
        this.deleteLength = length;
    }
    public void execute() {
        String currentText = editor.getText();
        int start = currentText.length() - deleteLength;
        if (start >= 0) {
            deletedText = currentText.substring(start);
            editor.delete(deleteLength);
        }
    }
    public void undo() {
        if (deletedText != null) {
            editor.write(deletedText);
        }
    }
}
// Invoker with history
class EditorInvoker {
    private final Stack<TextCommand> history;
    private final Stack<TextCommand> redoStack;
    public EditorInvoker(){
        this.history = new Stack<>();
        this.redoStack = new Stack<>();
    }
    public void executeCommand(TextCommand command){
        command.execute();
        history.push(command);
        redoStack.clear();
    }
    public void undo(){
        if(!history.isEmpty()){
            TextCommand command = history.pop();
            command.undo();
            redoStack.push(command);
            System.out.println("Undo performed");
        } else {
            System.out.println("Nothing to undo");
        }
    }
    public void redo(){
        if(!redoStack.isEmpty()){
            TextCommand command = redoStack.pop();
            command.execute();
            history.push(command);
            System.out.println("Redo performed");
        } else {
            System.out.println("Nothing to redo");
        }
    }
}
