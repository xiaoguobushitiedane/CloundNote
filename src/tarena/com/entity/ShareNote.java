package tarena.com.entity;

import java.io.Serializable;

public class ShareNote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5547205996230071999L;

	private String cn_share_id;
	private String cn_share_title;
	private String cn_share_body;
	private String cn_note_id;
	
	
	public String getCn_share_id() {
		return cn_share_id;
	}
	public void setCn_share_id(String cn_share_id) {
		this.cn_share_id = cn_share_id;
	}
	public String getCn_share_title() {
		return cn_share_title;
	}
	public void setCn_share_title(String cn_share_title) {
		this.cn_share_title = cn_share_title;
	}
	public String getCn_share_body() {
		return cn_share_body;
	}
	public void setCn_share_body(String cn_share_body) {
		this.cn_share_body = cn_share_body;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	/**
	 * @param cn_share_id
	 * @param cn_share_title
	 * @param cn_share_body
	 * @param cn_note_id
	 */
	public ShareNote(String cn_share_id, String cn_share_title,
			String cn_share_body, String cn_note_id) {
		super();
		this.cn_share_id = cn_share_id;
		this.cn_share_title = cn_share_title;
		this.cn_share_body = cn_share_body;
		this.cn_note_id = cn_note_id;
	}
	/**
	 * 
	 */
	public ShareNote() {
		super();
	}
	@Override
	public String toString() {
		return "ShareNote [cn_share_id=" + cn_share_id + ", cn_share_title="
				+ cn_share_title + ", cn_share_body=" + cn_share_body
				+ ", cn_note_id=" + cn_note_id + "]";
	}
	
}
