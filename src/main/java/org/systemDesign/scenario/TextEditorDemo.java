package org.systemDesign.scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
🎯 Scenario 8 — Text Editor Actions, Undo/Redo, and Macros

Context
A simple text editor (`Document`) supports typing text and deleting the last N
characters. It must support Undo and Redo of every action, and also support
recording a sequence of actions (e.g. Type + Bold + Type) as a single reusable
Macro that itself can be undone/redone as one unit.

Requirements
1. Every action (type, delete, bold-toggle, ...) must be independently undoable and
   redoable.
2. Redo must only be possible immediately after an undo; performing any NEW action
   after an undo must clear the redo history (you can't redo into a now-invalid
   future).
3. Adding a brand-new action type (e.g. UppercaseCommand) must not require changing
   the invoker/undo-redo management code.
4. A Macro (sequence of multiple actions) must be undoable/redoable as a single atomic
   unit — undoing a macro undoes all its steps in reverse order, redoing replays them
   in forward order — while individual actions must ALSO remain independently
   undoable when not part of a macro.
5. main must demonstrate: type some text, delete some, undo twice, redo once, run a
   macro of 2+ combined actions, then undo the whole macro in one call.

Q&A (asked and answered during this exercise)
Q1: Name the pattern(s).
A1: Command — every action (Type, Delete, Bold) is encapsulated as an object with
    `execute()`/`undo()`, decoupling the invoker from the receiver (`Document`) and
    from action-specific logic. Composite — `MacroCommand` holds a list of child
    Commands and itself implements the same Command interface, so "one command" and
    "a group of commands" are treated identically by the invoker.

Q2: Why must each concrete Command store enough state to reverse itself, rather than
    the invoker or Document tracking history centrally?
A2: `execute()` performs the action against the Receiver (`Document`); `undo()` must
    know exactly what to reverse (e.g. DeleteCommand must remember the deleted
    characters to re-insert them on undo) — that receiver-specific reversal state only
    makes sense living inside the command object itself, keeping the invoker generic
    (it never inspects what kind of command it's holding).

Q3: How do the undo/redo stacks work, and why does starting a NEW action clear redo?
A3: The invoker (`TextInvoker`) keeps two stacks: an undo-history stack (most recent
    action on top) and a redo stack. `execute(cmd)` runs it and pushes it onto undo-
    history, AND CLEARS the redo stack (since a new branch of history invalidates any
    previously-undone future). `undo()` pops from undo-history, calls `.undo()`, and
    pushes it onto redo. `redo()` pops from redo, calls `.execute()` again, and pushes
    it back onto undo-history.

Q4: How does MacroCommand's undo reverse "in the opposite order" of its execute?
A4: `execute()` iterates its child list forward and calls `execute()` on each in
    order; `undo()` iterates the SAME list in REVERSE and calls `undo()` on each —
    mirroring how you'd manually undo a sequence of steps (undo the last thing done
    first).

Ruled out similar patterns:
- Strategy: Commands are stateful request objects with history (what was done, what's
  needed to reverse it) that get queued/stacked — not stateless interchangeable
  algorithms selected once per call.
- Adapter: there is no incompatible external API being translated here; this is purely
  about decoupling the invoker from receiver-specific action logic.

Rule of thumb:
- Command interface exposes `execute()` and `undo()` (or `execute()` + inverse data).
- Each concrete command stores exactly the state it needs to reverse itself; it holds a
  reference to the Receiver, not the other way around.
- The Invoker manages only the undo/redo stacks generically — it must remain completely
  unaware of what specific commands do.
- MacroCommand implements the same Command interface as its children, executes them
  forward and undoes them in reverse — that symmetry (Composite treating "one" and
  "many" identically) is what separates it from a naive command-array loop.
