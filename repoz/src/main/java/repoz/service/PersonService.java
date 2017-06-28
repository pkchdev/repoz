package repoz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repoz.dao.IPersonDao;
import repoz.model.Person;

@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
	private IPersonDao dao;

	@Override
	public void addPerson(Person p) {
		dao.addPerson(p);
	}

}
