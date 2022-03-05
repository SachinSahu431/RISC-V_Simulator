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
        String format = "%-2s %2s%8d%15s%20s\n";
        System.out.println("\n\nRegister Name \t Decimal \t Hexadecimal \t Binary");

        for (int i = 0; i < 32; i++) {
            System.out.printf(format,
                    "Register[" + i + "]: ", RegisterName[i],
                    this.Register[i],
                    Integer.toHexString(this.Register[i]),
                    Integer.toBinaryString(this.Register[i]));
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

    // register and symbolic names
    public static final String[] RegisterName = {
            "zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0", "a1", "a2", "a3", "a4", "a5", "a6",
            "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6", "pc"
    };
}
