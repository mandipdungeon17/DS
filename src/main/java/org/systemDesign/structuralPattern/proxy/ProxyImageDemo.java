package org.systemDesign.structuralPattern.proxy;

import java.util.Objects;
/*
    Proxy Pattern Example: Virtual Proxy for Image Loading
    In this example, we implement a virtual proxy for an image loading scenario.
    The ProxyImage class controls access to the RealImage class, which simulates
    an expensive operation of loading an image from disk. The proxy defers the
    loading of the image until it is actually needed (lazy initialization).

    Proxy and Adapter pattern are similar in structure but serve different purposes.
    Proxy controls access to an object, while Adapter changes the interface of an object.
    Proxy: Same interface as real object whereas Adapter: Different interface
 */
public class ProxyImageDemo {
    public static void main(String[] args) {
        System.out.println("=== Creating proxy images ===");
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("Images created (not loaded yet)\n");
        System.out.println("=== Displaying image1 (first time - will load) ===");
        image1.display();

        System.out.println("\n=== Displaying image1 (second time - already loaded) ===");
        image1.display();

        System.out.println("\n=== Displaying image2 (first time - will load) ===");
        image2.display();
    }
}
// Subject interface
interface Image{
    void display();
}
// Real Subject (Heavy object)
class RealImage implements Image{
    private final String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }
    private void loadFromDisk(){
        System.out.println("Loading image from disk: " + this.fileName);
        // Simulate expensive operation
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Image loaded: " + fileName);
    }
    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
// Virtual Proxy (Controls access to RealImage)
class ProxyImage implements  Image{
    private RealImage realImage;
    private final String fileName;
    public ProxyImage(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void display() {
        //Lazy Initialization
        if(Objects.isNull(realImage)){
            this.realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
