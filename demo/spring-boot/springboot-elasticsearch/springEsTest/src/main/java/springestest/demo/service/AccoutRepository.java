package springestest.demo.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import springestest.demo.pojo.Accout;

@Repository
public interface AccoutRepository extends ElasticsearchRepository<Accout, Integer> {
}

