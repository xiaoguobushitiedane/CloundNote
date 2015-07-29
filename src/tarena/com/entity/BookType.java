package tarena.com.entity;

import java.io.Serializable;

public class BookType implements Serializable {

	
	private static final long serialVersionUID = 1080986684528208433L;

private String notebook_type_id;
private String notebook_type_code;
private String notebook_type_name;
private String notebook_type_desc;
public String getNotebook_type_id() {
	return notebook_type_id;
}
public void setNotebook_type_id(String notebook_type_id) {
	this.notebook_type_id = notebook_type_id;
}
public String getNotebook_type_code() {
	return notebook_type_code;
}
public void setNotebook_type_code(String notebook_type_code) {
	this.notebook_type_code = notebook_type_code;
}
public String getNotebook_type_name() {
	return notebook_type_name;
}
public void setNotebook_type_name(String notebook_type_name) {
	this.notebook_type_name = notebook_type_name;
}
public String getNotebook_type_desc() {
	return notebook_type_desc;
}
public void setNotebook_type_desc(String notebook_type_desc) {
	this.notebook_type_desc = notebook_type_desc;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
/**
 * @param notebook_type_id
 * @param notebook_type_code
 * @param notebook_type_name
 * @param notebook_type_desc
 */
public BookType(String notebook_type_id, String notebook_type_code,
		String notebook_type_name, String notebook_type_desc) {
	super();
	this.notebook_type_id = notebook_type_id;
	this.notebook_type_code = notebook_type_code;
	this.notebook_type_name = notebook_type_name;
	this.notebook_type_desc = notebook_type_desc;
}
/**
 * 
 */
public BookType() {
	super();
}
@Override
public String toString() {
	return "NoteBook [notebook_type_id=" + notebook_type_id
			+ ", notebook_type_code=" + notebook_type_code
			+ ", notebook_type_name=" + notebook_type_name
			+ ", notebook_type_desc=" + notebook_type_desc + "]";
}

}
