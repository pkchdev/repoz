package repoz.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public abstract class AbstractRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void create(T entity) {
		getSession().persist(entity);
	}

	public void delete(Serializable id) {
		getSession().delete(id);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(Class<T> entity) {
		return getSession().createCriteria(entity).list();
	}

	public T read(Class<T> entityType, Serializable id) {
		return getSession().get(entityType, id);
	}

	@SuppressWarnings("unchecked")
	public T readQueryFromValue(Class<T> entityType, String column, String value) {
		Criteria criteria = getSession().createCriteria(entityType);
		return (T) criteria.add(Restrictions.eq(column, value)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> readAllQueryFromValue(Class<T> entityType, String column, String value) {
		Criteria criteria = getSession().createCriteria(entityType);
		return (List<T>) criteria.add(Restrictions.eq(column, value)).list();
	}
}
