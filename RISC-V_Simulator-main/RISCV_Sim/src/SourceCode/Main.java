package SourceCode;

public class Main extends Register {

    public static void main(String[] args) {
        // NOTE: this is just for testing purpose

        System.out.println("WELCOME TO RISCV SIMULATOR\n");

        // Initialize all the registers
        // auto clean and print the initial registers
        Register reg = new Register();

        // Initialize all the memory
        // Memory mem = new Memory();

        // Test the setMemory() function
        reg.setMemory(80, 431);

        /* To load word from (regIndex) to (memIndex) */
        reg.loadWord(1, 80);

        reg.printAll();

    }

}
