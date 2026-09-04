package org.systemDesign.scenario;

import java.util.Arrays;
import java.util.List;

/*
🎯 Scenario 3 — Report Export

Context
A reporting service exports data as CSV, JSON, PDF. Every export runs the same six
steps in this exact order: validate permission, fetch records, write header, write
rows, write footer, log audit. Steps 1/2/6 are identical for every format; steps 3/4/5
differ per format. CSV has no footer. PDF alone needs an extra "compress" step after
the footer.

Original problem: three exporter classes each copy-pasted all six steps; the audit-log
step was fixed in two of them and silently forgotten in the third for a month.

Requirements
1. The order of the six steps must be enforced — a new format cannot skip validation
   or audit logging, or reorder steps.
2. Adding XML export must not require editing existing export code.
3. The footer step is optional (CSV has none) without writing an empty method just to
   satisfy an interface.
4. PDF alone needs an extra compress step after the footer, without any
   `if (format == PDF)` anywhere, and without other formats being affected.
5. main must run all three exports.

Q&A (asked and answered during this exercise)
Q1: Name the pattern, and why not Facade (the closest lookalike)?
A1: Template Method. Facade wraps many unrelated classes behind one new simplified
    entry point and doesn't care about sequence. Template Method wraps the steps of
    ONE algorithm and its whole point is enforcing sequence via a `final` method.

Q2: Which language feature enforces "the order cannot be overridden"?
A2: The `final` keyword on the template method itself (`exportFile()`), so no subclass
    can override the skeleton or reorder/skip steps.

Q3: Requirement 4 (PDF's extra compress step) has a specific name distinct from
    requirement 3 (optional footer) — what is it, and how do the two differ?
A3: Both are solved with the same tool: a HOOK METHOD — a concrete method in the base
    class with a default (often empty) body, inserted at a specific point in the fixed
    sequence, which subclasses may optionally override.
    - Req 3 (optional existing step): `writeFooter()` is a hook with an empty default;
      CSV simply never overrides it.
    - Req 4 (new step, one subclass only): needs its OWN hook at a new point in the
      template (`postProcess()`), which only PDF overrides — it must not be smuggled
      inside `writeFooter()`, or the skeleton would "lie" about what actually runs.

Ruled out similar patterns:
- Facade: no fixed algorithm/sequence enforcement is Facade's concern; it only
  simplifies access to unrelated subsystems.
- Strategy: steps aren't swapped as one interchangeable whole algorithm object; only
  individual steps vary while the sequence itself stays fixed and non-negotiable.

Rule of thumb:
- Make the orchestrating method `final` in the abstract base class — this is the
  actual enforcement mechanism for "order cannot change."
- Steps common to all subclasses live directly in the base class (private, not
  overridable). Steps that must vary are `abstract`. Steps that are optional or
  format-specific are hooks — concrete methods with an empty/default body.
- Never bundle two unrelated concerns into one hook (e.g. don't hide a new step inside
  an existing hook's body) — give each distinct concern its own hook so the skeleton
  method remains an honest, complete list of everything that happens.
*/
public class ReportExportDemo {
    public static void main(String[] args) {
        ReportExporter file = new CSV();
        try {
            file.exportFile("ADMIN", "report.csv");
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("-----------------------------");
        file = new PDF();
        try {
            file.exportFile("ADMIN", "report.pdf");
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("-----------------------------");
        file = new JSON();
        try {
            file.exportFile("ADMIN", "report.json");
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

abstract class ReportExporter {
    List<String> users = Arrays.asList("ADMIN", "USER");
    public final void exportFile(String user, String file) throws IllegalAccessException {
        validateUserPermission(user);
        getFileFromDB(file);
        writeHeader(file);
        writeRows(file);
        writeFooter(file);
        postProcess(file);
        auditLog();
    }

    private void validateUserPermission(String user) throws IllegalAccessException {
        if(users.contains(user)){
            System.out.println("User "+user+" is authorized");
        } else {
            throw new IllegalAccessException("User "+user+" is not authorized");
        }
    }

    private void getFileFromDB(String file){
        System.out.println("Fetching file from DB: "+file);
    }

    protected abstract void writeHeader(String file);
    protected abstract void writeRows(String file);
    protected void writeFooter(String file) {}
    protected void postProcess(String file) {}

    private void auditLog(){
        System.out.println("Auditing log");
    }
}
 class CSV extends ReportExporter {

     @Override
     protected void writeHeader(String file) { System.out.println("Writing header to CSV: "+file); }

     @Override
     protected void writeRows(String file) { System.out.println("Writing rows to CSV: "+file); }
 }

class JSON extends ReportExporter {

    @Override
    protected void writeHeader(String file) { System.out.println("Writing header to JSON: "+file); }

    @Override
    protected void writeRows(String file) { System.out.println("Writing rows to JSON: "+file); }

    @Override
    protected void writeFooter(String file) { System.out.println("Writing footer to JSON: "+file); }
}
class PDF extends ReportExporter {
    @Override
    protected void writeHeader(String file) { System.out.println("Writing header to PDF: "+file); }

    @Override
    protected void writeRows(String file) { System.out.println("Writing rows to PDF: "+file); }

    @Override
    protected void writeFooter(String file) { System.out.println("Writing footer to PDF: "+file); }

    @Override
    protected void postProcess(String file) { System.out.println("Compressing PDF: "+file); }
}