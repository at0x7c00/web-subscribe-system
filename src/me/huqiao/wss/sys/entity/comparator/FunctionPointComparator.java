package me.huqiao.wss.sys.entity.comparator;

import java.util.Comparator;

import me.huqiao.wss.sys.entity.FunctionPoint;


/**
 * 权限菜单比较器 <br/>如果id相等，则认为菜单相等,否则，如果排序号相等，按照id进行比较排序，如果排序号不等，则按排序号比较排序
 * @author NOVOTS
 * @version Version 1.0
 */
public class FunctionPointComparator implements Comparator<FunctionPoint>{

	@Override
	public int compare(FunctionPoint p1, FunctionPoint p2) {
		if(p1.getId().intValue()==p2.getId()){
			return 0;
		}
		if(p1.getOrderNum().intValue()==p2.getOrderNum()){
			return p1.getId().intValue()-p2.getId();
		}
		return p1.getOrderNum()-p2.getOrderNum();
	}

}
