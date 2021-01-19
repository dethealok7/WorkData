/*

 * Copyright (C) Joffer Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Alok Dethe <dethealok@gmail.com>, <det.alex0110@gmail.com>, July 2020

 */
package com.treemode.kpro.KafkaIntegration;

import com.treemode.kpro.KafkaMsg.ConsumerRecordKafkaMsgMap;
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
@Conditional(ConsumerLCondition.class)
public class KafkaConsumer {
    
    @Autowired
    private KafkaIntegration kafkaMsgPro;
    
    @Autowired
    private ConsumerRecordKafkaMsgMap consumerRecordKafkaMsgMap;
    
    @KafkaListener(topics = "topics")
    public void receive(ConsumerRecord<String, byte[]> consumerRecord){
        kafkaMsgPro.process(consumerRecordKafkaMsgMap.getKafkaMessage(consumerRecord, null), consumerRecord.value());
    }
}

