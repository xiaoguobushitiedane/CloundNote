package tarena.com.web;

import java.util.HashMap;
import java.util.Map;













import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tarena.com.entity.Result;
import tarena.com.entity.User;
import tarena.com.service.LoginService;
import tarena.com.util.SystemConstant;

/**
 * 登陆
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private LoginService loginService;
	
	@RequestMapping("/register.do")
	@ResponseBody
	//public String reqister(ModelMap model,User user){返回view页面时ModelMap用来存储数据
	public Result reqister(User user){
		boolean b=loginService.addUser(user);
		/**
		 * 点击注册按钮后页面将通过post请求发送到这
		 */
		
		//返回一个Result对象给回调函数
		return new Result(b);
	//	model.put("1", "2");
		//return "list";//返回的list在配置文件中为list.jsp的页面
		//{success;true,messager:"",data:true}
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Result login(String username,String password,HttpSession session){
		
		Map<String,Object> map=loginService.checkUser(username, password);
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(map+"1111111111");
		//获取登陆检查的信息返回值,并转化为Int
		int flag=Integer.valueOf(map.get("flag").toString());
		System.out.println(flag+"22222222222");
		
		if(flag==SystemConstant.SUCCESS){
			//登陆成功
			User user=(User)map.get("user");
			session.setAttribute("user", user);
			
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("flag", flag);
			m.put("userId", user.getCn_user_id());
			m.put("userName", user.getCn_user_name());
			//{"success":true,"message":null,"data":{"flag":0,"userId":"xxx","userName":"aaa"}}

			return new Result(m);
		}else{
			//登陆失败
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("flag", flag);
			//{"success":true,"message":null,"data":{"flag":1}}

			return new Result(m);
		}
	
	}
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public Result logiout(HttpSession session){
		//注销session
		session.invalidate();
		
		return new Result();
		
	}
	
	/**
	 * 更新密码
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Result update(String username,String newpassword,
			String oldpassword,String fnewpassword,HttpSession session){
		
		Map<String,Object>map=loginService.updatepwd(username, newpassword, oldpassword, fnewpassword);
			
			boolean msg=(Boolean) map.get("msg");
			
		Map<String,Object>m=new HashMap<String, Object>();
		
		
		
		m.put("msg", msg);
		System.out.println(msg);
		return new Result(m);
		
	}
	
}
