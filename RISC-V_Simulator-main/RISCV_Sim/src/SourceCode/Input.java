/*
1. Take input as words and then do the operations on the corresponding data
*/

package SourceCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Input extends Register {

    File code;
    Scanner input;
    int pc = 0;
    Vector<Labels> label = new Vector<Labels>();
    Vector<String> InputCode = new Vector<String>();
    Map<String, Integer> labels = new HashMap<String, Integer>();
    // Note : PC count in label is showing from which pc input is for label

    Input() throws FileNotFoundException {
        code = new File(
                "D:/SACHIN/Sachin_Docs/sem 4/CO/RISC project/gh/RISC-V_Simulator/RISC-V_Simulator-main/TestCase/loop2.txt");
        // "D:/SACHIN/Sachin_Docs/sem 4/CO/RISC
        // project/gh/RISC-V_Simulator/RISC-V_Simulator-main/TestCase/loop2.txt");
        // gh\RISC-V_Simulator\RISC-V_Simulator-main\TestCase\loop2.txt
        // "D:\\Work\\NOTES\\Computer
        // Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\test.txt");
        // "D:\\Work\\NOTES\\Computer
        // Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\loop2.txt");
        // "D:\\Work\\NOTES\\Computer
        // Organisation\\Project\\RISC-V_Simulator\\RISC-V_Simulator-main\\TestCase\\test2.txt");
        // "RISC-V_Simulator-main/TestCase/BubbleSort3.txt");
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

    // LETS ASSUME THAT EVERY COMMENT IS IN NEXT LINE I.E. SAME LINE DOES NOT CONTAI
    // ANY COMMENTS
    void CollectInput() {
        // STORING THE INPUT
        int i = 0;
        while (input.hasNextLine()) {
            String s = input.nextLine();
            s = s.replaceAll("\\s", "");
            // STATEMENT MAPING LABELS WITH CORRESPONDING VALUE WHERE THEY HAVE TO RETURN
            if (s.indexOf(":") != -1)
                labels.put(s.substring(0, s.indexOf(":")).trim(), i);
            // s = s + ",";
            if (s.contains("#"))
                continue;
            InputCode.add(s);
            i++;
        }
        // CHECK 0.0
        // System.out.print("-------------------------Let's Check
        // Input------------------");
        // for(int j=0;j<InputCode.size();j++)
        // System.out.println(InputCode.get(j));

        i = 0;
        while (i < InputCode.size()) {
            // SKIPPING THE LABEL PART
            if (InputCode.elementAt(i).contains(":") || InputCode.elementAt(i).contains(".")) {
                i++;
                continue;
            }

            String[] inst = InputCode.elementAt(i).split("[,]", 0);
            // CHECK 2.0
            System.out.println("Input Code: " + InputCode.elementAt(i));
            System.out.println("Break outs: " + Arrays.toString(inst));
            System.out.println("-----------------------");
            if (InputCode.elementAt(i).contains("addi")) {
                addi(regToIndex(inst[0].substring(4)), regToIndex(inst[1]), Integer.parseInt(inst[2]));
            } else if (InputCode.elementAt(i).contains("add")) {
                add(regToIndex(inst[0].substring(3)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("jne")) {
                // Jump Command
            } else if (InputCode.elementAt(i).contains("jal")) {
                // input_jal(inst[0].substring(3));
                Register[1] = i + 1;
                i = labels.get(inst[0].substring(3)) + 1;
            } else if (InputCode.elementAt(i).contains("ble")) {
                if (Register[regToIndex(inst[0].substring(3))] < Register[regToIndex(inst[1])]) {
                    i = labels.get(inst[2]) + 1;
                }
            } else if (InputCode.elementAt(i).contains("bne")) {
                if (Register[regToIndex(inst[0].substring(3))] != Register[regToIndex(inst[1])]) {
                    i = labels.get(inst[2]) + 1;
                }
            } else if (InputCode.elementAt(i).contains("bgt")) {
                if (Register[regToIndex(inst[0].substring(3))] > Register[regToIndex(inst[1])]) {
                    i = labels.get(inst[2]) + 1;
                }
            } else if (InputCode.elementAt(i).contains("beq")) {
                if (Register[regToIndex(inst[0].substring(3))] == Register[regToIndex(inst[1])]) {
                    i = labels.get(inst[2]) + 1;
                }
            } else if (InputCode.elementAt(i).contains("lw")) {
                System.out.println("lw " + inst[0] + " " + inst[1]);
                loadWord(regToIndex(inst[0].substring(2)), addressToIndex(inst[1]));
            } else if (InputCode.elementAt(i).contains("li")) {
                li(regToIndex(inst[0].substring(2)), Integer.parseInt(inst[1]));
            } else if (InputCode.elementAt(i).contains("sw")) {
                System.out.println("sw " + inst[0] + " " + inst[1]);
                storeWord(regToIndex(inst[0].substring(2)), addressToIndex(inst[1]));
            } else if (InputCode.elementAt(i).contains("subi")) {
                subi(regToIndex(inst[0].substring(4)), regToIndex(inst[1]), Integer.parseInt(inst[2]));
            } else if (InputCode.elementAt(i).contains("sub")) {
                sub(regToIndex(inst[0].substring(3)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("mulh")) {
                mulh(regToIndex(inst[0].substring(4)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("mul")) {
                mul(regToIndex(inst[0].substring(3)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("div")) {
                div(regToIndex(inst[0].substring(3)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("rem")) {
                rem(regToIndex(inst[0].substring(3)), regToIndex(inst[1]), regToIndex(inst[2]));
            } else if (InputCode.elementAt(i).contains("jr") && InputCode.elementAt(i).contains("ra")) {
                i = Register[1];
            } else if (InputCode.elementAt(i).contains("j")) {
                System.out.println(inst[0].substring(1, inst[0].length()));
                i = labels.get(inst[0].substring(1, inst[0].length()));
            } else if (InputCode.elementAt(i).contains(".exit")) {
                break;
            } else {
                System.out.println(InputCode.elementAt(i) + " :Thinking to invent some new command here or what...");
            }

            i++;
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
}