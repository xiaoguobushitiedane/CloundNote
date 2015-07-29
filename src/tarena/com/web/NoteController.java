package tarena.com.web;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import tarena.com.entity.Note;
import tarena.com.entity.NoteBook1;
import tarena.com.entity.Result;
import tarena.com.entity.ShareNote;
import tarena.com.entity.User;
import tarena.com.service.NoteService;


@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource
	private NoteService noteservice;
	
	/**
	 * 添加笔记
	 * @param notetitle
	 * @param notebookid
	 * @param session
	 * @return
	 */
	@RequestMapping("/addnote.do")
	@ResponseBody
	public Result Addnote(String notetitle,String notebookid,HttpSession session){
		User user=(User)session.getAttribute("user");
		
		noteservice.addnote(notetitle, notebookid, user.getCn_user_id());
		return new Result();
		
	}
	/**
	 * 根据笔记本查询笔记(包括回收站)
	 * @param notebookid
	 * @return
	 */
	@RequestMapping("/findnote.do")
	@ResponseBody
	public Result findnote(String notebookid){
		
		List<Note> note=noteservice.findnote(notebookid);
		
		for(Note n:note){
			//System.out.println(n);
		}
		
		return new Result(note);
		
	}
	/**
	 * 删除笔记
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Result deletenote(String noteid,HttpSession session,String reid){
		User user=(User)session.getAttribute("user");
		noteservice.deletenote(noteid, user.getCn_user_id(),reid);
		
		return new Result();
		
	}
	
	
	//告诉SpringMVC，在请求开始时先调用
	//当前的方法，然后再调用Controller处理
	//请求的方法。
	@InitBinder
	public void initBinder(
			WebDataBinder binder, WebRequest request) {
		
		//将Timestamp类型的转换器注册为
		//TimestampEditor，之后SpringMVC在
		//处理Timestamp类型时会调用TimestampEditor
		//来进行类型转换
//		binder.registerCustomEditor(
//				Timestamp.class, new TimestampEditor());
	}
	@RequestMapping("/movenote.do")
	@ResponseBody
	public Result movenote(String notebookid,String noteid,HttpSession session){
		User user=(User)session.getAttribute("user");
		
		noteservice.movenote(notebookid, noteid, user.getCn_user_id());
		
		
		return new Result();
		
	}
	/**
	 * 删除回收站笔记
	 */
	@RequestMapping("/deletechedi.do")
	@ResponseBody
	public Result deletechedi(String noteid){
		noteservice.deletenotechedi(noteid);
		
		
		
		return new Result();
		
	}
	
	/**
	 * 编辑笔记
	 */
	@RequestMapping("/updatanormal.do")
	@ResponseBody
	public Result updatenormalnote(String title,String body,String noteid,String notebookid,HttpSession session){
		
		User user=(User)session.getAttribute("user");
		noteservice.updatenormalnote(title, body, noteid, notebookid, user.getCn_user_id());
		
		
		return new Result();
		
	}
	
	/**
	 * 返回单一笔记的数据
	 */
	@RequestMapping("/notebody.do")
	@ResponseBody
	public Result findone(String noteid){
		
		Note note=noteservice.findone(noteid);
		
		return new Result(note);
		
	}
	/**
	 * 添加收藏笔记
	 * @return
	 */
	@RequestMapping("/addfanote.do")
	@ResponseBody
	public Result addfanote(String snoteid,String fanotebookid,HttpSession session){
		
		User user=(User)session.getAttribute("user");
		noteservice.addfavoritenote(snoteid, fanotebookid,user.getCn_user_id());
		
		
		return new Result();
		
	}
	/**
	 * 查询参加活动的笔记
	 */
	@RequestMapping("/findacnote.do")
	@ResponseBody
	public Result findAcNote(){
		List<Note> list=noteservice.findAcNote();
		
		
		return new Result(list);
		
	}
}
