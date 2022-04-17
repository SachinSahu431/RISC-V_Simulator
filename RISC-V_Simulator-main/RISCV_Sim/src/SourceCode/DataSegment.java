
package SourceCode;

import java.util.Vector;

public class DataSegment {

    private String id; // Type of the data structure
    private String valueString;
    Vector<Integer> valueInt = new Vector<>();
    int stored_Address;

    DataSegment(String id) {
        this.id = id;
        this.valueString = "";
        this.stored_Address = 0;
    }

    String getId() {
        return this.id;
    }

    // All inputs should be currently in a single line
    void workOnId(String arr[], String s) {

        switch (arr[0]) {
            case ".asciiz":
                setAsciiz(s);
                break;
            case ".string":
                setString(s);
                break;
            case ".word":
                setWords(arr, s);
                break;
            default:
                AssemblerNotPresent(s);
                break;
        }
    }
    // void workOnId(String s) {

    // switch (this.id) {
    // case ".asciiz":
    // setAsciiz(s);
    // break;
    // case ".string":
    // setString(s);
    // break;
    // case ".word":
    // setWords(s);
    // break;
    // default:
    // AssemblerNotPresent(s);
    // break;
    // }
    // }

    private void setAsciiz(String s) {
        try {
            this.valueString = s;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void setString(String s) {
        try {
            this.valueString = s + "\n";
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void setWords(String A[], String s) {
        try {
            // We presume all words are present in the string
            System.out.println("Inside setWords = " + s);
            for (int i = 1; i < A.length; i++)
                this.valueInt.add(Integer.parseInt(A[i]));
            System.out.println(this.valueInt);
            Memory.initArray(valueInt);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
            // throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public Vector<Integer> returnDataArray() {
        return valueInt;
    }

    private void AssemblerNotPresent(String s) {
        // throw new UnsupportedOperationException("Not supported yet .");
        System.out.println("Assembler not present in the data segment");
    }

    int getWord(int index) {
        try {
            return valueInt.get(index);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Current Index not present in word array .");
        }
    }

}
