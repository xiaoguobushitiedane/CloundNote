package tarena.com.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tarena.com.dao.NoteMapper;
import tarena.com.dao.ShareMapper;
import tarena.com.entity.Note;
import tarena.com.entity.ShareNote;
import tarena.com.util.SystemConstant;
@Service
public class ShareNoteService implements SystemConstant{

	@Resource
	private ShareMapper sharemapper;
	@Resource
	private NoteMapper noteservice;
	/**
	 * 新增分享笔记
	 */
	public void addsharenote(String noteid){
		if(noteid==null){
			throw new RuntimeException("笔记id为空");
		}
		//通过Id查找普通笔记
		Note note=noteservice.findonenote(noteid);
		//将普通笔记的内容复制到分享笔记中
		ShareNote shnote=new ShareNote();
		shnote.setCn_note_id(note.getCn_note_id());
		shnote.setCn_share_body(note.getCn_note_body());
		shnote.setCn_share_id(UUID.randomUUID().toString());
		shnote.setCn_share_title(note.getCn_note_title());
		//查找分享笔记
		ShareNote list=sharemapper.findone(noteid);
		//System.out.println(list);
		//判断表中是否存在分享笔记如果有则更新该笔记,如果没有则新建分享笔记
		if(list==null){
			sharemapper.addSharenote(shnote);
		}else{
			sharemapper.updatefacnote(shnote);
		}
		
	}
	/**
	 * 查询所有同名的分享笔记
	 */
	public List findAll(String title,int currentPage){
		if(title==null){
			throw new RuntimeException("分享笔记名为空");
		}
		if(currentPage==0){
			throw new RuntimeException("当前页为空");
		}
		int begin=(currentPage-1)*PAGESIZE+1;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("title", title);
		map.put("begin", begin);
		map.put("pagesize", PAGESIZE);
		
		
		List<ShareNote> list=sharemapper.findAll(map);
		
		return list;
		
	}
	
	
	
	
	
}
