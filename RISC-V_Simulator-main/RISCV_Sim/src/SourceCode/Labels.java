// This class will store data of all the actions performed by a particular label
package SourceCode;

import java.util.Vector;

public class Labels {
    
    private String id;
    int line;
    
    
    Labels(int pc, String id){
        this.id = id;
        this.line = pc;
    }
    
    String getId(){
        return this.id;
    }
    
    void setId(String s){
        this.id = s;
    }

    int getLine() {
        return line;
    }
}
