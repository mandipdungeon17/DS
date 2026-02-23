package org.systemDesign.creationtionalPattern.factory.abstractFactory.uifactory;

public interface CheckBox {
    void render();
}

class WindowsCheckBox implements CheckBox {
    @Override
    public void render() {
        System.out.println("Rendering Windows style CheckBox");
    }
}

class MacCheckBox implements CheckBox {
    @Override
    public void render() {
        System.out.println("Rendering Mac style CheckBox");
    }
}