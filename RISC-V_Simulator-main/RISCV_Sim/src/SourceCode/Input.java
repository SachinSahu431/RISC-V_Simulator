/*
1. Take input as words and then do the operations on the corresponding data
*/

package SourceCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

        File code = new File("");
        Scanner input;
        
    Input() throws FileNotFoundException{
        this.input = new Scanner(code);
    }
    
    // A function used to take input of the program and call for other functions to do the task
    void TakeFileInputinMain(){
        
        // For a paticular set of instructions it shall run efficiently
        while(input.hasNextLine()){
            switch(input.next()){
                case "add":
                    input_Add();
                    break;
                case "sub":
                    input_Sub();
                    break;
                case "lw":
                    input_lw();
                    break;
                case "li":
                    input_li();
                    break;
                case "addi":
                    input_addi();
                    break;
                case "subi":
                    input_subi();
                    break;
                case "mul":
                    input_mul();
                    break;
                case "mulh":
                    input_mulh();
                    break;
                case "div":
                    input_div();
                    break;
                case "rem":
                    input_rem();
                    break;
                case "bne":
                    input_bne();
                    break;
                case "jal":
                    input_jal();
                    break;
                case "#":
                    input.nextLine();
                    break;
                default:
                    break;
                    
                
            }
        }
    }

    // A method that converts input string which contains a target register to index of that particualr register
    private int regToIndex(String reg){
        
            return 0;    
    }
    
    private int addressToIndex(String reg){
            
        return 0;    
    }
    
    private void input_Add() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e add rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        //Calling Add operation of memory class
    }

    private void input_Sub() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        // Currently only accepts the standard way i.e sub rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        // Calling Sub operation of memory class
    }

    private void input_lw() {
        int dest,src;
        // Currently only accepts the standard way i.e lw x1, 0(x2)
        dest = regToIndex(input.next());
        src = addressToIndex(input.next());
        // Calling lw operation of memory class
    }

    private void input_li() {
        int dest,addr;
        // throw new UnsupportedOperationException("Not supported yet.");
        // Currently only accepts the standard way i.e li x0, 0
        dest = regToIndex(input.next());
        addr = input.nextInt();
    }

    private void input_addi() {
        // throw new UnsupportedOperationException("Not supported yet."); 
        int rd,rs1,imm;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e add rd, rs1, 5
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        imm = input.nextInt();
        //Calling Addi operation of memory class
    }

    private void input_bne() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void input_jal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void input_subi() { 
        int rd,rs1,immd;
        // throw new UnsupportedOperationException("Not supported yet.");
        // Currently only accepts the standard way i.e sub rd, rs1, 8
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        immd = input.nextInt();
        // Calling Subi operation of memory class        
    }

    private void input_mul() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e mul rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        //Calling Mul operation of memory class
    }

    private void input_mulh() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e mulh rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        //Calling Mulh operation of memory class
    }

    private void input_div() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e div rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        //Calling div operation of memory class
    }

    private void input_rem() {
        int rd,rs1,rs2;
        // throw new UnsupportedOperationException("Not supported yet.");
        //Currently only accepts the standard way i.e rem rd, rs1, rs2
        rd = regToIndex(input.next());
        rs1 = regToIndex(input.next());
        rs2 = regToIndex(input.next());
        //Calling Rem operation of memory class
    }
}