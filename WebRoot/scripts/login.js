/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		//调用业务方法
		
		register();
	});
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
});

//注册
function register() {
	/**
	 * .val()获得匹配元素的当前值
	 */
	var userName=$("#regist_username").val();
	var nickname=$("#nickname").val();
	var password=$("#regist_password").val(); 
	var final_password=$("#final_password").val();
	
	
	//效验账号,3-20字母,数字,下滑线
	var reg=/^\w{3,20}$/;
	
	
	if(!reg.test(userName)){
		$("#warning_1 span").text("请输入有效的用户名");
		$("#warning_1").show();
		return;
	}else{
		$("#warning_1").hide();
	}
	//效验密码
	
	if(password.length==0||password.length>10){
		alert("1111111111111");
		$("#warning_2 span").text("密码输入错误");
		$("#warning_2").show();
		return;
		}else{	
		$("#warning_2").hide();	
		}
	
	//效验确认密码
	
	if(password!=final_password){
		$("#warning_3 span").text("密码输入不一致");
		$("#warning_3").show();
		return;
	}else{
		$("#warning_3").hide();
	}
	
	$.post(
			basePath+"/login/register.do",{"cn_user_name":userName,
				"cn_user_password":password,"cn_user_desc":nickname,
				"cn_user_token":final_password},function(result){
					
					//{success;true,messager:"",data:true}
				if(result.success){
					//
					if(result.data){
						//注册成功
						//attr设置或返回被选元素的属性值 data.messager
						$("#zc").attr("class","sig sig_out");
						$("#dl").attr("class","log log_in");
					}else{
						//注册失败
						$("#warning_1 span").text("用户名存在");
						$("#warning_1").show();
					}
					
				}else{
					//代码执行失败，给予错误提示
					alert(result.message);

				}
			
}
	);
}

//登陆
function login() {
	var userName=$("#count").val();
	var password=$("#password").val();
	
	if(!userName){
		alert("用户名不能为空");
	}
	if(!password){
		alert("密码不能为空");
	}
	
	$.post("/CLOUDNOTE/login/login.do",{"username":userName,
		"password":password},function(result){
		if(result.success){
			
			var map=result.data;
			if(map.flag==0){
				location.href="edit.html";
				addCookie("userId",map.userId,10);
				addCookie("userName",map.userName,10);
				
			}else if(map.flag==1){
				alert("用户名错误");
			}else if(map.flag==2){
				alert("密码错误");
			}
		}else{
			alert(result.messager);
		}
		
	});
}

/**
 * 退出登录
 */
function logout(){
	//注销session发送异步请求
	$.post(
			basePath+"/login/logout.do",{},function(result){
		if(result.success){
			location.href="login.html";
		}else{
			alert(result.messager);
		}
	});
}

/**
 * 修改密码
 */
function changepwd(){
	var oldpassword=$("#last_password").val();
	var newpassword=$("#new_password").val();
	var fnewpassword=$("#final_password").val();
	var username=getCookie("userName");
	if(oldpassword.length==0){
		$("#warning_1 span").text("原密码格式错误");
		$("#warning_1").show();
		return;
	}else{
		$("#warning_1").hide();
	}
	if(newpassword.length==0||newpassword.length>10){
	
	$("#warning_2 span").text("新密码输入错误");
	$("#warning_2").show();
	return;
	}else{
		$("#warning_2").hide();
	}
	if(newpassword!=fnewpassword){
		$("#warning_3 span").text("密码输入不一致");
		$("#warning_3").show();
		return;
	}else{
		$("#warning_3").hide();
	}
	
	$.post(basePath+"/login/update.do",{"username":username,
		"newpassword":newpassword,"fnewpassword":fnewpassword,"oldpassword":oldpassword},
		function(result){
			
			if(result.success){
				
				var map=result.data;
				alert(map.msg);
				
				if(map.msg){
					alert("更新成功");
					$("#warning_1").hide();
					location.href="login.html";
					return;
				}else{
					alert("更新失败");
					$("#warning_1 span").text("原密码错误");
					$("#warning_1").show();
				}
				
				
			}else{
				alert(result.messager);
			}
			}
		
	
	);
}


