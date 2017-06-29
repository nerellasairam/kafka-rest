package com.verizon.kafkaconnect.restrelay;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class RestRelayProducer {
	
	
	
	
         
         public  void produceMessage(String test, String topic, String bootstrapServers)  {
              
            // System.out.println("produceMessage");
             //Configure the Producer
             Properties configProperties = new Properties();
             configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
             configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
             configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

             org.apache.kafka.clients.producer.Producer producer = new KafkaProducer<String, String>(configProperties);
         
             if(test!=null) {
                 ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topic, test);
                 producer.send(rec);
                
             }
            
             producer.close();
         }
       

}
