
package com.poc.restapi;


import com.treemode.kafkaintegrator.kafkaMessage.ConsumerRecordKafkaMsgMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author alokdethe
 */
@Component
@Conditional(ConsumerAutoAsync.class)
public class KafkaAsyncConsumer {
    
    @Autowired
    private KafkaIntegrate kafkaMsgPro;
    
    @Autowired
    private ConsumerRecordKafkaMsgMap consumerRecordKafkaMsgMap;
    
    @KafkaListener(topics = "topicname")
    @Conditional(ConsumerAutoAsync.class)
    public void receive(ConsumerRecord<String, byte[]> consumerRecord){
        kafkaMsgPro.process(consumerRecordKafkaMsgMap.getKafkaMessage(consumerRecord, null), consumerRecord.value());
    }
    
}

