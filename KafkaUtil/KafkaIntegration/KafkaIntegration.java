/*

 * Copyright (C) Joffer Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Alok Dethe <dethealok@gmail.com>, <det.alex0110@gmail.com>, July 2020

 */
package com.treemode.kpro.KafkaIntegration;


import com.treemode.kpro.KafkaMsg.MsgPayload;

/**
 *
 * @author alokdethe
 */
public interface KafkaIntegration {
    
    public void process(MsgPayload kafkaMessage, byte[] messageContent);
    
    public void reProcess(int partition, int offset, String topicName);
    
    public void processAck(MsgPayload messagePayload);
    
    public void processNAck(MsgPayload messagePayload);
    
}

