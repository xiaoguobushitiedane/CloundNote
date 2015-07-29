package tarena.com.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tarena.com.dao.NoteMapper;
import tarena.com.dao.ShareMapper;
import tarena.com.entity.Note;
import tarena.com.entity.NoteBook1;
import tarena.com.entity.ShareNote;
@Service
public class NoteService implements Serializable{

	@Resource
	private NoteMapper noteservice;
	@Resource
	private ShareMapper sharemapper;
	/**
	 * 添加笔记
	 * @param notetitle
	 * @param notebookid
	 * @param userid
	 */
	public void addnote(String notetitle,String notebookid,String userid){
		if(notetitle==null){
			throw new RuntimeException("笔记名为空");
			
		}
		if(notebookid==null){
			throw new RuntimeException("笔记id为空");
		}
		if(userid==null){
			throw new RuntimeException("笔记中用户id为空");
		}
			Note note=new Note();
			Date date=new Date();
			long time=date.getTime();
			
			note.setCn_note_create_time(time);
			note.setCn_note_id(UUID.randomUUID().toString());
			note.setCn_note_title(notetitle);
			note.setCn_notebook_id(notebookid);
			note.setCn_user_id(userid);
			note.setCn_note_type_id("5");
			noteservice.addnote(note);
	}
	/**
	 * 根据笔记本查询笔记
	 * @param notebookid
	 * @return
	 */
	public List findnote(String notebookid){
		if(notebookid==null){
			throw new RuntimeException("笔记本id为空");
		}
		List<Note> list=noteservice.findnote(notebookid);
		
		return list;
		
	}
	
	public Note deletenote(String noteid,String userid,String reid){
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		if(userid==null){
			throw new RuntimeException("用户名id为空");
		}
		if(reid==null){
			throw new RuntimeException("笔记本id为空");
		}
		Note note=noteservice.findonenote(noteid);
		System.out.println(note);
		
		note.setCn_user_id(userid);
		note.setCn_note_title(note.getCn_note_title());
		System.out.println(reid);
		note.setCn_notebook_id(reid);
		note.setCn_note_type_id("2");
		note.setCn_note_id(note.getCn_note_id());
		note.setCn_note_body(note.getCn_note_body());
		note.setCn_note_status_id(note.getCn_note_status_id());
		Date date=new Date();
		long time=date.getTime();
		note.setCn_note_last_modify_time(time);
		
		noteservice.updatenote(note);
		
		return note;
		
	}
/**
 * 移动笔记
 */
	public void movenote(String notebookid,String noteid,String userid){
		if(notebookid==null){
			throw new RuntimeException("笔记本id为空");
		}
		
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		if(userid==null){
			throw new RuntimeException("用户名id为空");
		}
		Note note=noteservice.findonenote(noteid);
		
		note.setCn_note_id(noteid);
		note.setCn_note_title(note.getCn_note_title());
		note.setCn_note_type_id(note.getCn_note_type_id());
		note.setCn_notebook_id(notebookid);
		note.setCn_user_id(userid);
		note.setCn_note_status_id(note.getCn_note_status_id());
		Date date=new Date();
		long time=date.getTime();
		note.setCn_note_last_modify_time(time);
		note.setCn_note_body(note.getCn_note_body());
		
		noteservice.updatenote(note);
	}
	/**
	 * 删除回收站笔记
	 */
	public void deletenotechedi(String noteid){
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		
		noteservice.deletenote(noteid);
		
		
	}
	/**
	 * 编辑笔记(2.0版本可以和move合并一个update,参数传递一个note对象,在前段传note时,不要写"note":,直接传Note对象)
	 */
	public void updatenormalnote(String title,String body,String noteid,String notebookid,String userid){
		if(title==null){
			throw new RuntimeException("笔记名为空");
		}
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		if(notebookid==null){
			throw new RuntimeException("笔记i本d为空");
		}
		if(userid==null){
			throw new RuntimeException("用户名为空");
		}
		Note note=noteservice.findonenote(noteid);
		note.setCn_note_body(body);
		note.setCn_note_id(noteid);
		Date date=new Date();
		long time=date.getTime();
		note.setCn_note_last_modify_time(time);
		note.setCn_note_title(title);
		note.setCn_note_type_id(note.getCn_note_type_id());
		note.setCn_notebook_id(notebookid);
		note.setCn_user_id(userid);
		note.setCn_note_status_id(note.getCn_note_status_id());
		noteservice.updatenote(note);
	}
	/**
	 * 查询单一笔记
	 */
	
	public Note findone(String noteid){
		
		Note note=noteservice.findonenote(noteid);
		
		return note;
		
	}
	
	/**
	 * 收藏笔记
	 */
	public void addfavoritenote(String snoteid,String fanotebookid,String userid){
		if(snoteid==null){
			throw new RuntimeException("分享笔记为空");
		}
		if(fanotebookid==null){
			throw new RuntimeException("收藏笔记本为空");
		}
		if(userid==null){
			throw new RuntimeException("用户名为空");
		}
		 //通过noteid查找分享库里的分享笔记
		 ShareNote snote=sharemapper.findone(snoteid);
		 //通过noteid查找普通笔记表中的表
		 Note note2=noteservice.findonenote(snoteid);
		
		
		Note note=new Note();
		note.setCn_note_body(snote.getCn_share_body());
		Date date=new Date();
		long time=date.getTime();
		
		note.setCn_note_create_time(time);
		note.setCn_note_id(snoteid);
		note.setCn_note_last_modify_time(time);
		//note.setCn_note_status_id(null);
		note.setCn_note_title(snote.getCn_share_title());
		//note.setCn_note_type_id(null);
		note.setCn_notebook_id(fanotebookid);
		note.setCn_user_id(userid);
		
		/**
		 * 所收藏的笔记如果普通笔记表中有就更新改笔记,如果没有就转换为收藏笔记()
		 */
		if(note2==null){
			noteservice.addnote(note);
		}else{
			noteservice.updatenote(note);
		}
	}
	/**
	 * 查询参加活动的笔记
	 * @return
	 */
	
	public List findAcNote(){
		
		List<Note>list=noteservice.findacnote("3");
		
		return list;
		
	}
}
