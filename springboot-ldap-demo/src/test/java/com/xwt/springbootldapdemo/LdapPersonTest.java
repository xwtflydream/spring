package com.xwt.springbootldapdemo;

import com.xwt.springbootldapdemo.entity.Person;
import com.xwt.springbootldapdemo.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LdapPersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findAll() throws Exception {
        personRepository.findAll().forEach(p -> {
            System.out.println(p);
        });
    }

    @Test
    public void addPerson() throws Exception {
        Person person = new Person();
        person.setUid("Uid:1");
        person.setSuerName("AAA");
        person.setCommonName("aaa");
        person.setUserPassword("123456");
        personRepository.save(person);
        personRepository.findAll().forEach(p -> {
            System.out.println(p);
        });
    }

}
