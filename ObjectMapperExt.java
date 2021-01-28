
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
    Get the JsonNode from the Json File
    
    jsonPath: Path to the Json File
    */
    public JsonNode getFromFileToJsonNode(String jsonPath){
        
        File f = new File(""+jsonPath);
        JsonNode jnode=null;
        
         try
         {
             if( f.exists() && f.canRead()){
                FileInputStream b;
                b = new FileInputStream(f);
                int cnt;
                String finalOutput="";
                
                do{
                    cnt = b.read();
                    finalOutput = finalOutput + ((char) cnt );
                }while(cnt != -1);
                
                jnode = readTree(finalOutput);
                
            }
             
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
         
         return jnode;
    }
    
    /*
    Get the JsonNode from the key
    
    key: key whose node is required
    jsonPath: Path to the Json File
    */
    public JsonNode getNode(String key, String jsonPath){
        
        File f = new File(""+jsonPath);
        JsonNode jnode=null;
        
         try
         {
             if(f.exists() && f.canRead()){
                FileInputStream b;
                b = new FileInputStream(f);
                int cnt;
                String finalOutput="";
                Map<String, JsonNode> mapN = null;
                
                do{
                    cnt = b.read();
                    finalOutput = finalOutput + ((char) cnt );
                }while(cnt != -1);
                
                jnode = readTree(finalOutput);
                Iterator<Entry<String, JsonNode>> itr = jnode.fields();
                
                while(itr.hasNext()){
                    Entry<String, JsonNode> ent =  itr.next();
                    if(ent.getKey().equalsIgnoreCase(key)){
                        jnode = ent.getValue();
                        break;
                    }
                }
                
            }
             
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
        
        return jnode;
    }
    
    /*
    Finds the key if exists in the JSON
    
    key: key to be found
    strJson: Actual json as String
    */
    public boolean findIfKeyExists(String key, String jsonString){
        boolean keyExistence = false;
        
        try{
            JsonNode j  = readTree(jsonString);
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
    Finds the key if exists in the JSON
    
    key: key to be found
    strJson: Path of the Json File
    */
    public boolean findIfKeyExists(String key, Path jsonPath){
    
        String jPath = jsonPath.toString();
        File f = new File(""+jPath);
        boolean keyExistence = false;
        String inString = "";
        FileInputStream fin = null;
        int c;
        
        if( f.exists() && f.canRead() ){
            
            try{
                
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
            
            
        }
        
        keyExistence = findIfKeyExists(key, inString);
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
            if( f.exists() && f.canRead() ){
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




