package repoz.service;

import java.util.List;

import repoz.model.User;

public interface UserService {

	public void create(User user);
	public User findByUsername(String username);
	public List<User> readAll();
}
