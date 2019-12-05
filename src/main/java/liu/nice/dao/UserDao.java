package liu.nice.dao;

import liu.nice.pojo.User;

public interface UserDao {
	User findUserByUsername(String username);
}
