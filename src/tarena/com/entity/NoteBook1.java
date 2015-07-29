package tarena.com.entity;

import java.io.Serializable;

public class NoteBook1 implements Serializable {

	private static final long serialVersionUID = 1080986684528208433L;

	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private String cn_notebook_createtime;
	
	public String getcn_notebook_id() {
		return cn_notebook_id;
	}
	public void setcn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}
	public String getcn_user_id() {
		return cn_user_id;
	}
	public void setcn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getcn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setcn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	public String getcn_notebook_name() {
		return cn_notebook_name;
	}
	public void setcn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}
	public String getcn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setcn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}
	public String getcn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setcn_notebook_createtime(String cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @param cn_notebook_id
	 * @param cn_user_id
	 * @param cn_notebook_type_id
	 * @param cn_notebook_name
	 * @param cn_notebook_desc
	 * @param cn_notebook_createtime
	 */
	public NoteBook1(String cn_notebook_id, String cn_user_id,
			String cn_notebook_type_id, String cn_notebook_name,
			String cn_notebook_desc, String cn_notebook_createtime) {
		super();
		this.cn_notebook_id = cn_notebook_id;
		this.cn_user_id = cn_user_id;
		this.cn_notebook_type_id = cn_notebook_type_id;
		this.cn_notebook_name = cn_notebook_name;
		this.cn_notebook_desc = cn_notebook_desc;
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	/**
	 * 
	 */
	public NoteBook1() {
		super();
	}
	@Override
	public String toString() {
		return "NoteBook [cn_notebook_id=" + cn_notebook_id + ", cn_user_id=" + cn_user_id
				+ ", cn_notebook_type_id=" + cn_notebook_type_id + ", cn_notebook_name="
				+ cn_notebook_name + ", cn_notebook_desc=" + cn_notebook_desc
				+ ", cn_notebook_createtime=" + cn_notebook_createtime + "]";
	}
	
	
}
