package com.yiii.es.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yiii.es.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;


@Service
@Slf4j
public class ESService {

    // 索引名称 (可以理解成数据库的表名)
    private static final String index_name = "user";

    // es对象
    @Autowired
    private RestHighLevelClient client;

    public int add() {
        int count = 0;
        try {
            // 从数据库拿到数据
            List<User> list = User.getAll();
            for(User u : list) {
                log.info("---------写入es数据打印---------->"+u+u.getId());
                addIndex(u, String.valueOf(u.getId()));
                count += 1;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return count;
    }
    /**
     * 添加索引
     * @param user
     * @param id
     * @throws IOException
     */
    public void addIndex(User user, String id) throws IOException{
        // 创建一个索引，指定文档id，source是文档内容
        IndexRequest request = new IndexRequest(index_name).id(id).source(beanToMap(user));
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println("获取新增的es数据------------>"+response);
    }
    /**
     * 查询
     * @param id
     * @return
     * @throws IOException
     */
    public Object get(String id) throws IOException{
        GetRequest getRequest = new GetRequest(index_name,id);
        GetResponse response = client.get(getRequest,RequestOptions.DEFAULT);

        Map<String, Object> source = response.getSource();
        System.out.println(response);
        return JSONObject.toJSON(source);
    }
    /**
     * 删除
     * @param id
     * @return
     * @throws IOException
     */
    public boolean del(String id) throws IOException{
        DeleteRequest request = new DeleteRequest(index_name,id);
        DeleteResponse res = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(res);
        return true;
    }
    /**
     * 改
     * @param id
     * @return
     * @throws IOException
     */
    public boolean edit(User user,String id) throws IOException{
        UpdateRequest request = new UpdateRequest(index_name,id).doc(beanToMap(user));
        UpdateResponse name = client.update(request,RequestOptions.DEFAULT);
        System.out.println(name);
        return true;
    }
    /**
     * 条件搜索
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    public List<User> select(String key,String value,int start, int end,int type) throws IOException {
        // 指定索引，类似于数据库的表
        SearchRequest searchRequest = new SearchRequest(index_name);
        // 创建查询对象，相当于写查询sql
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(type==1) {
            // matchQuery是模糊查询，会对key进行分词
            searchSourceBuilder.query(QueryBuilders.matchQuery(key,value));
        }else if(type==2) {
            // termQuery是精准查询
            searchSourceBuilder.query(QueryBuilders.termQuery(key,value));
        }
        // 指定从哪条开始查询
        searchSourceBuilder.from(start);
        // 需要查出的总记录条数
        searchSourceBuilder.size(end);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));

        SearchHit[] hits = response.getHits().getHits();
        List<User> list = new LinkedList<>();
        for(SearchHit hit: hits){
            User user = JSONObject.parseObject(hit.getSourceAsString(),User.class);
            list.add(user);
        }
        return list;
    }

    /**
     * 对象转map
     * @param bean
     * @param <T>
     * @return
     */
    public <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if(beanMap.get(key) != null){
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }
}
