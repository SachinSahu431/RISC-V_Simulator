
package SourceCode;

import java.util.Vector;

public class DataSegment {
    
    private String id;  //Type of the data structure
    private String valueString;
    Vector<Integer> valueInt = new Vector<>();
    
    DataSegment(String id){
        this.id = id;
        this.valueString = "";
    }
    
    String getId(){
        return this.id;
    }
    
    // All inputs should be currently in a single line
    void workOnId(String s){
        switch(this.id){
            case ".asciz":
                setAsciz(s);
                break;
            case ".string":
                setString(s);
                break;
            case ".word":
                setWords(s);
                break;
            default :
                AssemblerNotPresent(s);
                break;
        }
    }

    private void setAsciz(String s) {
        try{
            this.valueString = s;
        }catch(Exception e){
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    private void setString(String s) {
        try{
            this.valueString = s+"\n";
        }catch(Exception e){
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    private void setWords(String s) {
        try{
            // We presume words as well will be in proper order like 245, 3, 4, 5
            s+='\0';
            String p="";
            for(int i=0;s.charAt(i) != '\0' ; i++){
                if(s.charAt(i) == ','){
                    valueInt.add(Integer.parseInt(p));
                    p="";
                    i++;
                }
                else {
                    p = p + s.charAt(i);
                }
            }
            
            
        }catch(Exception e){
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }

    private void AssemblerNotPresent(String s){
        throw new UnsupportedOperationException("Not supported yet ."); 
    }
    
    int getWord(int index){
        try{
            return valueInt.get(index);
        }catch(Exception e){
            throw new UnsupportedOperationException("Current Index not present in word array .");
        }
    }
    
}
