package org.systemDesign.creationtionalPattern.factory.abstractFactory.uifactory;

import java.util.Scanner;

public class Application {
    private final Button button;
    private final CheckBox checkBox;

    Application(GUIFactory guiFactory){
        this.button = guiFactory.createButton();
        this.checkBox = guiFactory.createCheckBox();
    }

    public void render(){
        this.button.render();
        this.checkBox.render();
    }
}

class ApplicationMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter OS type (MAC/WINDOWS): ");
        String osType = scanner.nextLine().toUpperCase(); //"MAC"; //Config Value
        GUIFactory guiFactory;
        if(osType.equals("MAC")){
            guiFactory = new MacFactory();
        }
        else {
            guiFactory = new WindowsFactory();
        }
        Application application = new Application(guiFactory);
        application.render();
    }
}
