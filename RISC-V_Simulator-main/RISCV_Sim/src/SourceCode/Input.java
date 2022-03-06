/*
1. Take input as words and then do the operations on the corresponding data
*/

package SourceCode;

import java.io.File;
import java.io.FileNotFoundException;
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
                "D:\\Work\\NOTES\\Computer Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\test.txt");
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
    void TakeFileInputinMain() throws FileNotFoundException {

        InputLabels();
        pc = 1;
        /*
         * PC increases by 1 after each instruction.
         * Continuous labels without any instruction in between them will not increase
         * PC
         */
        // For a paticular set of instructions it shall run efficiently
        while (input.hasNextLine()) {
            String z = input.next();
            // System.out.println(z);
            switch (z) {
                case "add":
                    add(regToIndex(input.next()),regToIndex(input.next()), regToIndex(input.next()));
                    pc++;
                    break;
                case "sub":
                    sub(regToIndex(input.next()),regToIndex(input.next()) ,regToIndex(input.next()) );
                    pc++;
                    break;
                case "lw":
                    loadWord(regToIndex(input.next()),addressToIndex(input.next()));
                    pc++;
                    break;
                case "li":
                    li(regToIndex(input.next()), input.nextInt());
                    pc++;
                    break;
                case "addi":
                    addi(regToIndex(input.next()), regToIndex(input.next()), input.nextInt());
                    pc++;
                    break;
                case "subi":
                    subi(regToIndex(input.next()), regToIndex(input.next()), input.nextInt());
                    pc++;
                    break;
                case "mul":
                    mul(regToIndex(input.next()),regToIndex(input.next()),regToIndex(input.next()));
                    pc++;
                    break;
                case "mulh":
                    mulh(regToIndex(input.next()),regToIndex(input.next()),regToIndex(input.next()));
                    pc++;
                    break;
                case "div":
                    div(regToIndex(input.next()),regToIndex(input.next()),regToIndex(input.next()));
                    pc++;
                    break;
                case "rem":
                    rem(regToIndex(input.next()),regToIndex(input.next()),regToIndex(input.next()));
                    pc++;
                    break;
                case "bne":
                    input_bne(regToIndex(input.next()),regToIndex(input.next()),input.next()); //Solve it?
                    pc++;
                    break;
                case "jal":
                    input_jal(input.next()); //?
                    pc++;
//                    printAll(); Check
                    break;
                case "#":
                    input.nextLine();
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
        char c2 = reg.charAt(2);
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
        String offset = "";
        // reg will be of format offset(rs1) => I need value inside rs1 and offset
        // Firstly lets catch up on offset
        int i = 0, regIndex;
        while (reg.charAt(i) != '(') {
            offset += reg.charAt(i);
            i++;
        }
        i++;
        // Dealing with rs1 now
        regIndex = regToIndex(reg.substring(i, reg.lastIndexOf(reg)));
        // Now We need to fetch value from this register and add it to offset which will
        // be returned
        // return (Register[regIndex]+(int)offset);
        return 0;
    }

    private void input_bne(int rs1, int rs2, String lb) {
         throw new UnsupportedOperationException("Not supported yet.");
        /*int jumpto = -1;
        for (int i = 0; i < label.size(); i++) {
            if (lb == label.get(i).getId())
                jumpto = label.get(i).getLine();
        }*/
    }

    private void input_jal(String Label_Name) throws FileNotFoundException {
         throw new UnsupportedOperationException("Not supported yet.");
       /* int index = -1;
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
        int temp_pc = -index;
        while (temp.hasNextLine()) {
            String p = temp.nextLine();
            p = p.strip();
            System.out.println(p);
            if (p.charAt(0) == '#' || p.charAt(1) == '#') {
                continue;
            }
            if (temp_pc < label.get(index).getLine()) {
                temp_pc++;
            } else {
                System.out.println("In Jal :" + p + "");
                if (p.length() >= 3) {
                    switch (p.substring(0, 3)) {
                        case "add":
                            add(regToIndex(temp.next()),regToIndex(temp.next()), regToIndex(temp.next()));
                            temp_pc++;
                            break;
                        case "sub":
                            sub(regToIndex(temp.next()),regToIndex(temp.next()) ,regToIndex(temp.next()) );
                            temp_pc++;
                            break;

                        case "mul":
                            input_mul();
                            temp_pc++;
                            break;

                        case "div":
                            input_div();
                            temp_pc++;
                            break;
                        case "rem":
                            input_rem();
                            temp_pc++;
                            break;
                        case "#  ":
                            temp.nextLine();
                            break;
                        default:
                            break;
                    }
                }

                if (p.length() >= 2) {
                    switch (p.substring(0, 2)) {
                        case "lw":
                            input_lw();
                            temp_pc++;
                            break;
                        case "li":
                            System.out.println(regToIndex(p.substring(3,5))+" "+Integer.parseInt(p.substring(7,p.length()-1)));
                            li(regToIndex(p.substring(3,5)),Integer.parseInt(p.substring(7,p.length()-1)));
                            temp_pc++;
                            break;
                        case "# ":
                            temp.nextLine();
                            break;
                        default:
                            break;
                    }
                }
                if (p.length() >= 4) {
                    switch (p.substring(0, 4)) {
                        case "addi":
                            addi(regToIndex(p.substring(5,7)), regToIndex(p.substring(9,11)),Integer.parseInt(p.substring(13)));
                            temp_pc++;
                            break;
                        case "mulh":
                            input_mulh();
                            temp_pc++;
                            break;
                        case "subi":
                            input_subi();
                            temp_pc++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        temp.close();*/
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
                if (z.charAt(0) != '#') {
                    temp_PC++;
                }
            }
        }
        temp.close();
    }
}