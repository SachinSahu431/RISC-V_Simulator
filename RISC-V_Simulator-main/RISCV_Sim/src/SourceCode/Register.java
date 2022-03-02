package SourceCode;

public class Register extends Memory {

    Register() {
        clearAll();
        // clearRegisters();
        // printRegisters();
    }

    // Print everything in register + memory
    public void printAll() {
        System.out.println("");
        this.printRegisters();
        this.printMemory();
        System.out.println("Printing completed!\n");
    }

    // Clear everything in register + memory
    public void clearAll() {
        this.clearRegisters();
        this.clearMemory();
        System.out.println("Cleaning completed!");
    }

    // print all registers
    public void printRegisters() {
        System.out.println("Register Values:");
        for (int i = 0; i < 32; i++) {
            System.out.println("Register[" + i + "]: " + this.Register[i]);
        }
    }

    // print specific register
    public void printRegister(int index) {
        System.out.println("Register[" + index + "]: " + this.Register[index]);
    }

    // clear all register
    public void clearRegisters() {
        for (int i = 0; i < 32; i++) {
            this.Register[i] = 0;
        }
        System.out.println("Registers Cleared");
    }

}
