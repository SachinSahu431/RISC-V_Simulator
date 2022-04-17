package SourceCode;

import java.util.Vector;

public class Pipelining {

    Vector<String> InputCode;
    // int matrix;

    Vector<Vector<String>> matrix = new Vector<Vector<String>>();
    // InputCode.pu/
    // ("adds3,s3,s5");

    // InputCode.push("adds3,s3,s5");

    public void ProcessPipeline(Vector<String> InputCode, boolean dataForwarding) {

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
        dataForwarding = false;
        // dataForwarding = true;

        String prev = "", prevprev = "";
        int stallcount = 0;
        int i = 0;
        System.out.println("################");

        while (i < InputCode.size()) {

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

            if (i == InputCode.size())
                break;

            if (prev.contains("lw") && InputCode.elementAt(i).contains("add")
                    ||
                    prev.contains("lw") && InputCode.elementAt(i).contains("sub")) {

                String curReg[] = InputCode.elementAt(i).split("[,]", 0);
                String prevReg[] = prev.split("[,]", 0);
                // String prevprevReg[] = prevprev.split("[,]", 0);

                if (curReg[1].equals(prevReg[0]) || curReg[2].equals(prevReg[0])) {
                    stallcount++;
                    System.out.println("Stall detected in " + InputCode.elementAt(i));
                }
            }

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
                String prevprevReg[] = prevprev.split("[,]", 0);

                if (curReg[2].equals(prevReg[1]) || curReg[3].equals(prevReg[1])) {
                    stallcount += check_for_stall(1, dataForwarding);
                    System.out.println("Stall detected in " + InputCode.elementAt(i));
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