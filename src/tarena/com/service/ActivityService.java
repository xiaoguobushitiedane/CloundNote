package tarena.com.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tarena.com.dao.ActivityMapper;
import tarena.com.dao.ActivityNoteMapper;
import tarena.com.dao.NoteBookMapper;
import tarena.com.dao.NoteMapper;
import tarena.com.entity.ActivityNote;
import tarena.com.entity.Note;
import tarena.com.entity.NoteBook1;
import tarena.com.util.SystemConstant;
@Service
public class ActivityService implements Serializable,SystemConstant{
	
	@Resource
	private ActivityMapper activitymapper;
	
	@Resource
	private ActivityNoteMapper activitynotemapper;
	@Resource
	private NoteMapper notemapper;
	@Resource
	private NoteBookMapper notenookmapper;
	
	
	
	//查询所有活动笔记本
	public List FindAllAc(){
		
		List list=activitymapper.FindAll();
		
		return list;
		
	}
	
	
	//查询对用活动笔记本的说有笔记
	public List FindAllAcNote(String acnotebookid,int fenye){
		if(acnotebookid==null){
			throw new RuntimeException();
		}
		if(fenye==0){
			throw new RuntimeException("当前页为空");
		}
		int begin=(fenye-1)*PAGESIZE+1;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("acnotebookid", acnotebookid);
		map.put("begin", begin);
		map.put("pagesize", PAGESIZE);
		
		
		
		List<ActivityNote>list=activitynotemapper.findAllnote(map);
		
		return list;
			
	}
	//添加参加活动笔记
	public void AddActivityNote(String noteid,String acbookid){
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		if(acbookid==null){
			throw new RuntimeException("活动id为空");
		}
		
		Note note=notemapper.findonenote(noteid);
		
		ActivityNote acnote=new ActivityNote();
		
		acnote.setCn_activity_id(acbookid);
		acnote.setCn_note_activity_body(note.getCn_note_body());
		acnote.setCn_note_activity_down(0);
		acnote.setCn_note_activity_id(UUID.randomUUID().toString());
		acnote.setCn_note_activity_title(note.getCn_note_title());
		acnote.setCn_note_activity_up(0);
		acnote.setCn_note_id(noteid);
		
		activitynotemapper.addAcNote(acnote);
		
		note.setCn_note_body(note.getCn_note_body());
		note.setCn_note_create_time(note.getCn_note_create_time());
		note.setCn_note_id(note.getCn_note_id());
		note.setCn_note_last_modify_time(note.getCn_note_last_modify_time());
		note.setCn_note_status_id("3");
		note.setCn_note_title(note.getCn_note_title());
		note.setCn_note_type_id(note.getCn_note_type_id());
		note.setCn_notebook_id(note.getCn_notebook_id());
		note.setCn_user_id(note.getCn_user_id());
		
		notemapper.updatenote(note);
	}
	/**
	 * 活动笔记变为收藏笔记
	 * @param noteid
	 * @param Bookid
	 */
	public void ShoucangAc(String acnoteid, String userid){
			if(acnoteid==null){
				throw new RuntimeException("笔记Id为空");
			}
			if(userid==null){
				throw new RuntimeException("用户名Id为空");
			}
			String SC = null;
			ActivityNote acnote=activitynotemapper.findoneAcNote(acnoteid);
			
			List <NoteBook1>list=notenookmapper.findSpecialNoteBook(userid);
			//System.out.println(list);
			
		for(NoteBook1 nb1:list){
			String num=nb1.getcn_notebook_type_id();
			//System.out.println(num+"5656565656");
			if(num.equals("1")){
				SC=nb1.getcn_notebook_id();
			}
		}
			
		//System.out.println(SC+"   78787878787878");
			
			
			
			
			Note note =new Note();
			note.setCn_note_body(acnote.getCn_note_activity_body());
			Date date=new Date();
			long time=date.getTime();
			note.setCn_note_create_time(time);
			note.setCn_note_id(acnoteid);
			note.setCn_note_last_modify_time(time);
			note.setCn_note_status_id(acnote.getCn_note_activity_id());
			note.setCn_note_title(acnote.getCn_note_activity_title());
			note.setCn_note_type_id(null);
			note.setCn_notebook_id(SC);
			note.setCn_user_id(userid);
			notemapper.addnote(note);
		
			//System.out.println(note);
			
	}
	//更新活动笔记
	public void UpdateAcNote(String acnoteid,int updown,String biaoji){
		
		if(acnoteid==null){
			throw new RuntimeException("笔记id为空");
		}
		
		if(biaoji==null){
			throw new RuntimeException("标记为空");
		}
		ActivityNote activitynote=activitynotemapper.findoneAcNote(acnoteid);
		
		if("up".equals(biaoji)){
			
		activitynote.setCn_activity_id(activitynote.getCn_activity_id());
		activitynote.setCn_note_activity_body(activitynote.getCn_note_activity_body());
		activitynote.setCn_note_activity_down(activitynote.getCn_note_activity_down());
		activitynote.setCn_note_activity_id(acnoteid);
		activitynote.setCn_note_activity_title(activitynote.getCn_note_activity_title());
		activitynote.setCn_note_activity_up(updown);
		activitynote.setCn_note_id(activitynote.getCn_note_id());
		
		}else if("down".equals(biaoji)){
			activitynote.setCn_activity_id(activitynote.getCn_activity_id());
			activitynote.setCn_note_activity_body(activitynote.getCn_note_activity_body());
			activitynote.setCn_note_activity_down(updown);
			activitynote.setCn_note_activity_id(acnoteid);
			activitynote.setCn_note_activity_title(activitynote.getCn_note_activity_title());
			activitynote.setCn_note_activity_up(activitynote.getCn_note_activity_up());
			activitynote.setCn_note_id(activitynote.getCn_note_id());
			
		}
		
		activitynotemapper.updateACnote(activitynote);
		
		
	}
	
	
	
}
