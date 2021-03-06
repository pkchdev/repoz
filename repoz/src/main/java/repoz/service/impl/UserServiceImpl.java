package repoz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import repoz.model.User;
import repoz.repository.RoleRepository;
import repoz.repository.UserRepository;
import repoz.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(roleRepository.findAllRole().get(0));
		userRepository.create(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public List<User> readAll() {
		return userRepository.readAll(User.class);
	}

}
