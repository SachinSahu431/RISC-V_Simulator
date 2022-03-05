package SourceCode;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        // NOTE: this is just for testing purpose
        System.out.println("WELCOME TO RISCV SIMULATOR\n");

        try {
            Input p = new Input();
            p.TakeFileInputinMain();
            p.printAll();
        } catch (FileNotFoundException e) {
            System.out.println("Please Reconsider the path of the file");
        }
    }

}
