/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.solr.cloud.autoscaling.sim;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.apache.solr.common.cloud.ClusterState;
import org.apache.solr.common.util.TimeSource;
import org.junit.Before;
import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 */
public class TestSimClusterStateProvider extends SimSolrCloudTestCase {

  private static final String COLL = "gettingstarted";
  private static final Set<String> initialLiveNodes = ImmutableSet.of("192.168.1.108:7574_solr", "192.168.1.108:8983_solr");

  private String coll1State = "{'gettingstarted':{\n" +
      "    'replicationFactor':'2',\n" +
      "    'router':{'name':'compositeId'},\n" +
      "    'maxShardsPerNode':'2',\n" +
      "    'autoAddReplicas':'false',\n" +
      "    'shards':{\n" +
      "      'shard1':{\n" +
      "        'range':'80000000-ffffffff',\n" +
      "        'state':'active',\n" +
      "        'replicas':{\n" +
      "          'core_node2':{\n" +
      "            'core':'gettingstarted_shard1_replica1',\n" +
      "            'base_url':'http://192.168.1.108:8983/solr',\n" +
      "            'node_name':'192.168.1.108:8983_solr',\n" +
      "            'state':'active',\n" +
      "            'leader':'true'},\n" +
      "          'core_node4':{\n" +
      "            'core':'gettingstarted_shard1_replica2',\n" +
      "            'base_url':'http://192.168.1.108:7574/solr',\n" +
      "            'node_name':'192.168.1.108:7574_solr',\n" +
      "            'state':'active'}}},\n" +
      "      'shard2':{\n" +
      "        'range':'0-7fffffff',\n" +
      "        'state':'active',\n" +
      "        'replicas':{\n" +
      "          'core_node1':{\n" +
      "            'core':'gettingstarted_shard2_replica1',\n" +
      "            'base_url':'http://192.168.1.108:8983/solr',\n" +
      "            'node_name':'192.168.1.108:8983_solr',\n" +
      "            'state':'active',\n" +
      "            'leader':'true'},\n" +
      "          'core_node3':{\n" +
      "            'core':'gettingstarted_shard2_replica2',\n" +
      "            'base_url':'http://192.168.1.108:7574/solr',\n" +
      "            'node_name':'192.168.1.108:7574_solr',\n" +
      "            'state':'active'}}}}}}";


  @Before
  public void setup() throws Exception {
    ClusterState cs = ClusterState.load(1, coll1State.getBytes(UTF_8),
        initialLiveNodes, "/collections/gettingstarted/state.json");
    cluster = SimCloudManager.createCluster(cs, TimeSource.get("simTime:10"));
  }

  @Test
  public void testAddReplica() throws Exception {

  }

}