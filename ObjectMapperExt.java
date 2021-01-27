
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
    Get the List of Nodes for the specific key
    
    key: key whose value needs to be retreived
    jsonPath: path to json file
    */
    public List<JsonNode> getValueForKey(String key, String jsonPath){
    
        String mode="1";
        File f = new File(""+jsonPath);
        boolean keyExistence = false;
        List<JsonNode> jnode = null;
        
        try{
            JsonNode j  = readTree(f);
            jnode = j.findValues(key);
            
            for(int i=0; i<jnode.size(); i++){
                System.out.println(" ==> "+jnode.toString());
            }
            
        }catch(Exception ex){
           ex.printStackTrace();
        }
           
        return jnode;
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




