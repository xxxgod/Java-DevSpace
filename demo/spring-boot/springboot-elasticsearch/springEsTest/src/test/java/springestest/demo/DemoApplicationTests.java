package springestest.demo;


import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import springestest.demo.pojo.Accout;
import springestest.demo.pojo.Person;
import springestest.demo.service.AccoutRepository;
import springestest.demo.service.PersonRepository;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AccoutRepository accoutRepository;

    @Test
    void test1() {

        BoolQueryBuilder bl = QueryBuilders.boolQuery();
        // 相当于 match 全文检索
        //MatchQueryBuilder bl2= QueryBuilders.matchQuery("title","r5");

        //multi_match
       // MultiMatchQueryBuilder bl2= QueryBuilders.multiMatchQuery("r88","title","desc");

        // term
        //TermQueryBuilder bl2=  QueryBuilders.termQuery("id",1);

        // qurery 中 terms 返回id 中 包含 1或2的
       // TermsQueryBuilder bl2=  QueryBuilders.termsQuery("title","r5","r6");

        //  Range
        //RangeQueryBuilder bl2= QueryBuilders.rangeQuery("id").gt(1).lt(5);

        // 模糊查找
        //3.前缀查询  如果字段没分词，就匹配整个字段前缀
       // PrefixQueryBuilder  bl2 = QueryBuilders.prefixQuery("title","r5");

        //5.wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
        //WildcardQueryBuilder  bl2 =QueryBuilders.wildcardQuery("desc","*j*");//前面是fieldname，后面是带匹配字符的字符串
        //WildcardQueryBuilder  bl2  =QueryBuilders.wildcardQuery("desc","jj?");


        // 复合操作
        bl.must(QueryBuilders.rangeQuery("id").gt(20));
        bl.should(QueryBuilders.matchQuery("title","r22"));


      //根据id 排序
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
       // 分页
        PageRequest page = PageRequest.of(0,5);

        // 构建查询
        NativeSearchQueryBuilder n = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        n.withQuery(bl);
        //将分页设置到构建中
        n.withPageable(page);
        //将排序设置到构建中
        n.withSort(sort);

        //生产NativeSearchQuery
        NativeSearchQuery query = n.build();

        // 执行方法
        Page<Person> page2 = personRepository.search(query);


        //4.获取总条数(用于前端分页)
        int total = (int) page2.getTotalElements();

        //5.获取查询到的数据内容（返回给前端）
        List<Person> content = page2.getContent();

        content.forEach(s-> System.out.println(s.getId() +"  " + s.getTitle() + " "+ s.getDesc())
                );

    }



    @Test
    void test2() {
        //搜索 address 中包含 mill 的所有人的年龄分布以及平均年龄

        //1.创建查询条件，也就是QueryBuild
        QueryBuilder matchAllQuery = QueryBuilders.matchQuery("address","mill");//搜索 address 中包含 mill 的所有人
        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //2.0 设置QueryBuilder
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        //2.2指定索引库和文档类型
        nativeSearchQueryBuilder.withIndices("bank").withTypes("_doc");
        //2.3
        //根据年龄分组
        TermsAggregationBuilder ageB = AggregationBuilders.terms("age_group").field("age");
        //计算条件范围内所有人的平均年龄
        AvgAggregationBuilder avgB =AggregationBuilders.avg("avg_group").field("age");

        nativeSearchQueryBuilder.addAggregation(ageB).addAggregation(avgB);

        // 获取聚合分页结果
        AggregatedPage<Accout> goodsList = (AggregatedPage<Accout>) accoutRepository.search(nativeSearchQueryBuilder.build());


        // 在查询结果中找到聚合 - 根据聚合名称
        LongTerms stringTerms = (LongTerms) goodsList.getAggregation("age_group");
        // 获取桶
        List<LongTerms.Bucket> buckets = stringTerms.getBuckets();

        for (LongTerms.Bucket b : buckets){
            System.out.println(b.getKeyAsString() + "  " + b.getDocCount());
        }
        Avg avg = (Avg) goodsList.getAggregation("avg_group");
        System.out.println("平均薪资："+avg.getValueAsString());


    }


    @Test
    void test3() {
        //按照年龄聚合，并且请求这些年龄段的这些人的平均薪资

        //1.创建查询条件，也就是QueryBuild
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //2.0 设置QueryBuilder
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        //2.2指定索引库和文档类型
        nativeSearchQueryBuilder.withIndices("bank").withTypes("_doc");
        //2.3
        /**
         //根据年龄分组
         TermsAggregationBuilder ageB = AggregationBuilders.terms("age_group").field("age");
         //计算每个年龄段的平均年龄
         AvgAggregationBuilder avgB =AggregationBuilders.avg("balance_group").field("balance");
         // 将子聚合添加到 ageB
         ageB.subAggregation(avgB);
         nativeSearchQueryBuilder.addAggregation(ageB);
         */

        //根据年龄分组,子聚合，计算每个年龄段的平均年龄
        nativeSearchQueryBuilder.addAggregation(
                AggregationBuilders.terms("age_group").field("age")
                        .subAggregation(AggregationBuilders.avg("balance_group").field("balance")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );


        // 获取聚合分页结果
        AggregatedPage<Accout> goodsList = (AggregatedPage<Accout>) accoutRepository.search(nativeSearchQueryBuilder.build());


        // 在查询结果中找到聚合 - 根据聚合名称
        LongTerms stringTerms = (LongTerms) goodsList.getAggregation("age_group");
        // 获取桶
        List<LongTerms.Bucket> buckets = stringTerms.getBuckets();

        for (LongTerms.Bucket b : buckets){
            System.out.println(b.getKeyAsString() + "  " + b.getDocCount());
            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) b.getAggregations().asMap().get("balance_group");
            System.out.println("平均售价：" + avg.getValue());

        }
    }



    @Test
    void test4() {
        //查出所有年龄分布，并且这些年龄段中 M 的平均薪资和 F 的平均薪资以及这个年龄段的总体平均薪资

        //1.创建查询条件，也就是QueryBuild
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //2.0 设置QueryBuilder
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        //2.2指定索引库和文档类型
        nativeSearchQueryBuilder.withIndices("bank").withTypes("_doc");
        //2.3
        nativeSearchQueryBuilder
                // 根据年龄分组
                .addAggregation(AggregationBuilders.terms("age_group").field("age").size(100)
                //每个年龄段 总体平均薪资
                .subAggregation(AggregationBuilders.avg("balance_group").field("balance"))

                 //每个年龄段 再根据 性别分组
                        // ??????????????????????????????????

                .subAggregation(AggregationBuilders.terms("gender_group").field("gender.keyword").size(100)
                 //再每种性别中算出平均薪资
                .subAggregation(AggregationBuilders.avg("gender_balance_group").field("balance"))
                )

        );

        // 获取聚合分页结果
        AggregatedPage<Accout> goodsList = (AggregatedPage<Accout>) accoutRepository.search(nativeSearchQueryBuilder.build());


        // 在查询结果中找到聚合 - 根据聚合名称
        LongTerms stringTerms = (LongTerms) goodsList.getAggregation("age_group");
        // 获取桶
        List<LongTerms.Bucket> buckets = stringTerms.getBuckets();

        for (LongTerms.Bucket b : buckets){
            System.out.println(b.getKeyAsString() + "  " + b.getDocCount());

            InternalAvg avg = (InternalAvg) b.getAggregations().asMap().get("balance_group");
            System.out.println("平均薪资：" + avg.getValue());

            // 获取子聚合
            Terms name = b.getAggregations().get("gender_group");
            for (Terms.Bucket bucket : name.getBuckets()) {
                System.out.println(bucket.getKeyAsString()+" "+bucket.getDocCount());
                InternalAvg avg2 = (InternalAvg) bucket.getAggregations().asMap().get("gender_balance_group");
                System.out.println("平均薪资：" + avg2.getValue());
            }
        }
    }



    @Test
    void test7() {
        BoolQueryBuilder bl = QueryBuilders.boolQuery();
        //should在与must或者filter同级时，默认是不需要满足should中的任何条件

        // 复合操作 (account_number=1 or account_number =56) and (age=32)

        // 同级不符合
        //  bl.should(QueryBuilders.matchQuery("account_number","1"));
        //   bl.should(QueryBuilders.matchQuery("account_number","56"));
        //   bl.must(QueryBuilders.termQuery("age","32"));

        //解决1
//        BoolQueryBuilder filterBool = QueryBuilders.boolQuery();
//        filterBool.should(QueryBuilders.matchQuery("account_number","1"));
//        filterBool.should(QueryBuilders.matchQuery("account_number","56"));
//
//        bl.filter(filterBool);
//        bl.must(QueryBuilders.termQuery("age","32"));

        // 解决2    (account_number=1 or account_number =56) and age and balance 
        bl.must(QueryBuilders.termQuery("age","32"));
        bl.must(QueryBuilders.termQuery("balance","14992"));
        BoolQueryBuilder filterBool = QueryBuilders.boolQuery();
        filterBool.should(QueryBuilders.matchQuery("account_number","1"));
        filterBool.should(QueryBuilders.matchQuery("account_number","56"));
        bl.must(filterBool);


        // 分页
        PageRequest page = PageRequest.of(0,60);

        // 构建查询
        NativeSearchQueryBuilder n = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        n.withQuery(bl);
        //将分页设置到构建中
        n.withPageable(page);
        //生产NativeSearchQuery
        NativeSearchQuery query = n.build();

        // 执行方法
        Page<Accout> page2 = accoutRepository.search(query);


        //4.获取总条数(用于前端分页)
        int total = (int) page2.getTotalElements();

        //5.获取查询到的数据内容（返回给前端）
        List<Accout> content = page2.getContent();

        content.forEach(s-> System.out.println(s.getAccount_number() + " " + s.getAge())
        );

    }



}
