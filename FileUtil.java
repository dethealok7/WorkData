package com.treemode.kpro.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author alokdethe
 */
public class FileUtil {
    
    public String getJSonStringFromFile(String jsonPath){
        
        String inString="";
        
        try{
            
            File f = new File(""+jsonPath);
            int c;
            FileInputStream fin=null;
            
            
                fin = new FileInputStream(f);
                do{
                    c = fin.read();
                    inString = inString + (char)c;
                }while(c != -1);
                
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        
        return inString;
    }
    
}

