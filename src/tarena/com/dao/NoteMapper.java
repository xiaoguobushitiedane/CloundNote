package tarena.com.dao;

import java.util.List;

import tarena.com.entity.Note;

@Mapperluo
public interface NoteMapper {

	//添加一个笔记
	void addnote(Note note);
	//根据查询笔记本id查询一个笔记本的所有笔记
	List<Note> findnote(String notebookid);
	//更新笔记
	void updatenote(Note note);
	//根据笔记的ID查询一个笔记
	Note findonenote(String noteid);
	//删除回收站笔记
	void deletenote(String noteid);
	//查询参加活动的笔记
	List<Note>findacnote(String status);
}
