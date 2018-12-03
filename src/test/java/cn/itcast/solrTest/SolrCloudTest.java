package cn.itcast.solrTest;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SolrCloudTest {
    CloudSolrServer server;
    @Before
    public void setUp() throws Exception {

     server= new CloudSolrServer("192.168.138.129:2181,192.168.138.130:2181,192.168.138.131:2181");
        server.setDefaultCollection("myCollection1");
    }

    @Test
    public void test() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","2");
        document.addField("title","大马猴,你是大马猴");
        server.add(document);
        server.commit();
    }

    @Test
    public void testQuery() throws Exception{
        QueryResponse response = server.query(new SolrQuery("*:*"));
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testDelete() throws Exception{
        UpdateResponse updateResponse = server.deleteById("2");
        server.commit();
    }
}
