package org.systemDesign.creationtionalPattern.prototype.realWorld.prototypeRegistry;

// Product prototype
abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    abstract void draw();
    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

// Concrete prototypes
class Circle extends Shape{

    public Circle(){
        this.type = "Circle";
    }
    @Override
    void draw() {
        System.out.println("Drawing a Circle");
    }
}
class Rectangle extends Shape{

    public Rectangle(){
        this.type = "Rectangle";
    }
    @Override
    void draw() {
        System.out.println("Drawing a Rectangle");
    }
}
class Square extends Shape{

    public Square(){
        this.type = "Square";
    }
    @Override
    void draw() {
        System.out.println("Drawing a Square");
    }
}
