package tarena.com.dao;

import java.util.List;

import tarena.com.entity.NoteBook1;

@Mapperluo
public interface NoteBookMapper {
	/**
	 * 添加笔记本
	 * @param notebook
	 */

	void saveNote(NoteBook1 notebook);
	/**
	 * 查询某用户所有的笔记本
	 * @param userId
	 * @return
	 */
	List<NoteBook1> findNoteBook(String userid);
	/**
	 * 加载特殊笔记本
	 * @param userId
	 * @return
	 */
	List<NoteBook1> findSpecialNoteBook(String userId);
	/**
	 * 重命名笔记本
	 */
	void updatename(NoteBook1 notebook);
	/**
	 * 彻底删除笔记本
	 */
	void deletenotebook(NoteBook1 notebook);
	
	
}
