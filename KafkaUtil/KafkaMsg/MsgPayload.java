
package com.treemode.kpro.KafkaMsg;


import org.springframework.kafka.support.Acknowledgment;

/**
 *
 * @author alokdethe
 */
public class MsgPayload {

    void setTopicName(String topic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTopicName() {
        return "";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void setIdentifier(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdentifier(){
        return "";
    }
    
    public void setOffset(long offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPartition(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setPayloadContent(byte[] value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    byte[] getPayloadContent(){
        return null;
    }

    void setAcknowledgment(Acknowledgment acknowledgement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

