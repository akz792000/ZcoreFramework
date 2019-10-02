/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import java.util.ArrayList;
import java.util.List;

import org.zcoreframework.component.tree.Tree.RelType;

public class TreeNodeModel {
			
	private Long id;
	
	private String name;
	
	private Long parent;
	
	private Long seq;
	
	private Long child = 0L;
	
	private RelType relType;
		
	private List<TreeNodeModel> nodes = new ArrayList<TreeNodeModel>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public Long getChild() {
		return child;
	}
	
	public void setChild(Long child) {
		this.child = child;
	}
	
	public RelType getRelType() {
		return relType;
	}
	
	public void setRelType(RelType relType) {
		this.relType = relType;
	}
	
	public List<TreeNodeModel> getNodes() {
		return nodes;
	}

	public void setModel(List<TreeNodeModel> nodes) {
		this.nodes = nodes;
	}

	public TreeNodeModel() {}
	
	public void assign(TreeNodeModel treeNodeModel) {
		id = treeNodeModel.getId();
		name = treeNodeModel.getName();
		parent = treeNodeModel.getParent();
		seq = treeNodeModel.getSeq();
		child = treeNodeModel.getChild();
		nodes = new ArrayList<TreeNodeModel>();
	}
	
	public TreeNodeModel(TreeNodeModel treeNodeModel) {
		assign(treeNodeModel);
	}
	
	public TreeNodeModel(Long id, String name, Long parent, Long seq, Long child) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.seq = seq;
		this.child = child;
	}	
	
	public TreeNodeModel(Long id, String name, Long parent, Long seq, Long child, RelType relType) {
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.seq = seq;
		this.child = child;
		this.relType = relType;
	}	
	
	public TreeNodeModel getParentMenu(Long parent) {
		if (getId() == parent) 
			return this;
		for (TreeNodeModel item : getNodes()) {
			TreeNodeModel nodeModel = item.getParentMenu(parent);
			if (nodeModel != null)
				return nodeModel;
		}
		return null;
	}	
	
}
