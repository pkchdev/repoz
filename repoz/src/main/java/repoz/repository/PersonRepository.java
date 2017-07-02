package repoz.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import repoz.model.Person;

@Repository
public class PersonRepository extends AbstractRepository<Person> {

	private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	public void addPerson(Person p) {
		create(p);
		logger.info("Person saved successfully, Person Details " + p.toString());
	}

}
