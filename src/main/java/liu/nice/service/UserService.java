package liu.nice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import liu.nice.dao.UserDao;
import liu.nice.pojo.User;

@Service
public class UserService {
	@Autowired 
	UserDao userdao;
	
	public User login(String username,String password) {
		User user = userdao.findUserByUsername(username);
		if((user!=null)&(user.getPassword().equals(password)))
		{
			return user;
		}else {
			return null;
		}
		
	}
}
