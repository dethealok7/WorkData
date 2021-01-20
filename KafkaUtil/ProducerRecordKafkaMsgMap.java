
package com.treemode.kpro.KafkaMsg;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author alokdethe
 */
public class ProducerRecordKafkaMsgMap {
    
    public ProducerRecord<String, byte[]> getProducerRecord(MsgPayload msgPayload){
        
        ProducerRecord<String, byte[]> producerRecord = new ProducerRecord<>(
                msgPayload.getTopicName(), msgPayload.getIdentifier(),msgPayload.getPayloadContent()
        );
        
        String producerHostName = "";
        
        try{
            producerHostName = InetAddress.getLocalHost().getHostName();
        }
        catch(UnknownHostException ex){
            ex.printStackTrace();
        }
        
        
        producerRecord.headers().add(new CusHeader("producerHostName", producerHostName));
        
        return producerRecord;
    }
    
}

