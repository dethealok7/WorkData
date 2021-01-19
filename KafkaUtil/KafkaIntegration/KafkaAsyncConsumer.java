
package com.treemode.kpro.KafkaIntegration;

import com.treemode.kpro.KafkaMsg.ConsumerRecordKafkaMsgMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/** @author alokdethe
 */
@Component
@Conditional(ConsumerAutoCSync.class)
public class KafkaAsyncConsumer {
    
    @Autowired
    private KafkaIntegration kafkaMsgPro;
    
    @Autowired
    private ConsumerRecordKafkaMsgMap consumerRecordKafkaMsgMap;
    
    @KafkaListener(topics = "topicname")
    @Conditional(ConsumerAutoCSync.class)
    public void receive(ConsumerRecord<String, byte[]> consumerRecord){
        kafkaMsgPro.process(consumerRecordKafkaMsgMap.getKafkaMessage(consumerRecord, null), consumerRecord.value());
    }
    
}
