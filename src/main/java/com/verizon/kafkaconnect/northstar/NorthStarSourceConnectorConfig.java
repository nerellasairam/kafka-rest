package com.verizon.kafkaconnect.northstar;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Width;

import java.util.Map;


public class NorthStarSourceConnectorConfig extends AbstractConfig {

  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = " topic";
  
  public static final String POLL_INTERVAL_CONFIG = "poll.interval.milliseconds";
  private static final String POLL_INTERVAL_DOC =  "The amount of time in ms to wait between polling. ";
  static final long POLL_INTERVAL_DEFAULT = 60000;
  
  public static final String AUTH_TOKEN_URL_CONFIG = "authentication.token.url";
  private static final String AUTH_TOKEN_URL_DOC = "If needs authentication token, provide the url";
  static final String LISTEN_ADDRESS_DEFAULT = "optional";

  public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
  private static final String BOOTSTRAP_SERVERS_DOC = "bootstrap.servers";
  
  public static final String URL_GROUP_1 = "rest.api.list";
  
  public static final String REST_URL1_CONFIG = "rest.url.1";
  private static final String REST_URL1_DOC = "rest.url.1";
  
 // public static final String URL_GROUP_2 = "rest.api.list.2";
  public static final String REST_URL2_CONFIG = "rest.url.2";
  private static final String REST_URL2_DOC = "rest.url.2";
   
 // public static final String URL_GROUP_3 = "rest.api.list.3";
  public static final String REST_URL3_CONFIG = "rest.url.3";
  private static final String REST_URL3_DOC = "rest.url.3";

 // public static final String URL_GROUP_4 = "rest.api.list.4";
  public static final String REST_URL4_CONFIG = "rest.url.4";
  private static final String REST_URL4_DOC = "rest.url.4";
  
 // public static final String URL_GROUP_5 = "rest.api.list.5";
  public static final String REST_URL5_CONFIG = "rest.url.5";
  private static final String REST_URL5_DOC = "rest.url.5";
  
 // public static final String URL_GROUP_6 = "rest.api.list.6";
  public static final String REST_URL6_CONFIG = "rest.url.6";
  private static final String REST_URL6_DOC = "rest.url.6";

  // public static final String URL_GROUP_7 = "rest.api.list.7";
  public static final String REST_URL7_CONFIG = "rest.url.7";
  private static final String REST_URL7_DOC = "rest.url.7";
  
  // public static final String URL_GROUP_8 = "rest.api.list.8";
  public static final String REST_URL8_CONFIG = "rest.url.8";
  private static final String REST_URL8_DOC = "rest.url.8";
  
  public final String topic;
  public final long pollInterval;
  public final String authToken;
  public final String bootstrapServers;
  
  
  
  public final String url1;

  public final String url2;

  public final String url3;

  public final String url4;

  public final String url5;
 
  public final String url6;
  
  public final String url7;
  
  public final String url8;

  
  public NorthStarSourceConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
    
    this.topic = this.getString(TOPIC_CONFIG);
    this.pollInterval = this.getLong(POLL_INTERVAL_CONFIG);
    this.authToken = this.getString(AUTH_TOKEN_URL_CONFIG);
    this.bootstrapServers = this.getString(BOOTSTRAP_SERVERS_CONFIG);
   
    
    this.url1 = this.getString(REST_URL1_CONFIG);
   
    this.url2 = this.getString(REST_URL2_CONFIG);
  
    this.url3 = this.getString(REST_URL3_CONFIG);
  
    this.url4 = this.getString(REST_URL4_CONFIG);
   
    this.url5 = this.getString(REST_URL5_CONFIG);
   
    this.url6 = this.getString(REST_URL6_CONFIG);
    
    this.url7 = this.getString(REST_URL7_CONFIG);
    
    this.url8 = this.getString(REST_URL8_CONFIG);
      
  }

  public NorthStarSourceConnectorConfig(Map<String, String> parsedConfig) {
    this(conf(), parsedConfig);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        
        .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
        .define(POLL_INTERVAL_CONFIG, Type.LONG, POLL_INTERVAL_DEFAULT, ConfigDef.Range.between(10, Integer.MAX_VALUE), Importance.MEDIUM, POLL_INTERVAL_DOC)
        .define(AUTH_TOKEN_URL_CONFIG, Type.STRING, LISTEN_ADDRESS_DEFAULT, Importance.LOW, AUTH_TOKEN_URL_DOC)
        .define(BOOTSTRAP_SERVERS_CONFIG, Type.STRING, Importance.HIGH, BOOTSTRAP_SERVERS_DOC)
        .define(REST_URL1_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL1_DOC, URL_GROUP_1, 1, Width.LONG, REST_URL1_DOC)
        .define(REST_URL2_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL2_DOC, URL_GROUP_1, 2, Width.LONG, REST_URL2_DOC)
        .define(REST_URL3_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL3_DOC, URL_GROUP_1, 3, Width.LONG, REST_URL3_DOC)
        .define(REST_URL4_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL4_DOC, URL_GROUP_1, 4, Width.LONG, REST_URL4_DOC)
        .define(REST_URL5_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL5_DOC, URL_GROUP_1, 5, Width.LONG, REST_URL5_DOC)
        .define(REST_URL6_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL6_DOC, URL_GROUP_1, 6, Width.LONG, REST_URL6_DOC)
        .define(REST_URL7_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL7_DOC, URL_GROUP_1, 7, Width.LONG, REST_URL7_DOC)
        .define(REST_URL8_CONFIG, Type.STRING, null, Importance.HIGH, REST_URL8_DOC, URL_GROUP_1, 8, Width.LONG, REST_URL8_DOC);
  }

  
}
