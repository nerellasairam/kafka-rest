package com.verizon.kafkaconnect.northstar;

import org.junit.Test;

import com.verizon.kafkaconnect.northstar.NorthStarSourceConnectorConfig;

public class NorthStarSourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.print(NorthStarSourceConnectorConfig.conf().toRst());
  }
}