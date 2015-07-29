package tarena.com.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tarena.com.dao.NoteBookMapper;
import tarena.com.dao.UserMapper;
import tarena.com.entity.NoteBook1;
import tarena.com.entity.User;
import tarena.com.util.Md5Util;
import tarena.com.util.SystemConstant;

/**
 * 登陆模块的业务组件
 * 将来要使用SPRING声明式事物来管理该业务组件的事物,声明式业务很简单,
 * 只需要配置即可,但要求这些业务方法有一定的规律
 * @author Administrator
 *
 */
@Service
public class LoginService implements SystemConstant{
	@Resource
	private UserMapper usermapper;
	@Resource
	private NoteBookMapper notebook;

	/**
	 * 添加用户
	 * @return
	 */
	public boolean addUser(User user){
		if(user==null){
			throw new RuntimeException("参数不能为空");
		}
		//1.判断用户名是否存在
		//2.新增用户
		//3.给用户初始化笔记本
		
		User u=usermapper.findByName(user.getCn_user_name());
		if(u==null){
			//新增用户
			createuser(user);
	
			//创建笔记本
			
			initNoteBook(user.getCn_user_id());
			
		}else{
			return false;
		}
		return true;
		
	}
	/**
	 * 效验账号和密码是否正确
	 * @param userName
	 * @param password
	 * @return
	 */
	public Map<String,Object> checkUser(String userName ,String password){
		
		if(userName==null){
			throw new RuntimeException("用户名不能为空");
		}
		
		if(password==null){
			throw new RuntimeException("密码不能为空");
		}
		/**
		 * 将返回值信息已键值对的形式存储
		 */
		Map<String,Object> map=new HashMap<String, Object>();
		User u=usermapper.findByName(userName);
		
		//效验用户名
		if(u==null){
			//用户名不存在
			map.put("flag", USER_NAME_ERROE);
			return map;
		}
		//效验密码
		String md5Password=Md5Util.md5(password);
		
		if(!md5Password.equals(u.getCn_user_password())){
			//密码输入错误
			map.put("flag",USER_PASSWORD);
			return map;
		}
		map.put("flag", SUCCESS);
		map.put("user", u);
		return map;
		
	}
	
	/**
	 * 更新密码
	 */
	public Map<String,Object> updatepwd(String username,String newpassword,String oldpassword,String fnewpassword){
		if(username==null){
			throw new RuntimeException("用户名能不能为空");
		}
		if(newpassword==null){
			throw new RuntimeException("密码不能为空");
		}
		if(oldpassword==null){
			throw new RuntimeException("密码不能为空");
		}
		String md5pwd=Md5Util.md5(oldpassword);
		Map<String,Object>map=new HashMap<String,Object>();
		if(newpassword.equals(fnewpassword)){
			
			User user=usermapper.findByName(username);
			 
			
			//System.out.println(md5pwd);
			//System.out.println(user.getCn_user_password());
			
			if(md5pwd.equals(user.getCn_user_password())){
				
				user.setCn_user_password(Md5Util.md5(newpassword));
				user.setCn_user_name(username);
				user.setCn_user_token(newpassword);
				usermapper.updatapassword(user);
				//System.out.println("3333333333333333333");
				map.put("msg", true);
			
				return map;
		}else{
			map.put("msg", false);
			
		}
		}
	
		return map;
		
	}

	/**
	 * 使用Alt+Shift+m可封装选中代码为一个方法
	 * @param userid
	 */
	private void createuser(User user) {
		user.setCn_user_id(UUID.randomUUID().toString());
		String md5Password=Md5Util.md5(user.getCn_user_password());
		user.setCn_user_password(md5Password);
		usermapper.save(user);
	}

	
	private void initNoteBook(String userid) {
		//初始化收藏笔记本
		NoteBook1 n1=new NoteBook1();
		n1.setcn_notebook_id(UUID.randomUUID().toString());
		n1.setcn_notebook_type_id(FAVORITES_TYPE_ID);
		n1.setcn_notebook_name(FAVORITES_TYPE_NAME);
		n1.setcn_user_id(userid);
		n1.setcn_notebook_createtime(new Timestamp(System.currentTimeMillis()).toString());
		notebook.saveNote(n1);
		//初始化回收站笔记本
		NoteBook1 n2=new NoteBook1();
		n2.setcn_notebook_id(UUID.randomUUID().toString());
		n2.setcn_notebook_type_id(RECYCLE_TYPE_ID);
		n2.setcn_notebook_name(RECYCLE_TYPE_NAME);
		n2.setcn_user_id(userid);
		n2.setcn_notebook_createtime(new Timestamp(System.currentTimeMillis()).toString());
		notebook.saveNote(n2);
		//初始化活动笔记本
		NoteBook1 n3=new NoteBook1();
		n3.setcn_notebook_id(UUID.randomUUID().toString());
		n3.setcn_notebook_type_id(ACTION_TYPE_ID);
		n3.setcn_notebook_name(ACTION_TYPE_NAME);
		n3.setcn_user_id(userid);
		n3.setcn_notebook_createtime(new Timestamp(System.currentTimeMillis()).toString());
		notebook.saveNote(n3);
		//初始化推送笔记本
		NoteBook1 n4=new NoteBook1();
		n4.setcn_notebook_id(UUID.randomUUID().toString());
		n4.setcn_notebook_type_id(PUSH_TYPE_ID);
		n4.setcn_notebook_name(PUSH_TYPE_NAME);
		n4.setcn_user_id(userid);
		n4.setcn_notebook_createtime(new Timestamp(System.currentTimeMillis()).toString());
		notebook.saveNote(n4);
		
	}
}
