package com.tiagomaniero.personapi.repository;

import com.tiagomaniero.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
