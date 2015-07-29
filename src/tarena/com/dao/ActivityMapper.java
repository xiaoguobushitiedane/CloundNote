package tarena.com.dao;

import java.util.List;
import java.util.Map;

import tarena.com.entity.Activity;

@Mapperluo
public interface ActivityMapper {

	/*
	 * 查询活动列表
	 */
	List<Activity> FindAll();
	
}
