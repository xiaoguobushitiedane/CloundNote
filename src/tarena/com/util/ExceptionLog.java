package tarena.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import tarena.com.entity.Result;
import tarena.com.entity.User;
@Component
@Aspect
public class ExceptionLog {

	@Resource
	private HttpServletRequest request;
	
	@Around("within(tarena.com.web.*)")
	public Object log(ProceedingJoinPoint p){
		Object result=null;
		
		try {
			result=p.proceed();
		} catch (Throwable e) {
			//记录日志
			logException(p, e);
			//发生异常时，返回错误信息
			result =new Result(false,"系统发生异常,请联系管理员.",null);
			return result;
		}
		
		return result;
		
	}
	
	
	private void logException(ProceedingJoinPoint p, Throwable e){
		Logger logger=Logger.getLogger(ExceptionLog.class);
		//获取用户名
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		String userName=user.getCn_user_name();
		//获取ip
		String ip=request.getRemoteHost();
		//获取当前系统时间
		String date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		///获取正在调用的目标组件方法名
		String methodName=p.getSignature().getName();
		//获取目标组件类名
		String className = p.getTarget().getClass().getName();
					

		//拼接提示信息
		StringBuffer sb=new StringBuffer();
		sb.append("用户[").append(userName).append("],IP[").append(ip).
		append("],在[").append(date).append("],调用[").append(className).
		append(".").append(methodName).append("]时,发生如下异常:");
		//拼接错误信息头
		logger.error(sb.toString());
		//拼接错误信息
		StackTraceElement[] elmets=e.getStackTrace();
		for(StackTraceElement els:elmets){
			logger.error("/t"+els.toString());
		}
		
	}
}
