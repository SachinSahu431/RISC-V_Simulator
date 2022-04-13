package SourceCode;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        // NOTE: this is just for testing purpose
        System.out.println("WELCOME TO RISCV SIMULATOR\n");
        try {
            Input p = new Input();
            // With This much of code everything other than labels got executed
//            p.TakeFileInputinMain();
                p.CollectInput();
            p.printAll();
            
// for(int i=0;i<p.label.size();i++)
            // System.out.println(p.label.get(i).getId()+" "+p.label.get(i).getLine());
        } catch (FileNotFoundException e) {
            System.out.println("Please Reconsider the path of the file " + e);
        }

        System.out.println("Thank You!");
    }

}
