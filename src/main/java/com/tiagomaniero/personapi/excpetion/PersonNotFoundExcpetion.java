package com.tiagomaniero.personapi.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExcpetion extends Exception {
    public PersonNotFoundExcpetion(Long id){
        super("Person not found id: " + id);
    }
}
