/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.model;

public class AuthorityItemModel  {
	
	private long id;
	
	private String name;
	
	private long parent;
	
	private long seq;
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public AuthorityItemModel() {
	}
	
	public AuthorityItemModel(long id, String name, long parent, long seq) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.seq = seq;
	}
		
}
