
package com.treemode.kpro.KafkaMsg;

import org.apache.kafka.common.header.Header;

/**
 *
 * @author alokdethe
 */
public class CusHeader implements Header {
    
     private String key;
    private byte[] value;
    
    public CusHeader(){
        
    }
    
    public CusHeader(String key, String strValue){
        this.key = key;
        System.out.println("Hostname "+strValue);
        this.value = strValue.getBytes();
    }
    
    @Override
    public String key(){
        return key;
    }
    
    @Override
    public byte[] value(){
        return value;
    }
    
    public String getStringValue(){
        return value.toString();
    }
    
    public void setHeader(String key, String strValue){
        this.key = key;
        this.value = strValue.getBytes();
    }
    
}

