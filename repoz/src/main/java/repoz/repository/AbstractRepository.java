package repoz.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public List<T> readAll(Class<T> entity) {
		return getSession().createCriteria(entity).list();
	}

	public T read(Class<T> entityType, Serializable id) {
		return getSession().get(entityType, id);
	}

	public T readQuery(Class<T> entityType, String column, String value) {
		String tableName = entityType.getAnnotation(Table.class).name();
		if (tableName != null) {
			System.out.println("from " + "User user" + " where " + column + " = :" + value);
			Query query = getSession().createQuery("from " + "User user" + " where " + column +  " = :" + column);
			query.setParameter(column, value);
			return (T) query.uniqueResult();
		}
		
		return null;
	}
}
