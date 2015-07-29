package tarena.com.dao;

import java.util.List;
import java.util.Map;

import tarena.com.entity.ShareNote;

@Mapperluo
public interface ShareMapper {
	/**
	 * 添加分享笔记
	 * @param note
	 */
	void addSharenote(ShareNote note);
	/**
	 * 查询说有分享笔记
	 */
	List<ShareNote> findAll(Map<String,Object>pgram);
	/**
	 * 查询单一分享笔记
	 */
	ShareNote findone(String noteid);
	/**
	 * 更新分享笔记
	 */
	void updatefacnote(ShareNote note);
}
