package SourceCode;

import java.util.Map;
import java.util.Vector;

public class Pipelining {

    Vector<String> InputCode;
    // int matrix;

    // boolean branchChecker(String s) {
    // boolean flag = false;
    // if (s.contains("beq")) {
    // String[] p = InputCode.elementAt(i).split("[,]", 0);
    // if (Register[regToIndex(p[1])] == Register[regToIndex(p[2])]) {
    // flag = true;
    // }
    // }
    // return flag;
    // }

    Vector<Vector<String>> matrix = new Vector<Vector<String>>();
    Vector<String> instructionPipeline = new Vector<String>();
    // Vector<String> normal = {"IF", "ID/RF", "EX", "MEM", "WB"};

    // InputCode.pu/
    // ("adds3,s3,s5");

    // InputCode.push("adds3,s3,s5");

    public void ProcessPipeline(Vector<String> InputCode, boolean dataForwarding, Map<String, Integer> labels) {

        // CHECK 0.0
        // System.out.print("-------------------------Let's Check
        // Input------------------");

        // int ii = 0;
        // while (ii < InputCode.size()) {
        // // SKIPPING THE LABEL PART
        // if (InputCode.elementAt(ii).contains(":") ||
        // InputCode.elementAt(ii).contains(".")) {
        // ii++;
        // continue;
        // }

        // for (int j = 0; j < InputCode.size(); j++)
        // System.out.println(InputCode.get(j));
        // dataForwarding = false;
        dataForwarding = true;

        String prev = "", prevprev = "";
        int stallcount = 0;
        int i = 0;
        System.out.println("################");
        String[] inst;

        String list[] = { "add", "sub", "addi", "subi" };
        boolean predictionFlag = false;
        while (i < InputCode.size()) {

            // if (i == InputCode.size())
            // break;
            // ignore labels and comments
            if (InputCode.elementAt(i).contains(":") ||
                    InputCode.elementAt(i).contains(".")) {
                i++;
                continue;
            }

            System.out.println();

            if (prevprev.length() > 0)
                System.out.println("prevprev =" + prevprev);
            if (prev.length() > 0)
                System.out.println("prev =" + prev);
            if (InputCode.get(i).length() > 0)
                System.out.println("curr =" + InputCode.get(i));

            // JUMP WILL CREATE A STALL
            // WDF GIVES 2 STALLS
            // DF GIVES 1 STALL

            if ((InputCode.elementAt(i).contains("beq"))
                    || (InputCode.elementAt(i).contains("bne"))
                    || (InputCode.elementAt(i).contains("bge"))
                    || (InputCode.elementAt(i).contains("blt"))
                    || (InputCode.elementAt(i).contains("bgt"))
                    || (InputCode.elementAt(i).contains("ble"))

            ) {
                // if (Register[regToIndex(inst[1])] == Register[regToIndex(inst[2])]) {
                // i = labels.get(inst[3]) + 1;

                System.out.println("\nSTALL DETECTED \n");
                stallcount++;

            }
            // || (InputCode.elementAt(i).contains("j"))) {
            // if (InputCode.elementAt(i).contains("j")) {
            // System.out.println("JUMP DETECTED : " + InputCode.elementAt(i) + "\n");
            // // inst = InputCode.elementAt(i).split("[,]", 0);
            // // i++;
            // // System.out.println(inst[0]);
            // // System.out.println(inst[1]);
            // // i = labels.get(inst[1]);

            // // continue;
            // }

            else if (prev.contains("lw") && InputCode.elementAt(i).contains("add")
                    ||
                    prev.contains("lw") && InputCode.elementAt(i).contains("sub")) {

                System.out.println("DEPENDENCY DETECTED : " + prev + " -> " + InputCode.elementAt(i) + "\n");
                String curReg[] = InputCode.elementAt(i).split("[,]", 0);
                String prevReg[] = prev.split("[,]", 0);
                // String prevprevReg[] = prevprev.split("[,]", 0);

                if (curReg[2].equals(prevReg[1])) {
                    if (dataForwarding == false)
                        stallcount += 2;
                    else
                        stallcount++;
                    System.out.println("STALL FOUND in " + InputCode.elementAt(i));
                }
            }

            // else if(list.contains(prev))

            else if ((prev.contains("add") && InputCode.elementAt(i).contains("add"))
                    ||
                    (prev.contains("sub") && InputCode.elementAt(i).contains("sub"))
                    ||
                    (prev.contains("add") && InputCode.elementAt(i).contains("sub"))
                    ||
                    (prev.contains("sub") && InputCode.elementAt(i).contains("add"))) {

                System.out.println("DEPENDENCY DETECTED : " + prev + " -> " + InputCode.elementAt(i) + "\n");

                String curReg[] = InputCode.elementAt(i).split("[,]", 0);
                String prevReg[] = prev.split("[,]", 0);
                // String prevprevReg[] = prevprev.split("[,]", 0);

                if (curReg[2].equals(prevReg[1]) || curReg[3].equals(prevReg[1])) {
                    stallcount += check_for_stall(1, dataForwarding);
                    System.out.println("STALL FOUND in " + InputCode.elementAt(i));
                }

                // if (prevprevReg.length > 0)
                // if (curReg[2].equals(prevprevReg[1]) || curReg[3].equals(prevprevReg[1])) {
                // stallcount += check_for_stall(2, dataForwarding);
                // System.out.println("Stall detected in " + InputCode.elementAt(i));
                // }

            }
            String temp = prev;
            prev = InputCode.elementAt(i);
            i++;
            if (i > 1)
                prevprev = temp;

        }

        System.out.println("\n\nData Forwarding: " + dataForwarding);
        System.out.println("Stall count: " + stallcount);
        return;

    }

