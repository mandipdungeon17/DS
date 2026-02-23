package org.systemDesign.structuralPattern.facade;

public class ComputerFacadeDemo {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start(); // Simple one-line operation
    }
}
class ComputerFacade{
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    // Simplified start method
    public void start() {
        System.out.println("\n=== Starting Computer ===\n");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("\n=== Computer Started ===\n");
    }
}
// Subsystem classes
class CPU {
    public void freeze() {
        System.out.println("CPU: Freezing processor");
    }

    public void jump(long position) {
        System.out.println("CPU: Jumping to position " + position);
    }

    public void execute() {
        System.out.println("CPU: Executing instructions");
    }
}
class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Loading data at position " + position);
    }
}
class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Reading " + size + " bytes from sector " + lba);
        return new byte[size];
    }
}
