package org.systemDesign.creationtionalPattern.prototype.realWorld.prototypeRegistry;

import java.util.HashMap;
import java.util.Map;

// Prototype Registry (Cache)
public class ShapeCache {
    private static final Map<String, Shape> shapeMap = new HashMap<>();

    // Load prototypes into cache
    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put("1", circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put("2", square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put("3", rectangle);
    }

    // Get clone from cache
    public static Shape getShape(String shapeId){
        Shape cachedShape = shapeMap.get(shapeId);
        return cachedShape.clone();
    }
}

class PrototypeDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        // Get clones from cache instead of creating new
        Shape clonedCircle = ShapeCache.getShape("1");
        System.out.println("Shape: " + clonedCircle.getType());
        clonedCircle.draw();

        Shape clonedRectangle = ShapeCache.getShape("2");
        System.out.println("Shape: " + clonedRectangle.getType());
        clonedRectangle.draw();

        Shape clonedSquare = ShapeCache.getShape("3");
        System.out.println("Shape: " + clonedSquare.getType());
        clonedSquare.draw();
    }
}


