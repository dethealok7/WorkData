
//package com.treemode.kafkaintegrator.kafkaMessage;
package com.treemode.kpro.KafkaMsg;

import java.util.Iterator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.kafka.support.Acknowledgment;

/**
 *
 * @author alokdethe
 */
@Component
public class ConsumerRecordKafkaMsgMap {
    
    public MsgPayload getKafkaMessage( ConsumerRecord<String,byte[]> consumerRecord, Acknowledgment acknowledgement){
        
        if(consumerRecord == null){
            return null;
        }
        
        MsgPayload message = new MsgPayload();
        Iterator<Header> headerItr = consumerRecord.headers().iterator();
        while(headerItr.hasNext()){
            Header customHeader = headerItr.next();
        }
        
        message.setTopicName(consumerRecord.topic());
        if(consumerRecord.key() != null){
            message.setIdentifier(consumerRecord.key().toString());
        }
        message.setOffset(consumerRecord.offset());
        message.setPartition(new Long(consumerRecord.partition()));
        message.setPayloadContent(consumerRecord.value());
        message.setAcknowledgment(acknowledgement);
        
        return message;
    }
    
}
