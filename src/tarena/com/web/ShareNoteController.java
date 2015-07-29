package tarena.com.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tarena.com.entity.Note;
import tarena.com.entity.Result;
import tarena.com.entity.ShareNote;
import tarena.com.service.ShareNoteService;

@Controller
@RequestMapping("/share")
public class ShareNoteController {

	@Resource
	private ShareNoteService sharenoteservice;
	
	/**
	 * 添加分享笔记
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Result addshare(String noteid){
		
		sharenoteservice.addsharenote(noteid);
		
		return new Result();
	
	}
	/**
	 * 查询所有分享笔记
	 * @param title
	 * @return
	 */
	@RequestMapping("/findall.do")
	@ResponseBody
	public Result findAll(String title,int currentPage){
		List<ShareNote> list=sharenoteservice.findAll(title,currentPage);
		
		return new Result(list);
		
	}
	
	
	
	
	
	
}
