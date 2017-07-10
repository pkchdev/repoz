package repoz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repoz.model.Person;
import repoz.repository.PersonRepository;
import repoz.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personPepository;

	@Override
	public void addPerson(Person p) {
		personPepository.addPerson(p);
	}

}
