package com.verizon.kafkaconnect.restrelay;

import org.junit.Test;

import com.verizon.kafkaconnect.northstar.NorthStarSourceConnectorConfig;

public class RestRelaySourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.print(NorthStarSourceConnectorConfig.conf().toRst());
  }
}