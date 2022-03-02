package SourceCode;

public class Memory {

    int[] Register = new int[32]; // All required 32 registers
    // Memory should be of 4kb => 4000 Bytes
    // In our system 1 word corr. to 4 bytes of memory
    int[] Memory = new int[1000]; // In words i.e. 4 bytes

    // Constructor to initialise all of it's stuffs
    Memory() {
        // clearMemory();
        // printMemory();
    }

    // Non-Arithmetic Operations
    void loadWord(int regIndex, int memIndex) {
        this.Register[regIndex] = Memory[memIndex];
    }

    void storeWord(int regIndex, int memIndex) {
        Memory[memIndex] = Register[regIndex];
    }

    void branchNotEqual() {

    }

    void jumpAndLink() {

    }

    // Arithmetic Operations (Ref.:https://shakti.org.in/docs/risc-v-asm-manual.pdf)

    // LI Destination Register (rd), Immediate data
    void li(int regIndex, int immediate) {
        this.Register[regIndex] = immediate;
    }

    // ADD Destination Register (rd), Source Register (rs1), Source Register (rs2)
    void add(int rd, int rs1, int rs2) {
        this.Register[rd] = this.Register[rs1] + this.Register[rs2];
    }

    // ADDI Destination Register (rd), Source Register (rs1), Immediate data
    void addi(int rd, int rs1, int immediate) {
        this.Register[rd] = this.Register[rs1] + immediate;
    }

    // SUB Destination Register (rd), Source Register (rs1), Source Register (rs2)
    void sub(int rd, int rs1, int rs2) {
        this.Register[rd] = this.Register[rs1] - this.Register[rs2];
    }

    // SUBI Destination Register (rd), Source Register (rs1), Immediate data
    void subi(int rd, int rs1, int immediate) {
        this.Register[rd] = this.Register[rs1] - immediate;
    }

    // MUL Destination Register (rd), Source Register (rs1), Source Register (rs2)
    // Only LOW BITS are stored ignoring any overflow
    void mul(int rd, int rs1, int rs2) {
        this.Register[rd] = this.Register[rs1] * this.Register[rs2];
    }

    // MULH Destination Register (rd), Source Register (rs1), Source Register (rs2)
    // Only HIGH BITS are stored ignoring any overflow
    /*
     * Ref.:
     * https://stackoverflow.com/questions/18859207/high-bits-of-long-multiplication
     * -in-java
     */
    void mulh(int rd, int rs1, int rs2) {
        long x_hi = this.Register[rs1] >>> 32;
        long y_hi = this.Register[rs2] >>> 32;
        long x_lo = this.Register[rs1] & 0xFFFFFFFFL;
        long y_lo = this.Register[rs2] & 0xFFFFFFFFL;
        long prod_hi = (x_hi * y_hi) + ((x_hi * y_lo) >>> 32) + ((x_lo * y_hi) >>> 32);
        this.Register[rd] = (int) prod_hi;
    }

    // DIV Destination Register (rd), Source Register (rs1), Source Register (rs2)
    // Quotient is stored in the destination register
    void div(int rd, int rs1, int rs2) {
        this.Register[rd] = this.Register[rs1] / this.Register[rs2];
    }

    // REM Destination Register (rd), Source Register (rs1), Source Register (rs2)
    // Remainder is stored in the destination register
    void rem(int rd, int rs1, int rs2) {
        this.Register[rd] = this.Register[rs1] % this.Register[rs2];
    }

    /**************************************************************************/

    // print specific memory
    public void printMemory(int index) {
        System.out.println("Memory " + index + ": " + this.Memory[index]);
    }

    // set specific memory
    public void setMemory(int index, int data) {
        this.Memory[index] = data;
    }

    // Only non-zero memory will be printed
    public void printMemory() {
        System.out.println("\nMemory Values(non-zero only):");
        for (int i = 0; i < 1000; i++) {
            // Solution : initialise boolean array as flag if memory is written then set
            // flag to true which is
            // false by default and now print the memory
            if (this.Memory[i] != 0) {
                System.out.println("Memory[" + i + "]: " + this.Memory[i]);
            }
        }
    }

    public void clearMemory() {
        for (int i = 0; i < 1000; i++) {
            this.Memory[i] = 0;
        }
        System.out.println("Memory Cleared");
    }

}
