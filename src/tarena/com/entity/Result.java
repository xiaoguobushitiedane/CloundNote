package tarena.com.entity;

import java.io.Serializable;
/**
 * Controller方法向页面返回数据的实体
 * 该实体对返回页面的数据做了一个明确的规范和封装
 *对于程序执行是否报错的判断,不需要在每一个Controller方法里做,这样比较麻烦.
 *最后使用AOP来统一处理
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -3803342915750511246L;

	//程序执行是否成功
	private boolean success=true;
	//失败时给予提示信息
	private String messager;
	//用来封装返回给页面的业务数据
	private Object data;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessager() {
		return messager;
	}
	public void setMessager(String messager) {
		this.messager = messager;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 
	 */
	public Result() {
		super();
		
	}
	/**
	 * @param data
	 */
	public Result(Object data) {
		super();
		this.data = data;
		
	}
	/**
	 * @param success
	 * @param messager
	 * @param data
	 */
	public Result(boolean success, String messager, Object data) {
		super();
		this.success = success;
		this.messager = messager;
		this.data = data;
		
	}
	
}
