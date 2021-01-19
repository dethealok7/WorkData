
package com.treemode.kpro.KafkaIntegration;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 *
 * @author alokdethe
 */

class ConsumerAutoCSync implements Condition{

    @Override
    public boolean matches(ConditionContext cc, AnnotatedTypeMetadata atm) {
        
        String consumerEnable = cc.getEnvironment().getProperty("kafka.consumer.enable");
        String autoCommit = cc.getEnvironment().getProperty("kafka.auto.commit");
        
        if(consumerEnable != null && consumerEnable.equals("true")){
            if(autoCommit != null && autoCommit.equals("false")){
                System.out.println("in sync mode");
                return true;
            }
            return false;
           
        }
        else{
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class ConsumerAutoCAsync implements Condition{
    
    
    @Override
    public boolean matches(ConditionContext cc, AnnotatedTypeMetadata atm) {
    
    String consumerEnable = cc.getEnvironment().getProperty("kafka.consumer.enable");
        String autoCommit = cc.getEnvironment().getProperty("kafka.auto.commit");
        
        if(consumerEnable != null && consumerEnable.equals("true")){
            if(autoCommit != null && autoCommit.equals("true")){
                System.out.println("in sync mode");
                return true;
            }
            return false;
        }
        else{
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class ConsumerLCondition implements Condition{
    
    @Override
    public boolean matches(ConditionContext cc, AnnotatedTypeMetadata atm) {
    
    String consumerEnable = cc.getEnvironment().getProperty("kafka.consumer.enable");
        
        
        if(consumerEnable != null && consumerEnable.equals("true")){
            
            return true;
        }
        else{
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

@EnableKafka
@Configuration
@Conditional(ConsumerLCondition.class)
public class KafkaConsumerConfig {
    
    @Value("${kafka.bootstrapServer}")
    private String bootStrapServer;
    
    @Value("${kafka.bootstrapServerPort}")
    private String port;
    
    @Value("${kafka.groupConfig}")
    private String groupId;
    
    @Value("${kafka.message.max.size}")
    private String messageMaxSize;
    
    @Value("${kafka.ssl.cert}")
    private String kafkaCert;
    
    @Value("${kafka.ssl.passcode}")
    private String kafkaPassCode;
    
    //private KafkaUtils kafkaUtils;
    
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        
        String bootStrapServerConfig = "";
        if(bootStrapServer.contains(",")){
            String[] serverArray = bootStrapServer.split(",");
            
            for(String serverItr : serverArray){
                bootStrapServerConfig = bootStrapServerConfig + serverItr + ":"+port+",";
                
            }
            
            bootStrapServerConfig = bootStrapServerConfig.substring(0 , bootStrapServerConfig.length()-1);
        }
        else{
            bootStrapServerConfig = bootStrapServer+":"+port;
        }
        
        Map<String, Object> props = new HashMap<>();
        props.put(port, props);
        props.put(port, props);
        //kafkaUtils.configureAutoCommit(ptops); configure certificates
        
        props.put(port, props);
        props.put(port, props);
        props.put(port, props);
        props.put(port, props);
        
        //kafkaUtils.configureAutoCommit(ptops);
        
        return new DefaultKafkaConsumerFactory<>(props);
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        //kafkaUtils.configureAckMode(factory)
        
        return factory;
    }
    
    
}
