package tarena.com.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tarena.com.entity.Activity;
import tarena.com.entity.ActivityNote;
import tarena.com.entity.Result;
import tarena.com.entity.User;
import tarena.com.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Resource
	private ActivityService activityservice;
	
	/**
	 * 查询所有活动
	 */
	@RequestMapping("/findall.do")
	@ResponseBody
	public Result findallac(){
		List<Activity>list=activityservice.FindAllAc();	
		
		
		return new Result(list);
		
	}
	
	@RequestMapping("/findallacbook.do")
	@ResponseBody
	public Result findallacnote(String acnotebookid,int fenye){
		List <ActivityNote>list2=activityservice.FindAllAcNote(acnotebookid,fenye);
		
		
		return new Result(list2);
		
	}
	/**
	 * 添加活动笔记
	 */
	@RequestMapping("/addacnote.do")
	@ResponseBody
	public Result addActivitynote(String acnoteid,String acbookid){
//		System.out.println(acbookid+"9999999999");
//		System.out.println(acnoteid+"8888888888");
		activityservice.AddActivityNote(acnoteid, acbookid);
		
		
		return new Result();
		
	}
	/**
	 * 收藏活动笔记
	 * @return
	 */
	@RequestMapping("/shoucangAC.do")
	@ResponseBody
	public Result ShouCangNote(String acnoteid,HttpSession session){
		User user=(User)session.getAttribute("user");
		activityservice.ShoucangAc(acnoteid, user.getCn_user_id());
//		System.out.println(acnoteid);
//		System.out.println(user.getCn_user_id());
		
		
		return new Result();
		
	}
	@RequestMapping("/updateac.do")
	@ResponseBody
	public Result UpdateAcNote(String acnoteid,int updown, String biaoji){
		activityservice.UpdateAcNote(acnoteid, updown, biaoji);
		
		
		return new Result();
		
	}
}
