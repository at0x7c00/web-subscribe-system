package me.huqiao.wss.taglib.treeselect;

import java.util.Collection;
/**
 * zTree数据构建器
 * @author NOVOTS
 * @version Version 1.0
 */
public class ZTreeDataCreator {
	/**
	 * 构建zTree及诶单数据
	 * @param data ZTreeNode集合
	 * @return zTree所用的JSON数据
	 */
	public static String createZTreeNodeData(Collection data){
		StringBuffer res = new StringBuffer();
		res.append("[");
		//res.append("{ id:0, pId:0, name:'清空', open:true},");
		for(Object obj : data){
			ZTreeNode node = (ZTreeNode) obj ;
			res.append("{id:'").append(node.getZTreeNodeId()).append("', pId:'").append(node.getZTreeNodeParent()==null ? "0" : node.getZTreeNodeParent().getZTreeNodeId()).append("', name:'").append(node.getZTreeNodeName()).append("',open:true},");
		}
		if(res.length()>1){
			res.delete(res.length()-1, res.length());
		}
		res.append("]");
		return res.toString();
	}
}
