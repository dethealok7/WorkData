
package com.treemode.kpro.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 *
 * @author alokdethe
 */
public class ObjectMapperExt extends ObjectMapper {
    
    public String jsonFormatter(String jsonString){
        return null;
    }
    
    /*
    Checks if the key exists
    
    key: key to be found
    jsonPath: Path to json file
    */
    public boolean findIfKeyExists(String key, String jsonPath){
    
        String mode="1";
        File f = new File(""+jsonPath);
        boolean keyExistence = false;
        
        try{
            JsonNode j  = readTree(f);
            Iterator<String> itr = j.fieldNames();
            
            while(itr.hasNext()){
                
                if(key.equalsIgnoreCase(itr.next())){
                    keyExistence = true;
                }
            }
            
        }catch(Exception ex){
           ex.printStackTrace();
        }
            
        return keyExistence;
    }
    
    /*
    Finds the value if exists in the JSON
    
    key: Value to be found
    jsonPath: Path to the json file
    */
    public boolean findIfValueExists(String key, String jsonPath){
    
        String mode="1";
        File f = new File(""+jsonPath);
        boolean keyExistence = false;
        
        try{
            JsonNode j  = readTree(f);
            List<JsonNode> jnode = j.findValues(key);
            
            if(!jnode.isEmpty()){
                //System.out.println("Check existence ==> "+itr.next());
                keyExistence = true;
            }
            
        }catch(Exception ex){
           ex.printStackTrace();
        }
            //System.out.println("Check existence ==> "+keyExistence);
        return keyExistence;
    }
    
    /*
    Finds the word if exists in the JSON
    
    key: Word to be found
    jsonPath: Path to the json file
    */
    public boolean findObjectExist(String key, String jsonPath){
        
        boolean resultOf=false;
        
          try{
            
            File f = new File(""+strJson);
            char c='1';
            String strToBeFound=key;
            
            //f..exists() && f.canRead() && f.isFile()
            if(true){
                FileInputStream b;
                b = new FileInputStream(f);
                
                int cnt;
                String finalOutput="";
                
                
                do{
                    cnt = b.read();
                    if( '"' == ((char)cnt)){
                        char finalChar;
                        finalOutput = "";
                        
                        do{
                           finalChar = (char)b.read();
                           finalOutput = finalOutput + finalChar;
                           System.out.println("char ==> "+finalOutput);
                        }while(finalChar != '"' );
                        
                        finalOutput = finalOutput.substring(0, finalOutput.length()-1);
                        
                        if( finalOutput.equalsIgnoreCase(strToBeFound) || finalOutput.matches("/"+strToBeFound+"/") ){ //set pattern
                            resultOf=true;
                            break;
                        }
                    }
                    
                    System.out.println(" ==> "+ finalOutput );
                }while(cnt != -1);
                
            }
            
            
        
        }
        catch(Exception ex){
            
            System.out.println("Inside exception");
            ex.printStackTrace();
        }

          return resultOf;
    }
    
    
}




