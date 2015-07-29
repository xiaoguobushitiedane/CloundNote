package tarena.com.dao;

import tarena.com.entity.User;

@Mapperluo
public interface UserMapper {
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	User findByName(String userName);
	/**
	 * 插入一个用户
	 * @param user
	 */
	void save(User user);
	
	/**
	 * 更新密码
	 */
	void updatapassword(User user);
}
