package org.systemDesign.creationtionalPattern.builder;
/*
 Static vs Non-Static Nested Class Access

 1. Both static and non-static inner classes CAN access private members

A non-static inner class can also access the private constructor, methods, and fields. The difference is about how you instantiate them:

```java
// Non-static inner class would require:
Employee emp = new Employee();  // Need outer instance first (but can't - constructor is private!)
Employee.EmployeeBuilder builder = emp.new EmployeeBuilder();  // Then create inner
```

The problem: You need an `Employee` instance to create the builder, but you need the builder to create an `Employee` — a chicken-and-egg problem.

 2. Private Methods and Fields ARE Accessible

The builder can access all private members of `Employee`. For example, this would work:

```java
public Employee build(){
    Employee emp = new Employee(this);
    emp.emp();  // Can call private method!
    System.out.println(emp.firstName);  // Can access private field!
    return emp;
}
```

 3. Why It Seems Like Only Constructor Is Used

In the Builder pattern, you only need to call the constructor because:
- Fields are set via the constructor parameters
- The builder holds the data, then passes itself to construct the object
- No need to access private methods/fields directly

 Summary

| Aspect                            | Static Inner Class | Non-Static Inner Class |
|--------                           |------------------- |------------------------|
| Access private members            | ✅ Yes             | ✅ Yes |
| Needs outer instance to create    | ❌ No              | ✅ Yes |
| Suitable for Builder pattern      | ✅ Yes             | ❌ No (circular dependency) |

The `static` keyword isn't about access permissions — it's about whether the inner class needs an enclosing instance to exist.
 */
public class Employee {
    // Required parameters
    private final String firstName;
    private final String lastName;

    // Optional parameters
    private final int age;
    private final String phone;
    private final String address;
    private final String department;

    private Employee (EmployeeBuilder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
        this.department = builder.department;
    }

    private void emp (){
        System.out.println("efdad");
    }

    public static class EmployeeBuilder {
        // Required parameters
        private final String firstName;
        private final String lastName;

        // Optional parameters
        private int age = 0;
        private String phone = "";
        private String address = "";
        private String department = "";

        public EmployeeBuilder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public EmployeeBuilder age(int age){
            this.age = age;
            return this;
        }
        public EmployeeBuilder phone(String phone){
            this.phone = phone;
            return this;
        }
        public EmployeeBuilder address(String address){
            this.address = address;
            return this;
        }
        public EmployeeBuilder department(String department){
            this.department = department;
            return this;
        }

        //Build Method
        public Employee build(){
//            Employee employee = new Employee(this);
//            employee.emp();
//            employee.firstName = "Mandip";
            return new Employee(this);
        }
    }

    @Override
    public String toString(){
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        // Getting Builder Object
//        Employee.EmployeeBuilder employee = new Employee.EmployeeBuilder("Mandip", "Archana");
//        System.out.println(employee);
        // Building object with only required parameters
        Employee employee = new Employee.EmployeeBuilder("Mandip", "Archana").build();
        System.out.println(employee);

        // Building object with all parameters
        Employee employee1 = new Employee.EmployeeBuilder("Mandip", "Pandit")
                .age(30).phone("7059").address("Aerosky").department("IGS").build();
        System.out.println(employee1);
    }
}


