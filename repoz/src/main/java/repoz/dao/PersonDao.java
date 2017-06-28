package repoz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import repoz.model.Person;

@Repository
public class PersonDao extends AbstractDao implements IPersonDao {

	private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);
	
	@Override
	public void addPerson(Person p) {
		persist(p);
		logger.info("Person saved successfully, Person Details= %s", p);
	}
	
	/*public List<Employee> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }*/
}
