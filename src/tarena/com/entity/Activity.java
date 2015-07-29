package tarena.com.entity;

import java.io.Serializable;

public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 821320201575378203L;
	
	private String cn_activity_id;
	private String cn_activity_title;
	private String cn_activity_body;
	private String cn_activity_end_timme;
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_activity_title() {
		return cn_activity_title;
	}
	public void setCn_activity_title(String cn_activity_title) {
		this.cn_activity_title = cn_activity_title;
	}
	public String getCn_activity_body() {
		return cn_activity_body;
	}
	public void setCn_activity_body(String cn_activity_body) {
		this.cn_activity_body = cn_activity_body;
	}
	public String getCn_activity_end_timme() {
		return cn_activity_end_timme;
	}
	public void setCn_activity_end_timme(String cn_activity_end_timme) {
		this.cn_activity_end_timme = cn_activity_end_timme;
	}
	/**
	 * @param cn_activity_id
	 * @param cn_activity_title
	 * @param cn_activity_body
	 * @param cn_activity_end_timme
	 */
	public Activity(String cn_activity_id, String cn_activity_title,
			String cn_activity_body, String cn_activity_end_timme) {
		super();
		this.cn_activity_id = cn_activity_id;
		this.cn_activity_title = cn_activity_title;
		this.cn_activity_body = cn_activity_body;
		this.cn_activity_end_timme = cn_activity_end_timme;
	}
	/**
	 * 
	 */
	public Activity() {
		super();
	}
	@Override
	public String toString() {
		return "Activity [cn_activity_id=" + cn_activity_id
				+ ", cn_activity_title=" + cn_activity_title
				+ ", cn_activity_body=" + cn_activity_body
				+ ", cn_activity_end_timme=" + cn_activity_end_timme + "]";
	}
	
	
}
