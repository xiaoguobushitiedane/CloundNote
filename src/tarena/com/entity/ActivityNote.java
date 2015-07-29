package tarena.com.entity;

import java.io.Serializable;

public class ActivityNote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4097785146785627389L;

	private String cn_note_activity_id;
	private String cn_activity_id;
	private String cn_note_id;
	private int cn_note_activity_up;
	private int cn_note_activity_down;
	private String cn_note_activity_title;
	private String cn_note_activity_body;
	public String getCn_note_activity_id() {
		return cn_note_activity_id;
	}
	public void setCn_note_activity_id(String cn_note_activity_id) {
		this.cn_note_activity_id = cn_note_activity_id;
	}
	public String getCn_activity_id() {
		return cn_activity_id;
	}
	public void setCn_activity_id(String cn_activity_id) {
		this.cn_activity_id = cn_activity_id;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	public int getCn_note_activity_up() {
		return cn_note_activity_up;
	}
	public void setCn_note_activity_up(int cn_note_activity_up) {
		this.cn_note_activity_up = cn_note_activity_up;
	}
	public int getCn_note_activity_down() {
		return cn_note_activity_down;
	}
	public void setCn_note_activity_down(int cn_note_activity_down) {
		this.cn_note_activity_down = cn_note_activity_down;
	}
	public String getCn_note_activity_title() {
		return cn_note_activity_title;
	}
	public void setCn_note_activity_title(String cn_note_activity_title) {
		this.cn_note_activity_title = cn_note_activity_title;
	}
	public String getCn_note_activity_body() {
		return cn_note_activity_body;
	}
	public void setCn_note_activity_body(String cn_note_activity_body) {
		this.cn_note_activity_body = cn_note_activity_body;
	}
	/**
	 * @param cn_note_activity_id
	 * @param cn_activity_id
	 * @param cn_note_id
	 * @param cn_note_activity_up
	 * @param cn_note_activity_down
	 * @param cn_note_activity_title
	 * @param cn_note_activity_body
	 */
	public ActivityNote(String cn_note_activity_id, String cn_activity_id,
			String cn_note_id, int cn_note_activity_up,
			int cn_note_activity_down, String cn_note_activity_title,
			String cn_note_activity_body) {
		super();
		this.cn_note_activity_id = cn_note_activity_id;
		this.cn_activity_id = cn_activity_id;
		this.cn_note_id = cn_note_id;
		this.cn_note_activity_up = cn_note_activity_up;
		this.cn_note_activity_down = cn_note_activity_down;
		this.cn_note_activity_title = cn_note_activity_title;
		this.cn_note_activity_body = cn_note_activity_body;
	}
	/**
	 * 
	 */
	public ActivityNote() {
		super();
	}
	@Override
	public String toString() {
		return "ActivityNote [cn_note_activity_id=" + cn_note_activity_id
				+ ", cn_activity_id=" + cn_activity_id + ", cn_note_id="
				+ cn_note_id + ", cn_note_activity_up=" + cn_note_activity_up
				+ ", cn_note_activity_down=" + cn_note_activity_down
				+ ", cn_note_activity_title=" + cn_note_activity_title
				+ ", cn_note_activity_body=" + cn_note_activity_body + "]";
	}
	
	
}
