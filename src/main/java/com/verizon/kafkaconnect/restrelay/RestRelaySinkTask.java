/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.verizon.kafkaconnect.restrelay;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileStreamSinkTask writes records to stdout or a file.
 */
public class RestRelaySinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(RestRelaySinkTask.class);

  
    private String topictopost;
    private String bootstrap;
    private String authurl;
 

    public RestRelaySinkTask() {
    }

    @Override
    public String version() {
        return new RestRelaySinkConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
      
        topictopost = props.get(RestRelaySinkConnector.RESPONSE_TOPIC_CONFIG);
        bootstrap = props.get(RestRelaySinkConnector.BOOTSTRAP_SERVERS_CONFIG);
        authurl = props.get(RestRelaySinkConnector.AUTH_TOKEN_URL_CONFIG);
       
    }

    @Override
    public void put(Collection<SinkRecord> sinkRecords) {
        for (SinkRecord record : sinkRecords) {
          
            String urlname="";
            if(record.value().toString().toLowerCase().contains("urlname")){

            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(record.value().toString());
            while (m.find()) {
                               
              log.info("Writing line to {}: {}",m.group(1));
              urlname=m.group(1);
            }
            
            
            if (urlname.length()>4){
            	 log.info("testif" );
            	RestRelayClient test = new RestRelayClient();
				 test.callRestServicewithAuthToken(urlname,topictopost,bootstrap,authurl);
				
            }
            
            }
            
           // System.out.println("addedlog"+record.value().toString());
           // outputStream.println(record.value());
        }
    }
    
  
    

    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> offsets) {
    
    }

    @Override
    public void stop() {
        
    }

   
}
