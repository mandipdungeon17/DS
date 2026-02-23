package org.systemDesign.creationtionalPattern.prototype;
// Prototype with deep copy
public class PersonClone implements Cloneable{
    private String name;
    private int age;
    private Address address; // Reference type

    PersonClone(String name, int age, Address address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    // Deep clone
    @Override
    public PersonClone clone() {
        try {
            PersonClone cloned = (PersonClone) super.clone();
            cloned.address = this.address.cloneAddress();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age +
                ", address=" + address + "}";
    }
}

// Address class (reference type)
class Address{
    private String city;
    private String country;

    Address(String city, String country){
        this.city = city;
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    // Clone method for deep copy
    public Address cloneAddress(){
        return new Address(this.city, this.country);
    }
}

class DeepCopyMain{
    public static void main(String[] args) {
        Address address = new Address("Mumbai", "India");
        PersonClone original = new PersonClone("Raj", 30, address);

        //Deep Clone
        PersonClone clone = original.clone();
        clone.setName("Priya");
        clone.setAge(28);
        clone.getAddress().setCity("Atlanta");
        clone.getAddress().setCountry("USA");

        System.out.println("Original: " + original); // Mumbai unchanged
        System.out.println("Clone: " + clone);       // USA
    }
}
