package com.tiagomaniero.personapi.service;


import com.tiagomaniero.personapi.dto.request.PersonDTO;
import com.tiagomaniero.personapi.dto.response.MessageResponseDTO;
import com.tiagomaniero.personapi.entity.Person;
import com.tiagomaniero.personapi.excpetion.PersonNotFoundExcpetion;
import com.tiagomaniero.personapi.mapper.PersonMapper;
import com.tiagomaniero.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson, "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundExcpetion {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundExcpetion  {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExcpetion {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(savedPerson, "Updated person with ID ");

    }

    private Person verifyIfExists(Long id) throws PersonNotFoundExcpetion {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundExcpetion(id));
    }

    private MessageResponseDTO createMessageResponse(Person savedPerson, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + savedPerson.getId())
                .build();
    }
}
