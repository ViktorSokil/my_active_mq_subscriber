package com.sokil.service.impl;

import com.sokil.dao.IPersonDAO;
import com.sokil.dao.ISequenceDao;
import com.sokil.dto.PersonDTO;
import com.sokil.entity.Person;
import com.sokil.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personServise")
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonDAO personDAO;
    @Autowired
    private ISequenceDao sequenceDao;

    @Override
    public void create(PersonDTO p) {
        Long id = sequenceDao.getNextSequenceId(PersonDTO.COLLECTION_NAME);
        p.setId(id);
        personDAO.create(p);
    }

    @Override
    public void save(PersonDTO personDTO) {
        personDAO.save(parseToPerson(personDTO));
    }

    @Override
    public PersonDTO readById(String id) {
        return personDAO.readById(id);
    }

    @Override
    public void update(PersonDTO p) {
        personDAO.update(p);
    }

    @Override
    public int deleteById(String id) {
        return personDAO.deleteById(id);
    }

    @Override
    public List<PersonDTO> getAll() {
        return personDAO.getAll();
    }

    @Override
    public void remove(Long id) {
        personDAO.remove(id);
    }

    private Person parseToPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setPersonName(personDTO.getName());
        person.setPersonAddress(personDTO.getAddress());
        return person;
    }
}
