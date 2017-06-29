package com.verizon.kafkaconnect.northstar;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

public class NorthStarSourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(NorthStarSourceTask.class);
  
    public  String topic;
    public  long pollInterval;
    public  String authToken;
    public  String bootstrapServers;
    
    public  String url1;
 
    public  String url2;

    public  String url3;

    public  String url4;

    public  String url5;

    public  String url6;
    public  String url7;
    public  String url8;
    
	
	static final Map<String, Object> EMPTY = ImmutableMap.of();
  @Override
  public String version() {
    return VersionUtil.getVersion();
  }
  
   NorthStarSourceConnectorConfig config;
   NorthStarRestClient restclient=new NorthStarRestClient();
  
   
  @Override
  public void start(Map<String, String> map) {

	  this.config = new NorthStarSourceConnectorConfig(map);
	  topic=this.config.topic;
	  pollInterval=this.config.pollInterval;	  
	  authToken=this.config.authToken;
	  bootstrapServers=this.config.bootstrapServers;
	  
	  
	  url1=this.config.url1;
	  
	  
	  url2=this.config.url2;
	
	  
	  url3=this.config.url3;
	
	  
	  url4=this.config.url4;
	
	  
	  url5=this.config.url5;

	  
	  url6=this.config.url6;
	  url7=this.config.url7;
	  url8=this.config.url8;
	
	  
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
	  
	  try {
	  ArrayList<SourceRecord> records = null;
	  
	  log.info("kafkaconnectrest pollInterval", pollInterval);
		  synchronized (this) {			
				this.wait(pollInterval);}
		
	  
	  if(url1!=null && url1.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url1, topic, bootstrapServers );
	  if(url2!=null && url2.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url2, topic, bootstrapServers );
	  if(url3!=null && url3.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url3, topic, bootstrapServers );
	  if(url4!=null && url4.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url4, topic, bootstrapServers );
	  if(url5!=null && url5.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url5, topic, bootstrapServers );
	  if(url6!=null && url6.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url6, topic, bootstrapServers );
	  if(url7!=null && url7.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url7, topic, bootstrapServers );
	  if(url8!=null && url8.length()>9)
		   restclient.callRestServicewithAuthToken( authToken, url8, topic, bootstrapServers );
	
	  
	  return records;
	  
	  }catch (Exception e){
		  
		  log.info("kafkaconnectrest exception", e);
	  }
	return null;
  }

  @Override
  public void stop() {
    //TODO: Do whatever is required to stop your task.
  }
}