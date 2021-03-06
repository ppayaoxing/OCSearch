package com.asiainfo.ocsearch.service.schema;

import com.asiainfo.ocsearch.listener.SystemListener;
import com.asiainfo.ocsearch.meta.Schema;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

/**
 * Created by mac on 2017/5/31.
 */
public class AddSchemaServiceTest {
    @Test
    public void testQuery() throws Exception {
        testAddSchema();

    }

    public static void testAddSchema() throws Exception {

        new SystemListener().initAll();
        JsonNode jsonNode = new ObjectMapper().readTree("{\n" +
                "   \"request\":true,\n" +
                "    \"name\": \"phoenixSchema\",\n" +
                "    \"rowkey_expression\": \"md5(phone,imsi)+‘|‘+phone+‘|‘+imsi\",\n" +
                "    \"table_expression\": \"table+’_'+time\",\n" +
                "    \"index_type\": 2,\n" +
                "    \"content_fields\": [\n" +
                "   \n" +
                "    ],\n" +
                "    \"inner_fields\": [\n" +
                "       \n" +
                "    ],\n" +
                "    \"query_fields\": [\n" +
                "      \n" +
                "    ],\n" +
                "    \"fields\": [\n" +
                "       \n" +
                "        {\n" +
                "            \"name\": \"title\",\n" +
                "            \"indexed\": true,\n" +
                "            \"index_stored\": true,\n" +
                "            \"index_type\": \"text_gl\",\n" +
                "            \"store_type\": \"STRING\",\n" +
                "            \"content_field\": \"_root_\",\n" +
                "            \"hbase_column\": \"1\",\n" +
                "            \"hbase_family\": \"B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"content\",\n" +
                "            \"indexed\": true,\n" +
                "            \"index_stored\": false,\n" +
                "            \"index_type\": \"text_gl\",\n" +
                "            \"store_type\": \"STRING\",\n" +
                "            \"hbase_column\": \"0\",\n" +
                "            \"hbase_family\": \"B\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        System.out.println(new Schema(jsonNode));
        new AddSchemaService().doService(jsonNode);

    }


}