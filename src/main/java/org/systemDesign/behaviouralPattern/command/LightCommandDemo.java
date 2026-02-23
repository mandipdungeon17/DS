package org.systemDesign.behaviouralPattern.command;

import java.util.Objects;

public class LightCommandDemo {
    public static void main(String[] args) {
        // Receiver
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");
        // Commands
        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);
        // Invoker
        RemoteControl remote = new RemoteControl();
        // Turn on living room light
        remote.setCommand(livingRoomOn);
        remote.pressButton();
        // Turn on bedroom light
        remote.setCommand(bedroomOn);
        remote.pressButton();
        // Undo last command
        remote.pressUndo();
        // Turn off living room light`
        remote.setCommand(livingRoomOff);
        remote.pressButton();
    }
}
// Command interface
interface Command {
    void execute();
    void undo();
}
// Receiver (the actual object doing work)
class Light {
    private final String location;
    private boolean isOn;
    public Light(String location){
        this.location = location;
        this.isOn = false;
    }
    public void on(){
        isOn = true;
        System.out.println(location + " light is ON");
    }
    public void off() {
        isOn = false;
        System.out.println(location + " light is OFF");
    }
}
// Concrete Commands
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.on();
    }
    @Override
    public void undo() {
        light.off();
    }
}
// Concrete Commands
class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }
    @Override
    public void undo() {
        light.on();
    }
}
// Invoker (remote control)
class RemoteControl {
    private Command command;
    private Command lastCommand;
    public void setCommand(Command command){
        this.command = command;
    }
    public void pressButton(){
        command.execute();
        lastCommand = command;
    }
    public void pressUndo(){
        if(Objects.nonNull(lastCommand)){
            System.out.println("Undoing last command...");
            lastCommand.undo();
        }
    }
}

