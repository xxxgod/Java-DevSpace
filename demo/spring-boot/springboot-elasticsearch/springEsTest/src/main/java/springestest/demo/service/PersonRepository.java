package springestest.demo.service;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import springestest.demo.pojo.Person;

@Repository
public interface PersonRepository  extends ElasticsearchRepository<Person, Integer> {
}