*/
*/
public class TextEditorDemo {
    public static void main(String[] args) {
        Document doc = new Document();
        TextInvoker invoker = new TextInvoker();

        // 3 operations
        invoker.executeCommand(new TypeCommand(doc, 0, "Hello"));
        invoker.executeCommand(new TypeCommand(doc, 5, "World"));
        invoker.executeCommand(new BoldCommand(doc, 0, 5));
        System.out.println(doc.getText());

        // 2 undos
        invoker.undo();
        invoker.undo();
        System.out.println(doc.getText());   // Hello

        // 1 redo
        invoker.redo();
//        invoker.redo();
        System.out.println(doc.getText());

        // Macro: record 2 operations as ONE unit
        MacroCommand macro = new MacroCommand();
        macro.add(new TypeCommand(doc, doc.getText().length(), "!"));
        macro.add(new BoldCommand(doc, 0, doc.getText().length() + 1));

        invoker.executeCommand(macro);   // macro treated as ONE command
        System.out.println(doc.getText());

        invoker.undo();   // undoes the ENTIRE macro in one call
        System.out.println(doc.getText());   // back to "Hello World"
    }
}

class Document {
    private final StringBuilder text;
    public Document() {
        this.text = new StringBuilder();
    }
    public void insert(int position, String text) {
        this.text.insert(position, text);
    }
    public void delete(int start, int end) {
        this.text.delete(start, end);
    }
    public void bold(int start, int end) {
        this.text.insert(start, "**");
        this.text.insert(end+2, "**");
    }
    public String getText() {
        return this.text.toString();
    }
}

interface TextCommand {
    void execute();
    void undo();
}

class TypeCommand implements TextCommand {
    private final Document document;
    private final int position;
    private final String textToInsert;

    public TypeCommand(Document document, int position, String textToInsert) {
        this.document = document;
        this.position = position;
        this.textToInsert = textToInsert;
    }
    @Override
    public void execute() {
        this.document.insert(this.position, textToInsert);

    }
    @Override
    public void undo() {
        this.document.delete(position, position + textToInsert.length());
    }
}
class DeleteCommand implements TextCommand {
    private final Document document;
    private final int deleteLength;
    private String deletedText;

    public DeleteCommand(Document document, int length) {
        this.document = document;
        this.deleteLength = length;
    }
    @Override
    public void execute() {
        String currentText = this.document.getText();
        int start = currentText.length() - this.deleteLength;
        if (start > 0) {
            this.deletedText = currentText.substring(start);
            this.document.delete(start, currentText.length());
        }
    }
    @Override
    public void undo() {
        String currentText = this.document.getText();
        this.document.insert(currentText.length(), this.deletedText);
    }
}

class BoldCommand implements TextCommand {
    private final Document document;
    private final int start;
    private final int end;
    public BoldCommand(Document document, int start, int end) {
        this.document = document;
        this.start = start;
        this.end = end;
    }
    @Override
    public void execute() {
        document.bold(start, end);
    }
    @Override
    public void undo() {
        document.delete(start, start+2);
        document.delete(end, end+2);
    }
}

class MacroCommand implements TextCommand {
    private final List<TextCommand> commands = new ArrayList<>();

    public void add(TextCommand command){ commands.add(command); }
    @Override
    public void execute() {
        for(TextCommand command : commands){
            command.execute();
        }
    }
    @Override
    public void undo() {
        for (int i = commands.size()-1; i >=0 ; i--) {
            commands.get(i).undo();

        }
    }
}

// INVOKER — never touches Document directly, never knows what a Command does
class TextInvoker {
    private final Stack<TextCommand> history = new Stack<>();
    private final Stack<TextCommand> redoStack = new Stack<>();

    public void executeCommand(TextCommand command) {
        command.execute();
        history.push(command);
        redoStack.clear();
    }
    public void undo() {
        if(!history.isEmpty()){
            TextCommand pop = history.pop();
            pop.undo();
            redoStack.push(pop);
            System.out.println("Undo performed");
        } else {
            System.out.println("Nothing to undo");
        }
    }
    public void redo() {
        if(!redoStack.isEmpty()){
            TextCommand pop = redoStack.pop();
            pop.execute();
            history.push(pop);
            System.out.println("Redo performed");
        } else  {
            System.out.println("Nothing to redo");
        }
    }
}
