package repoz.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import repoz.model.Role;

@Repository
public class RoleRepository extends AbstractRepository<Role> {

	public List<Role> findAllRole() {
		return readAll(Role.class);
	}

}
