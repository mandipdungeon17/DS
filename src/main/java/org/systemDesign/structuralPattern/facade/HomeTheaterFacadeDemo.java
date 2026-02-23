package org.systemDesign.structuralPattern.facade;

public class HomeTheaterFacadeDemo {
    public static void main(String[] args) {
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem sound = new SoundSystem();
        Lights lights = new Lights();

        // Without Facade - Complex
        /*
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        sound.on();
        sound.setVolume(5);
        sound.setSurroundSound();
        dvd.on();
        dvd.play("Inception");
        */

        HomeTheaterFacade facade = new HomeTheaterFacade(dvd,projector,sound,lights);
        facade.watchMovie("Inception");
        facade.endMovie();
    }
}
//Facade
class HomeTheaterFacade{
    private final DVDPlayer dvd;
    private final Projector projector;
    private final SoundSystem sound;
    private final Lights lights;
    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, SoundSystem sound, Lights lights){
        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
        this.lights = lights;
    }
    // Simplified method
    public void watchMovie(String movie){
        System.out.println("\n=== Get ready to watch a movie ===\n");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        sound.on();
        sound.setVolume(5);
        sound.setSurroundSound();
        dvd.on();
        dvd.play(movie);
        System.out.println("\n=== Enjoy! ===\n");
    }
    public void endMovie(){
        System.out.println("\n=== Shutting down theater ===\n");
        dvd.off();
        sound.off();
        projector.off();
        lights.on();
        System.out.println("\n=== Done! ===\n");
    }
}
// Complex subsystem classes
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player: Turning ON");
    }
    public void play(String movie) {
        System.out.println("DVD Player: Playing '" + movie + "'");
    }
    public void off() {
        System.out.println("DVD Player: Turning OFF");
    }
}
class Projector {
    public void on() {
        System.out.println("Projector: Turning ON");
    }
    public void wideScreenMode() {
        System.out.println("Projector: Setting widescreen mode");
    }
    public void off() {
        System.out.println("Projector: Turning OFF");
    }
}
class SoundSystem {
    public void on() {
        System.out.println("Sound System: Turning ON");
    }
    public void setVolume(int level) {
        System.out.println("Sound System: Setting volume to " + level);
    }
    public void setSurroundSound() {
        System.out.println("Sound System: Setting surround sound");
    }
    public void off() {
        System.out.println("Sound System: Turning OFF");
    }
}
class Lights {
    public void dim(int level) {
        System.out.println("Lights: Dimming to " + level + "%");
    }
    public void on() {
        System.out.println("Lights: Turning ON");
    }
}