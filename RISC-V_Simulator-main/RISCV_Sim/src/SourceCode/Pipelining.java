package SourceCode;

public class Pipelining {

    private void ProcessPipeline(int InputCode[]) {
        String s1 = "", s2 = "";

        while(){
            if (s2.contains("lw") || InputCode.elementAt(i).contains("add")) {
                String s[] = InputCode.elementAt(i).split("[,]", 0);

            } else if (s2.contains("lw") || InputCode.contains("sub")) {
            }else if(){
            }
        }
        

    }

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

    private void lwAndAdd() {
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * queue of current, prev, prevprev
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

    boolean dataForwarding = false;

    int countStalls(int dependentLine, boolean dataForwarding) {
        int stalls = 0;
        if (dependentLine == 1) { // prev line
            if (dataForwarding) {
                stalls += 1;
            } else {
                stalls += 2;
            }
        } else if (dependentLine == 2) { // prev prev line
            if (dataForwarding) {
                stalls += 0;
            } else {
                stalls += 1;
            }
        }
        return 0;
    }
}