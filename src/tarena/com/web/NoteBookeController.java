package tarena.com.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tarena.com.entity.NoteBook1;
import tarena.com.entity.Result;
import tarena.com.entity.User;
import tarena.com.service.NoteBookService;
import tarena.com.util.SystemConstant;

@Controller
@RequestMapping("/notebook")
public class NoteBookeController implements SystemConstant{

	@Resource
	private NoteBookService notebookService;
	
	
	
	@RequestMapping("/findNormal.do")
	@ResponseBody
	public Result findNormal( HttpSession session){
		
		User user=(User) session.getAttribute("user");
		//System.out.println(user);
		
		List<NoteBook1> list=notebookService.findNormalNoteBook(user.getCn_user_id());
		
		
		return new Result(list);
		
	}
	
	
	/**
	 * 查询特殊笔记本
	 */
	@RequestMapping("/specialnote.do")
	@ResponseBody
	public Result findspecial(HttpSession session){
		
		User user=(User) session.getAttribute("user");
		
		
		List<NoteBook1> list=notebookService.FindSpecialNoteBook(user.getCn_user_id());
		
		
		//System.out.println(list);
		
		return new Result(list);
		
	}
	/**
	 * 添加笔记本
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Result addnotebook(String notebookname,HttpSession session){
		
		User user=(User)session.getAttribute("user");
		String userid=user.getCn_user_id();
		NoteBook1 nb=notebookService.AddNoteBookdd(notebookname, NOMAL_TYPE_ID, userid);
		
		
		return new Result(nb);
		
	}
	/**
	 * 重命名笔记本
	 */
	@RequestMapping("/rename.do")
	@ResponseBody
	public Result renamebook(String newbookname,String bookid){
		
		NoteBook1 nb=notebookService.updateNoteBook(newbookname, bookid);
		
		return new Result(nb);
		
	}
	/**
	 * 彻底删除笔记本
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Result Deletebook(String bookid){
		
		boolean nb=notebookService.deletebook(bookid);
		
		return new Result(nb);
		
		
	}
	
	
}
