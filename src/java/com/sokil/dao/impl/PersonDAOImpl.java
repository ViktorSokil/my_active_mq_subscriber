package com.sokil.dao.impl;

import com.mongodb.WriteResult;
import com.sokil.dao.IPersonDAO;
import com.sokil.dto.PersonDTO;
import com.sokil.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("personDAO")
@Transactional
public class PersonDAOImpl implements IPersonDAO {
	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private SessionFactory sessionFactory;


	public void create(PersonDTO p) {
		mongoOps.insert(p);
	}

	public PersonDTO readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoOps.findOne(query, PersonDTO.class);
	}

	public void update(PersonDTO p) {
		mongoOps.save(p);
	}

	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = mongoOps.remove(query, PersonDTO.class);
		return result.getN();
	}

	public List<PersonDTO> getAll() {
		return mongoOps.findAll(PersonDTO.class);
	}

	public void remove(Long id) {
		mongoOps.remove(Query.query(Criteria.where("_id").is(id)), PersonDTO.class);
	}

	@Override
	public void save(Person person) {
		currentSession().saveOrUpdate(person);
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}


