package de.ipk_gatersleben.bit.bi.isa4j.components;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DataFile extends StudyOrAssayTableObject implements Commentable { 
	
	private CommentCollection comments = new CommentCollection();
	
	public CommentCollection comments() {
		return this.comments;
	}
	
	private String name;
	private String type;
	
	public DataFile(String type, String name) {
		this.setType(type);
		this.setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = Objects.requireNonNull(name, "Name cannot be null");
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = Objects.requireNonNull(type, "Type cannot be null");
	}
	
	public LinkedHashMap<String, String[]> getHeaders() {
		LinkedHashMap<String, String[]> headers = new LinkedHashMap<String, String[]>();
		
		headers.put(this.type, new String[]{this.type});
		headers.putAll(this.getHeadersForComments(this.comments));
		
		return headers;
	}
	
	
	public Map<String, String[]> getFields() {
		HashMap<String, String[]> fields = new HashMap<String, String[]>();
		
		fields.put(this.type, new String[]{this.name});
		fields.putAll(this.getFieldsForComments(this.comments));

		return fields;
	}

}
