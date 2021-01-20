
package com.treemode.school.entity;

/**
 *
 * @author alokdethe
 */
public class StringUtil {
    
    public boolean NotNull(String s){
        
        if(s.equals(null)){
            return false;
        }
        else if(!s.equals(null)){
            return true;
        }
        return false;
    }
    
    public boolean isEmptyCheck(String s){
        if( s.isEmpty() ){
            return true;
        }
        else if(!s.isEmpty()){
            return false;
        }
        return false;
    }
}
