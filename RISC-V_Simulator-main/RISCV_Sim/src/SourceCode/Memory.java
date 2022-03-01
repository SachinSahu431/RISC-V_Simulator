package SourceCode;

public class Memory {
    
    int[] Register = new int[32]; //All required 32 registers
    // Memory should be of 4kb => 4000 Bytes
    //In our system 1 word corr. to 4 bytes of memory
    int[] Memory = new int[1000]; //In words i.e. 4 bytes
    
    //Constructor to initialise all of it's stuffs
    Memory(){
        
    }
    
    // Non-Arithmetic Operations
    void loadWord(int regIndex, int memIndex){
        this.Register[regIndex] = Memory[memIndex];
    }
    
    void storeWord(int regIndex, int memIndex){
        Memory[memIndex] = Register[regIndex];
    }
    
    void branchNotEqual(){
        
    }
    
    void jumpAndLink(){
        
    }
}
