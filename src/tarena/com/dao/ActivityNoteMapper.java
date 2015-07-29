package tarena.com.dao;

import java.util.List;
import java.util.Map;

import tarena.com.entity.Activity;
import tarena.com.entity.ActivityNote;
import tarena.com.entity.Note;

@Mapperluo
public interface ActivityNoteMapper {

	/*
	 * 查询活动笔记本里的活动笔记
	 */
	List<ActivityNote> findAllnote(Map<String,Object>pagram);
	/**
	 * 添加活动笔记
	 */
	void addAcNote(ActivityNote note);
	
	//查找活动笔记(单一)
	ActivityNote findoneAcNote(String noteid);
	//更新活动笔记
	void updateACnote(ActivityNote acnoteid);
	
}
