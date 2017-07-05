package repoz.service;

public interface SecurityService {

	public String findLoggedInUsername(String username);
	public void autoLogin(String username, String password);
}
