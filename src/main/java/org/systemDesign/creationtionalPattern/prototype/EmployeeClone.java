package org.systemDesign.creationtionalPattern.prototype;

/*
A class implements the Cloneable interface to indicate to the Object.clone() method that it is legal for that method to
make a field-for-field copy of instances of that class. Invoking Object's clone method on an instance that does not
implement the Cloneable interface results in the exception CloneNotSupportedException being thrown.
By convention, classes that implement this interface should override Object.clone (which is protected) with a public
method. See Object.clone() for details on overriding this method.
 */
// Prototype with shallow copy
public class EmployeeClone implements Cloneable{

    private int id;
    private String name;
    private String department;

    public EmployeeClone(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Getters and setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeClone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    // Clone method
    @Override
    public EmployeeClone clone() {
        try {
            return (EmployeeClone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class CloneMain{
    public static void main(String[] args) {
        // Original object (expensive to create)
        EmployeeClone employeeClone = new EmployeeClone(1, "Mandip", "CSE");
        System.out.println(employeeClone);

        // Clone instead of creating new
        EmployeeClone clone1 = employeeClone.clone();
        clone1.setId(2);
        clone1.setName("Jane Smith");
        clone1.setDepartment("MECH");

        EmployeeClone clone2 = employeeClone.clone();
        clone2.setId(3);
        clone2.setName("Bob Johnson");
        System.out.println("Clone 1: " +clone1);
        System.out.println("Clone 2: " +clone2);

        System.out.println(employeeClone == clone1);
        System.out.println(clone2 == clone1);
        System.out.println(employeeClone); // Unchanged
    }
}
