package me.huqiao.wss.sys.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.sys.dao.IFunctionPointDao;
import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.Role;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.sys.entity.comparator.FunctionPointComparator;
import me.huqiao.wss.sys.service.IFunctionPointService;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;

/**
 * 权限Service实现
 * @author NOVOTS
 * @version Version
 */
@Service
public class FunctionPointServiceImpl extends BaseServiceImpl<FunctionPoint> implements IFunctionPointService {
	/** 权限Dao对象 */
	@Resource
	private IFunctionPointDao functionPointDao;

	@Override
	public Page<FunctionPoint> getPage(FunctionPoint functionPoint, Page pageInfo) {
		int countRecord = functionPointDao.findRowCount(functionPoint).intValue();
		Page<FunctionPoint> page = new Page<FunctionPoint>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<FunctionPoint> functionPointList = functionPointDao.findPage(functionPoint, page.getStartIndex(), page.getNumPerPage(), pageInfo.getOrderField(), pageInfo.getOrderDirection());
		page.setList(functionPointList);
		page.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc" : pageInfo.getOrderDirection());
		page.setOrderField(pageInfo.getOrderField() == null ? "id" : pageInfo.getOrderField());
		return page;
	}

	@Override
	public List<FunctionPoint> getTopLevelFunctionPoints() {
		return functionPointDao.getTopLevelFunctionPoints();
	}

	/**
	 * 遍历所有菜单信息，生成树状信息List
	 * 
	 * @param topDepartments
	 *            部门权限集合
	 * @param prefixCode
	 *            前缀
	 * @param filterId
	 *            过滤的ID
	 * @param result
	 *            结果集合
	 */
	public void tree(Collection<FunctionPoint> topDepartments, String prefixCode, List<FunctionPoint> result, Integer filterId) {
		if (topDepartments == null)
			return;
		int count = 0;
		for (FunctionPoint subDept : topDepartments) {
			count++;
			FunctionPoint temp_dep = new FunctionPoint();
			temp_dep.setId(subDept.getId());
			temp_dep.setIcon(subDept.getIcon());
			temp_dep.setParent(subDept.getParent());
			temp_dep.setUrl(subDept.getUrl());
			temp_dep.setOrderNum(subDept.getOrderNum());
			String prefix = parsePrefix(prefixCode);
			if (count == topDepartments.size()) {
				if (filterId == null || subDept.getId() != filterId) {
					temp_dep.setName(prefix + "┗━" + subDept.getName());
					result.add(temp_dep);
					tree(subDept.getChildren(), prefixCode + "0", result, filterId);
				}
			} else {
				if (filterId == null || subDept.getId() != filterId) {
					temp_dep.setName(prefix + "┣━" + subDept.getName());
					result.add(temp_dep);
					tree(subDept.getChildren(), prefixCode + "1", result, filterId);
				}
			}
		}
	}

	/**
	 * 解析前缀，生成复制FunctionPoint的名称的前缀
	 * 
	 * @param prefixCode
	 *            要解析对象
	 * @return String 生成复制FunctionPoint的名称的前缀
	 */
	private static String parsePrefix(String prefixCode) {
		String str = " ";
		char[] array = prefixCode.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c == '1') {
				str += "┃&nbsp;&nbsp;";
			} else {
				str += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
		}
		return str;
	}

	@Override
	public void down(Integer id) {
		if (id == null) {
			return;
		}
		FunctionPoint p = getById(FunctionPoint.class, id);
		if (p == null) {
			return;
		}
		changeSortValue(p, getNearByBrother(p, 1));
	}

	@Override
	public void up(Integer id) {
		if (id == null) {
			return;
		}
		FunctionPoint p = getById(FunctionPoint.class, id);
		if (p == null) {
			return;
		}
		changeSortValue(p, getNearByBrother(p, 0));
	}

	/**
	 * 交换排序号
	 * 
	 * @param p1
	 *            要交换的权限1
	 * @param p2
	 *            要交换的权限2
	 */
	private void changeSortValue(FunctionPoint p1, FunctionPoint p2) {
		if (p1 == null || p2 == null) {
			return;
		}
		if (p1.getOrderNum() == null) {
			p1.setOrderNum(p1.getId());
		}
		if (p2.getOrderNum() == null) {
			p2.setOrderNum(p2.getId());
		}
		int sortValue = p1.getOrderNum();
		p1.setOrderNum(p2.getOrderNum());
		p2.setOrderNum(sortValue);
		update(p1);
		update(p2);
	}

	/**
	 * 获取临近的兄弟 
	 * @param p 当前权限
	 * @param type 0:表示比自己小的，1表示比自己大的
	 * @return FunctionPoint 兄弟权限，如果没找到返回null
	 */
	private FunctionPoint getNearByBrother(FunctionPoint p, int type) {
		return functionPointDao.getNearByBrother(p, type);
	}

	@Override
	public Integer getMaxOrderNum(Integer parentId) {
		return functionPointDao.getMaxOrderNum(parentId);
	}

	@Override
	public List<FunctionPoint> findFunctionPointByIds(Integer[] ids) {
		return functionPointDao.findByIds(ids);
	}
   
	public List<FunctionPoint> getFunctionPointsByUser(User user) {
		// 把用户的所有角色对应的权限搜集到集合中，并查重
		Set<FunctionPoint> functionPoints = new TreeSet<FunctionPoint>(new FunctionPointComparator());
		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				if (role.getFunctionPoints() != null) {
					for (FunctionPoint p : role.getFunctionPoints()) {
						functionPoints.add(p);
					}
				}
			}
		}
		// System.out.println("function point found:"+functionPoints.size());
		List<FunctionPoint> result = new ArrayList<FunctionPoint>();
		// 整理这些权限成为一个有层级关系的树状结构
		findChild(functionPoints, null, result);
		return result;
	}

	/**
	 * 整理集合中的数据成为有上下级关联关系的集合<br/>
	 * <b>原序列</b>------------<b>整理结果</b><br/>
	 * A-----------------A<br/>
	 * A1-------------------A1<br/>
	 * A2----------------------A11<br/>
	 * A11---------------------A12<br/>
	 * A12------------------A2<br/>
	 * B-----------------B<br/>
	 * B1-------------------B1<br/>
	 * C-----------------C<br/>
	 * 
	 * @param queuePoints 要整理集合
	 * @param parent 父节点
	 * @param result 搜集的所有顶级菜单
	 */
	private void findChild(Set<FunctionPoint> queuePoints, FunctionPoint parent, List<FunctionPoint> result) {
		for (FunctionPoint point : queuePoints) {
			FunctionPoint copy = point.copy();
			if (copy.getParent() == null) {
				if (!result.contains(copy)) {
					result.add(copy);// 搜集所有顶级菜单
					findChild(queuePoints, copy, result);// 递归搜集子菜单的子菜单
				}
			} else {
				if (parent != null && copy.getParent().equals(parent)) {
					parent.getChildren().add(copy);
					findChild(queuePoints, copy, result);// 递归搜集子菜单的子菜单
				}
			}
		}
	}

	public Integer getNextId() {
		return functionPointDao.getNextId();
	}
}
