package springestest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springestest.demo.service.PersonRepository;
import springestest.demo.pojo.Person;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/selectAll")
    public Object selectAll() {
        Iterable<Person>  findALL = personRepository.findAll();
        return findALL;
    }
    @RequestMapping("/insert")
    public String insert() {
        Person person = new Person();
        //person.setId(8);
        person.setTitle("gg");
        person.setDesc("1");
        person.setUser("年後");
        Person save = personRepository.save(person);
        return "添加成功，当前添加后数据的id为：" + save.getId();
    }
    @RequestMapping("/selectById/{id}")
    public Person selectById(@PathVariable("id") Integer id) {
        Optional<Person> findById = personRepository.findById(id);
        return findById.get();
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id){
        Optional<Person> findById = personRepository.findById(id);
        Person person =  findById.get();
        if(findById != null){
            person.setTitle("cccccc");
            personRepository.save(person);
            return "当前修改的id为：" + person.getId() + "；成功！";
        }
        return "当前修改的id为：" + id + "；不存在！";
    }

    @RequestMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        personRepository.deleteById(id);
    }



}
