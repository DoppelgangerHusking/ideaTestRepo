package cn.itcast.solrTest;
//大马猴,套你猴子,臭傻逼,操你妈
import cn.itcast.solrj.pojo.Item;
import cn.itcast.solrj.pojo.Item2;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SolrJTest {
    @Test
    public void testGit(){
        System.out.println("我操你妈,你妈死了");
    }
    //连接solr服务器
    HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/core2");
    @Test
    public void test() throws IOException, SolrServerException {
        //创建solr的输入Document
        SolrInputDocument document = new SolrInputDocument();
        //添加字段
        document.addField("id", 15L);
        document.addField("title", "霸王手机,手机中的战斗机,买不了吃亏,买不了上当");
        document.addField("price", 998);
        //如果存在就修改,不存在就添加
        server.add(document);
        server.commit();
    }

    @Test
    public void test2() throws IOException, SolrServerException {
        Item item = new Item(16L,"你就是野猪皮",9999);
        server.addBean(item);
        server.commit();
    }

    @Test
    public void testDelete() throws IOException, SolrServerException {
//        server.deleteById("16"); //id为字符串
        server.deleteByQuery("title:野猪");
        server.commit();
    }

    @Test
    public void testQuery() throws SolrServerException {
        SolrQuery query = new SolrQuery("华为");
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        System.out.println("本次共搜索到"+results.size()+"条数据");
        for (SolrDocument document : results) {
            System.out.println("id :"+document.getFieldValue("id"));
            System.out.println("title :"+document.getFieldValue("title"));
            System.out.println("price :"+document.getFieldValue("price"));
//            System.out.println(document.getFieldValueMap());
//            System.out.println(document.getFieldValuesMap());
        }
    }

    @Test
    public void testBeanQuery() throws SolrServerException {
        SolrQuery query = new SolrQuery("apple");
        QueryResponse response = server.query(query);
        List<Item2> beans = response.getBeans(Item2.class);
        for (Item2 bean : beans) {
            System.out.println(bean);
        }
    }

    @Test
    public void testBoolean() throws Exception {
        SolrQuery query = new SolrQuery("title:apple OR title:小米");
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result);
        }
        server.commit();
    }

    @Test
    public void testSimilar() throws Exception {
        SolrQuery query = new SolrQuery("title:appke~");
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result);
        }
        server.commit();
    }

    @Test
    public void testRange() throws Exception {
        SolrQuery query = new SolrQuery("price:[100000 TO 200000]");
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result);
        }
        server.commit();

    }
    @Test
    public void testSort() throws Exception {
        SolrQuery query = new SolrQuery("华为");
        query.setSort("price", SolrQuery.ORDER.desc);
        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            System.out.println(result);
        }
        server.commit();
    }
    @Test
    public void testPage() throws Exception {
        int pageNum = 1;
        int pageSize = 5;
        int start = (pageNum -1 ) * pageSize;

        SolrQuery query = new SolrQuery("手机");
        query.setSort("id", SolrQuery.ORDER.asc);
        query.setStart(start);
        query.setRows(pageSize);//设置每页显示条数
        QueryResponse response = server.query(query);
        List<Item2> beans = response.getBeans(Item2.class);
        System.out.println("当前第"+pageNum+"页"+",本页显示了"+pageSize+"条数据");
        for (Item2 bean : beans) {
            System.out.println(bean);
        }
        server.commit();
    }
}
