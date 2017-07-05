package repoz.service.impl;

public interface SecurityService {

	public String findLoggedInUsername(String username);
	public void autologin(String username, String password);
}
