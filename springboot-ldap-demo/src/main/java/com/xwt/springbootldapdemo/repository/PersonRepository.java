package com.xwt.springbootldapdemo.repository;

import com.xwt.springbootldapdemo.entity.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {
}
