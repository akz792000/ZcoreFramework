/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class TreeModel extends AbstractComponentImpl {
	
	private TreeNodeModel treeNodeModel = new TreeNodeModel();

	private TreeClientModel ClientModel = new TreeClientModel();
	
	public TreeNodeModel getTreeNodeModel() {
		return treeNodeModel;
	}

	public void setTreeNodeModel(TreeNodeModel treeNodeModel) {
		this.treeNodeModel = treeNodeModel;
	}

	public TreeClientModel getClientModel() {
		return ClientModel;
	}

	public void setClientModel(TreeClientModel clientModel) {
		ClientModel = clientModel;
	}
}
