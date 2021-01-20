
package com.treemode.kpro.KafkaIntegration;

import com.treemode.kpro.KafkaMsg.MsgPayload;
import com.treemode.kpro.KafkaMsg.ProducerRecordKafkaMsgMap;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

/**
 *
 * @author alokdethe
 */
public class KafkaPro {
    
     @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;
    
    @Autowired
    private KafkaIntegration kafkaIntegration;
    
    @Autowired
    private ProducerRecordKafkaMsgMap producerRecordKafkaMsgMap;
    
    public void send( final MsgPayload msgPayload ){
        
        ListenableFuture<SendResult<String,byte[]>> future = kafkaTemplate.send(producerRecordKafkaMsgMap.getProducerRecord(msgPayload));
        
        
        future.addCallback(new ListenableFutureCallback<SendResult<String,byte[]>>(){
            
            @Override
            public void onSuccess(SendResult<String, byte[]> result){
                
                msgPayload.setPartition(new Long(result.getRecordMetadata().partition()));
                msgPayload.setOffset(new Long(result.getRecordMetadata().offset()));
                kafkaIntegration.processAck(msgPayload);
                
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
                kafkaIntegration.processNAck(msgPayload);
            }
            
        });
    }
    
    public Boolean sendSync(final MsgPayload msgPayload) throws InterruptedException, ExecutionException{
        
        final SettableListenableFuture<Boolean> settableListenableFuture = new SettableListenableFuture<Boolean>();
        
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send( producerRecordKafkaMsgMap.getProducerRecord(msgPayload) );
    
        future.addCallback(new ListenableFutureCallback<SendResult<String,byte[]>>(){
           
            @Override
            public void onSuccess(SendResult<String, byte[]> result){
                settableListenableFuture.set(Boolean.TRUE);
            }
            
            @Override
            public void onFailure(Throwable arg0){
                settableListenableFuture.set(Boolean.FALSE);
            }
            
        });
        
        do{
            
        }while(!settableListenableFuture.isDone());
    
        return settableListenableFuture.get();
    }
    
}

