
package com.treemode.kpro.KafkaIntegration;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.treemode.kpro.KafkaMsg.ConsumerRecordKafkaMsgMap;
import com.treemode.kpro.KafkaMsg.MsgPayload;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author alokdethe
 */
public class KafkaMsgFetch {
    
    @Value("${kafka.bootstrapServer}")
    private String bootStrapServer;
    
    @Value("${kafka.bootstrapServerPort}")
    private String port;
    
    @Value("${kafka.retrieval.groupConfig}")
    private String groupId;
    
    @Value("${kafka.ssl.cert}")
    private String kafkaCert;
    
    @Value("${kafka.ssl.passcode}")
    private String kafkaPassCode;
    
    @Autowired
    private ConsumerRecordKafkaMsgMap consumerRecordKafkaMsgMap;
    
    public MsgPayload fetchMsgPayload(int partition, int offset, String topicName){
        
        
        return consumerRecordKafkaMsgMap.getKafkaMessage(fetchKafkaConsumerRecord(partition,offset,topicName), null);
    }
    
    public byte[] fetchMsgByteContent(int partition, int offset, String topicName){
        
        ConsumerRecord<String,byte[]> consumerRecord = fetchKafkaConsumerRecord(partition,offset,topicName);
        return consumerRecord.value();
    }
    
    public ConsumerRecord<String, byte[]> fetchKafkaConsumerRecord(int partition, int offset, String topicName){
        
        TopicPartition tp = new TopicPartition(topicName, partition);
        String bootStrapServerConfig = "";
        if(bootStrapServer.contains(",")){
            String[] serverArray = bootStrapServer.split(",");
            for(String serverItr : serverArray){
                bootStrapServerConfig = bootStrapServerConfig + serverItr + ":"+port+",";
            }
            bootStrapServerConfig = bootStrapServerConfig.substring(0,bootStrapServerConfig.length()-1);
            
        }
        else{
            bootStrapServerConfig = bootStrapServer+":"+port;
        }
        
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServerConfig);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        //kafkautil.configureCertificates
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"none");
        org.apache.kafka.clients.consumer.KafkaConsumer<String,byte[]> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, byte[]>(props);
        consumer.assign(Arrays.asList(tp));
        consumer.seek(tp, offset);
        
        ConsumerRecords<String, byte[]> records = consumer.poll(1000000);
        ConsumerRecord<String,byte[]> consumerRecord = null;
        
        if(records != null){
            consumerRecord = (ConsumerRecord<String,byte[]>) records.iterator().next();
        }
        consumer.close();
        
        if(consumerRecord != null){
            return consumerRecord;
        }
        
        return null;
    }
    
}


