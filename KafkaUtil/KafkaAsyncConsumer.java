
package com.poc.restapi;


import com.treemode.kafkaintegrator.kafkaMessage.ConsumerRecordKafkaMessageMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/** @author alokdethe*/
@Component
@Conditional(ConsumerAutoCommitAsync.class)
public class KafkaAsyncConsumer {
    
    @Autowired
    private KafkaIntegration kafkaMessagePro;
    
    @Autowired
    private ConsumerRecordKafkaMsgMap consumerRecordKafkaMsgMap;
    
    @KafkaListener(topics = "topicname")
    @Conditional(ConsumerAutoCommitAsync.class)
    public void receive(ConsumerRecord<String, byte[]> consumerRecord){
        kafkaMessagePro.process(consumerRecordKafkaMsgMap.getKafkaMessage(consumerRecord, null), consumerRecord.value());
    }
    
}

