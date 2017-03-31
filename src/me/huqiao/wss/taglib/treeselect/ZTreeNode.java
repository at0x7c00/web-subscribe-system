package me.huqiao.wss.taglib.treeselect;


/**
 * 树状选择控件节点
 * @author huqiao
 */
public interface ZTreeNode {

	/**
	 * 获取父节点
	 * @return 父节点对象，返回空时表示无父节点
	 */
	public ZTreeNode getZTreeNodeParent();
	
	/**
	 * 获取显示名称
	 * @return String 名称
	 */
	public String getZTreeNodeName();
	
	/**
	 * 获取实际的id值
	 * @return String id值
	 */
	public String getZTreeNodeId();
	
}
