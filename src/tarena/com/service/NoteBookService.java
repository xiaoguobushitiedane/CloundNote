package tarena.com.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import tarena.com.dao.NoteBookMapper;
import tarena.com.entity.NoteBook1;
@Service
public class NoteBookService {

	private Logger logger=Logger.getLogger(NoteBookService.class);
	
	@Resource
	private NoteBookMapper notebook;
	
	/**
	 * 查询某用户的所有普通笔记
	 * @param userId
	 * @return
	 */
	public List<NoteBook1>findNormalNoteBook(String userId){
		logger.info("into fingNormal");
		if(userId==null){
			throw new RuntimeException("参数为空");
		}
		
		List <NoteBook1> list=notebook.findNoteBook(userId);
		logger.info(list==null?"":list.size());
		logger.info("into fingNormal");
		return list;
	}
	/**
	 * 查询用户特殊笔记本
	 */
	public List<NoteBook1>FindSpecialNoteBook(String userid){
		
		logger.info("into fingspecial");
		if(userid==null){
			throw new RuntimeException("参数为空");
		}
		
		List <NoteBook1> list=notebook.findSpecialNoteBook(userid);
		logger.info(list==null?"":list.size());
		logger.info("into fingspecial");
		return list;
		
	}
	/**
	 * 添加笔记本
	 */
	
	public NoteBook1 AddNoteBookdd(String notebookname,String notebooktypeid,String userid){
		if(notebookname==null){
			throw new RuntimeException("书名不能为空");
		}
		if(notebooktypeid==null){
			throw new RuntimeException("书类型不能为空");
		}
		if(userid==null){
			throw new RuntimeException("用户id不能为空");
		}
		
		NoteBook1 nb=new NoteBook1();
		nb.setcn_notebook_id(UUID.randomUUID().toString());
		nb.setcn_notebook_name(notebookname);
		nb.setcn_notebook_type_id(notebooktypeid);
		nb.setcn_user_id(userid);
		
		notebook.saveNote(nb);
		
		return nb;
	
	}
	
	/**
	 * 重命名笔记本
	 */
	public NoteBook1 updateNoteBook(String newnotebookname,String notebookid){
		if(newnotebookname==null){
			throw new RuntimeException("更新书名为空");
		}
		if(notebookid==null){
			throw new RuntimeException("书名id为空");
		}
		
		NoteBook1 nb=new NoteBook1();
		nb.setcn_notebook_name(newnotebookname);
		nb.setcn_notebook_id(notebookid);
		
		notebook.updatename(nb);
		return nb;
	}
/**
 * 彻底删除笔记本	
 */
public boolean deletebook(String notebookid){
	if(notebookid==null){
		throw new RuntimeException("删除本id错误");
	}
	NoteBook1 nob=new NoteBook1();
	nob.setcn_notebook_id(notebookid);
	
	notebook.deletenotebook(nob);
	
	return true;
	
}
	
	
	
	
	
}
