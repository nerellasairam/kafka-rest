package com.verizon.kafkaconnect.vcpops;

import org.junit.Test;

import com.verizon.kafkaconnect.northstar.NorthStarSourceConnectorConfig;

public class VcpOpsSourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.print(NorthStarSourceConnectorConfig.conf().toRst());
  }
}