    // lw/sw after branching
    // lw/sw don't get affected by branching

    // decide how many prev line to consider while checking

    // check mips pipeline
    // prevTag, prevprevTag, currentTag
    // two regs of currentTag

    // LINE 1
    // LINE 2
    // LINE 3

    // currentTag_reg1, currentTag_reg2;
    // prevTag_reg1, prevTag_reg2;

    // stall when execute comes after write

    // if(LINE 2 contains exe) {
    // if(LINE 3 contains write in common registers) {
    // if(prevTag_reg1 = currentTag_reg1 || prevTag_reg1 = currentTag_reg2) {
    // stall = true;
    // }
    // }

    // stall when lw in line 1
    // if(LINE 1 contains lw) {
    // if(LINE 3 contains write in common registers) {
    // if(prevTag_reg1 = currentTag_reg1 || prevTag_reg1 = currentTag_reg2) {
    // stall = true;
    // }
    // }

    // private void lwAndAdd() {
    // }

    /*
     * 
     * 
     * 
     * 
     * 
     * queue of current, prev, prev
     * 
     * 
     * if add/sub
     * -bne/enq = +1
     * -add/sub [check common]
     * -sw/lw [check common]
     * 
     * 
     * 
     * if addi/subi
     * -bne/enq = +1
     * -add/sub [check common]
     * -sw/lw [check common]
     * 
     * if sw
     * -bne/enq = +1
     * -add/sub [check common]
     * -sw/lw [check common]
     * 
     * if lw
     * -bne/enq = +1
     * -add/sub [check common]
     * -sw/lw [check common]
     * 
     * 
     * if bne/beq
     * -
     * 
     * 
     * if li/lax
     * if lw/sw
     * 
     * 
     * 
     * 
     * 
     */

    // boolean dataForwarding = false;

    int check_for_stall(int dependentLineIndex, boolean dataForwarding) {
        int stalls = 0;
        if (dependentLineIndex == 1) { // prev line
            if (dataForwarding) {
                stalls += 1;
            } else {
                stalls += 2;
            }
        } else if (dependentLineIndex == 2) { // prev prev line
            if (dataForwarding) {
                stalls += 0;
            } else {
                stalls += 1;
            }
        }
        return stalls;
    }
}