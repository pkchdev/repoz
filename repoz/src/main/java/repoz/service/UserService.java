package repoz.service;

import repoz.model.User;

public interface UserService {

	public void save(User user);
	public User findByUsername(String username);
}
