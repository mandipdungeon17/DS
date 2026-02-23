package org.systemDesign.creationtionalPattern.factory.abstractFactory.uifactory;

public interface Button {
    void render();
}

class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Windows style button");
    }
}

class MacButton implements Button {

    @Override
    public void render() {
        System.out.println("Rendering Mac style button");
    }
}