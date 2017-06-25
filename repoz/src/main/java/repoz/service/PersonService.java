package repoz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repoz.dao.PersonDao;
import repoz.model.Person;

@Service
public class PersonService implements IPersonService {

	private PersonDao personDao;

	public void setPersonDAO(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDao.addPerson(p);
	}

}
