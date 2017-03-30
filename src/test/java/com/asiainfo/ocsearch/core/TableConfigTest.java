package com.asiainfo.ocsearch.core;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class TableConfigTest {
    @Test
    public void getKeyFields() throws Exception {
        System.out.println(tableConfig.getKeyFields());
    }

    TableConfig tableConfig;
    @Before
    public void setUp() throws Exception {
        String json = "{\n" +
                "    \"name\": \"tableName\",\n" +
                "    \"store\": {\n" +
                "        \"type\": \"d\",\n" +
                "        \"period\": 30,\n" +
                "        \"partition\": \"title\"\n" +
                "    },\n" +
                "    \"content\": {\n" +
                "        \"name\": \"_root_\",\n" +
                "        \"type\": \"text\"\n" +
                "    },\n" +
                "    \"rowkey\": {\n" +
                "        \"version\": 1,\n" +
                "        \"keys\": [\n" +
                "            {\n" +
                "                \"name\": \"title\",\n" +
                "                \"order\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"hbase\": {\n" +
                "        \"name\": \"hbaseTable\",\n" +
                "        \"isExist\": \"true\"\n" +
                "    },\n" +
                "    \"solr\": {\n" +
                "        \"name\": \"solrCollection\",\n" +
                "        \"isExist\": \"true\"\n" +
                "    },\n" +
                "    \"fields\": [\n" +
                "        {\n" +
                "            \"name\": \"length\",\n" +
                "            \"type\": \"int\",\n" +
                "            \"indexed\": \"true\",\n" +
                "            \"contented\": \"false\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"content\",\n" +
                "            \"type\": \"text\",\n" +
                "            \"indexed\": \"false\",\n" +
                "            \"contented\": \"false\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"title\",\n" +
                "            \"type\": \"text\",\n" +
                "            \"indexed\": \"true\",\n" +
                "            \"contented\": \"false\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"queryfields\": [\n" +
                "        {\n" +
                "            \"name\": \"title\",\n" +
                "            \"weight\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"content\",\n" +
                "            \"weight\": 20\n" +
                "        }\n" +
                "    ],\n" +
                "    \"basefields\": [\n" +
                "        {\n" +
                "            \"name\": \"title\",\n" +
                "            \"isFast\": true\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JsonNode jsonNode = (new ObjectMapper()).readTree(json);
        this.tableConfig = new TableConfig(jsonNode);
    }


    @Test
    public void getTableFields() throws Exception {

        System.out.println(tableConfig.getTableFields());
    }

    @Test
    public void getSchemaFields() throws Exception {

        System.out.println(tableConfig.getSchemaFields());
    }

    @Test
    public void getBaseFields() throws Exception {

            System.out.println(tableConfig.getBaseFields());

    }

    @Test
    public void getQueryFields() throws Exception {


        System.out.println(tableConfig.getQueryFields());
    }

    @Test
    public void getSolrFields() throws Exception {

        Iterator var4 = tableConfig.getSolrFields().iterator();

        while(var4.hasNext()) {
            Element element = (Element)var4.next();
            System.out.println(element.asXML());
        }
    }
}
