/*
1. Take input as words and then do the operations on the corresponding data
*/

package SourceCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Input extends Register {

    File code;
    Scanner input;
    int pc = 0;
    Vector<Labels> label = new Vector<Labels>();
    // Note : PC count in label is showing from which pc input is for label

    Input() throws FileNotFoundException {
        code = new File(
                // "D:\\Work\\NOTES\\Computer
                // Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\test.txt");
                // "RISC-V_Simulator-main/TestCase/loop2.txt");
                "D:\\Work\\NOTES\\Computer Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\BubbleSort2.txt");
        // "RISC-V_Simulator-main/TestCase/BubbleSort2.txt");
        this.input = new Scanner(code);
    }

    boolean checkLabel(String z, int tpc) {
        if (z.indexOf(":") != -1) {
            System.out.println("Label found at PC = " + tpc);
            System.out.println(z.substring(0, z.indexOf(":")).trim());
            label.add(new Labels(tpc, z.substring(0, z.indexOf(":")).trim()));
            return true;
        }
        return false;
    }

    // A function used to take input of the program and call for other functions to
    // do the task
    DataSegment data = new DataSegment(null);

    void TakeFileInputinMain() throws FileNotFoundException {

        InputLabels();
        pc = 1;
        System.out.println("chaliye shuru karte h");
        /*
         * PC increases by 1 after each instruction.
         * Continuous labels without any instruction in between them will not increase
         * PC
         */
        // Assume 1st Line is .data and succeeding is the list of assemblers
        // corresponding corresponding it

         input.nextLine();
        System.out.println("shuru hogaya");
        data = new DataSegment(input.next());
        data.workOnId(input.nextLine());
        for (int i = 0; i < data.valueInt.size(); i++)
            Memory[i] = data.valueInt.get(i);
        input.nextLine();
        // System.out.println(data.getWord(0));

        // For a paticular set of instructions it shall run efficiently
        while (input.hasNextLine()) {
            String z = input.next();
            z = z.replaceAll("\\s", "");
            String p;
            p = input.nextLine();
            p = p.replaceAll("\\s", "");
            p = p + ",";
            // System.out.println(z+":"+p);
            String[] inst = p.split("[,]", 0);
            System.out.println(Arrays.asList(inst));
            switch (z) {
                case "add":
                    add(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "sub":
                    sub(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "lw":
                    System.out.println("lw " + inst[0] + " " + inst[1]);
                    // loadWord(regToIndex(inst[0]), addressToIndex(inst[1]));
                    loadWord(regToIndex(inst[0]), addressToIndex(inst[1]));
                    pc++;
                    break;
                case "sw":
                    System.out.println("sw " + inst[0] + " " + inst[1]);
                    storeWord(regToIndex(inst[0]), addressToIndex(inst[1]));
                    pc++;
                    break;
                case "li":
                    li(regToIndex(inst[0]), Integer.parseInt(inst[1]));
                    pc++;
                    break;
                case "addi":
                    addi(regToIndex(inst[0]), regToIndex(inst[1]), Integer.parseInt(inst[2]));
                    pc++;
                    break;
                case "subi":
                    subi(regToIndex(inst[0]), regToIndex(inst[1]), Integer.parseInt(inst[2]));
                    pc++;
                    break;
                case "mul":
                    mul(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "mulh":
                    mulh(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "div":
                    div(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "rem":
                    rem(regToIndex(inst[0]), regToIndex(inst[1]), regToIndex(inst[2]));
                    pc++;
                    break;
                case "bne":
                    input_bne(regToIndex(inst[0]), regToIndex(inst[1]), inst[2]);
                    pc++;
                    break;
                case "beq":
                    input_beq(regToIndex(inst[0]), regToIndex(inst[1]), inst[2]);
                    pc++;
                    break;
                case "jal":
                    input_jal(inst[0]);
                    pc++;
                    // printAll();
                    break;
                case "ble":
                    input_ble(regToIndex(inst[0]), regToIndex(inst[1]), inst[2]);
                    pc++;
                    break;
                case "#":
                    // pc++;
                    break;
                default:
                    break;
            }
        }
    }

    // A method that converts input string which contains a target register to index
    // of that particualr register
    private int regToIndex(String reg) {
        char c0 = reg.charAt(0);
        char c1 = reg.charAt(1);
        char c2 = '*';
        try {
            c2 = reg.charAt(2);
        } catch (Exception e) {
        }
        if ((c0 == 't') || (c0 == 's') || (c0 == 'a')) {
            switch (c0) {
                case 't':
                    return getIndexOfT(c1);
                case 's':
                    return getIndexOfS(c1, c2);
                case 'a':
                    return getIndexOfA(c1);
                default:
                    return -1;
            }
        } else {
            // Ideally will throw an error but currently let's deal it simple
            return -1;
        }
    }

    private int addressToIndex(String reg) {
        // reg will be of format offset(rs1)
        String tempOffset = reg.split("\\(")[0];
        int offset = Integer.parseInt(tempOffset);
        String rs = reg.substring(reg.indexOf("(") + 1, reg.indexOf(")"));
        System.out.println("offset = " + offset);
        System.out.println("rs = " + rs);
        // rs1 is a register which stores the address of the array
        int regIndex = regToIndex(rs);
        // Now We need to fetch value from this register and add it to offset which will
        // be returned
        System.out.println("regIndex = " + regIndex);
        System.out.println("data.getWord = " + data.getWord(offset / 4));
        System.out.println("memory wala  " + Memory[offset / 4]);
        // return (Register[regIndex] + (int) offset);
        // return 0;
        return (offset / 4);
    }

    private void input_bne(int rs1, int rs2, String lb) throws FileNotFoundException {
        // throw new UnsupportedOperationException("Not supported yet.");
        int jumpto = -1;
        for (int i = 0; i < label.size(); i++) {
            if (lb.equals(label.get(i).getId()))
                jumpto = label.get(i).getLine();
        }
        if (jumpto != -1) {
            if(Register[rs1] != Register[rs2])
                input_jal(lb);
            else
                return;
        }
//        else{
//            throw new UnsupportedOperationException("Label not supported.");
//        }
    }

    private void input_beq(int rs1, int rs2, String lb) throws FileNotFoundException {
        // throw new UnsupportedOperationException("Not supported yet.");
        int jumpto = -1;
        for (int i = 0; i < label.size(); i++) {
            if (lb.equals(label.get(i).getId()))
                jumpto = label.get(i).getLine();
        }
        if (jumpto != -1) {
            if(Register[rs1] == Register[rs2])
                input_jal(lb);
            else
                return;
        }
//        else{
//            throw new UnsupportedOperationException("Label not supported.");
//        }
    }

    private void input_jal(String Label_Name) throws FileNotFoundException {
        // throw new UnsupportedOperationException("Not supported yet.");
        int index = -1;
        for (int i = 0; i < label.size(); i++) {
            // System.out.println(label.get(i).getId()+" ? "+Label_Name);
            if (label.get(i).getId().equals(Label_Name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new UnsupportedOperationException("Label " + Label_Name + " Not found. ");
        }
        Scanner temp = new Scanner(code);
        while (temp.hasNextLine()) {
            String p = temp.next();
            if (p.indexOf(":") != -1 && p.contains(label.get(index).getId())) {
                break;
            } else {
                temp.nextLine();
            }
        }
        while (temp.hasNextLine()) {
            String z = temp.next();
            // System.out.println(z);
            switch (z) {
                case "add":
                    add(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "sub":
                    sub(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "lw":
                    loadWord(regToIndex(temp.next()), addressToIndex(temp.next()));
                    break;
                case "sw":
                    storeWord(regToIndex(temp.next()), addressToIndex(temp.next()));
                    break;
                case "li":
                    li(regToIndex(temp.next()), temp.nextInt());
                    break;
                case "addi":
                    addi(regToIndex(temp.next()), regToIndex(temp.next()), temp.nextInt());
                    break;
                case "subi":
                    subi(regToIndex(temp.next()), regToIndex(temp.next()), temp.nextInt());
                    break;
                case "mul":
                    mul(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "mulh":
                    mulh(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "div":
                    div(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "rem":
                    rem(regToIndex(temp.next()), regToIndex(temp.next()), regToIndex(temp.next()));
                    break;
                case "bne":
                    input_bne(regToIndex(temp.next()), regToIndex(temp.next()), temp.next()); // Solve it?
                    break;
                case "beq":
                    input_beq(regToIndex(temp.next()), regToIndex(temp.next()), temp.next()); // Solve it?
                    break;
                case "jal":
                    input_jal(temp.next()); // ?
                    // printAll(); Check
                    break;
                case "#":
                    temp.nextLine();
                    // pc++;
                    break;
                default:
                    break;
            }
        }
        temp.close();
    }

    private int getIndexOfT(char num) {
        switch (num) {
            case '0':
                return 5;
            case '1':
                return 6;
            case '2':
                return 7;
            case '3':
                return 28;
            case '4':
                return 29;
            case '5':
                return 30;
            case '6':
                return 31;
            default:
                return -1;
        }
    }

    private int getIndexOfS(char reg, char reg2) {
        switch (reg) {
            case '0':
                return 8;
            case '1': // Register mapping to specific one
                if (reg2 == '0')
                    return 26;
                else if (reg2 == '1')
                    return 27;
                else
                    return 9;
            case '2':
                return 18;
            case '3':
                return 19;
            case '4':
                return 20;
            case '5':
                return 21;
            case '6':
                return 22;
            case '7':
                return 23;
            case '8':
                return 24;
            case '9':
                return 25;
            default:
                return -1;
        }
    }

    private int getIndexOfA(char num) {
        switch (num) {
            case '0':
                return 10;
            case '1':
                return 11;
            case '2':
                return 12;
            case '3':
                return 13;
            case '4':
                return 14;
            case '5':
                return 15;
            case '6':
                return 16;
            case '7':
                return 17;
            default:
                return -1;
        }
    }

    private void InputLabels() throws FileNotFoundException {
        int temp_PC = 1;
        Scanner temp = new Scanner(code);
        while (temp.hasNextLine()) {
            String z = temp.nextLine();
            if (checkLabel(z, temp_PC)) {
                continue;
            } else {
                if (z.length() > 0) {
                    if (z.charAt(0) != '#') {
                        temp_PC++;
                    }
                }
            }
        }
        temp.close();
    }

    private void input_ble(int rs1, int rs2, String lb) throws FileNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); 
        int jumpto = -1;
        for (int i = 0; i < label.size(); i++) {
            if (lb.equals(label.get(i).getId()))
                jumpto = label.get(i).getLine();
        }
        if (jumpto != -1) {
            if(Register[rs1] <= Register[rs2])
                input_jal(lb);
            else
                return;
        }
//        else{
//            throw new UnsupportedOperationException("Label not supported.");
//        }
    }
}