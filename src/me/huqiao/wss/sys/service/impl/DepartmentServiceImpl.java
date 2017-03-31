package me.huqiao.wss.sys.service.impl;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.history.entity.HistoryRecord;
import me.huqiao.wss.sys.dao.IDepartmentDao;
import me.huqiao.wss.sys.entity.Department;
import me.huqiao.wss.sys.service.IDepartmentService;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;
/**
 * 部门Service接口实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
    /**部门DAO对象*/
    @Resource
    private IDepartmentDao departmentDao;
    @Override
    public Page<Department> getListPage(Department department,Page pageInfo) {
      	pageInfo.setTotalCount(departmentDao.findListRowCount(department).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(departmentDao.findListPage(department,pageInfo));
        return pageInfo;
    }
	@Override
	public Page<HistoryRecord<Department>> getHistoryListPage(Department department, Page pageInfo) {
		pageInfo.setTotalCount(departmentDao.findHistoryListRowCount(department,pageInfo).intValue());
		pageInfo.setOrderField(pageInfo.getOrderField() == null ? "id": pageInfo.getOrderField());
		pageInfo.setOrderDirection(pageInfo.getOrderDirection() == null ? "asc": pageInfo.getOrderDirection());
		pageInfo.setList(departmentDao.findHistoryListPage(department,pageInfo));
        return pageInfo;
	}
	@Override
	public Department findByVersion(Integer version) {
		return departmentDao.findByVersion(version);
	}
	@Override
	public Page<Department> queryByKey(String queryKey, Page<Department> pageInfo) {
		int countRecord = departmentDao.findRowCount(queryKey).intValue();
		Page<Department> page = new Page<Department>(pageInfo == null ? 0 : pageInfo.getPageNum(), countRecord, pageInfo.getNumPerPage());
		List<Department> departmentList = departmentDao.findByKey(pageInfo,queryKey);
		page.setList(departmentList);
		return page;
	}
	@Override
	public List<Department> queryById(Integer[] ids) {
		return departmentDao.findById(ids);
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
	public void tree(Collection<Department> topDepartments, String prefixCode, List<Department> result, Integer filterId) {
		if (topDepartments == null)
			return;
		int count = 0;
		for (Department subDepartment : topDepartments) {
			count++;
			Department tmpDepartment = new Department();
			tmpDepartment.setId(subDepartment.getId());
			tmpDepartment.setParent(subDepartment.getParent());
			tmpDepartment.setSort(subDepartment.getSort());
			tmpDepartment.setManageKey(subDepartment.getManageKey());
			tmpDepartment.setStatus(subDepartment.getStatus());
			String prefix = parsePrefix(prefixCode);
			if (count == topDepartments.size()) {
				if (filterId == null || subDepartment.getId() != filterId) {
					tmpDepartment.setName(prefix + "┗━" + subDepartment.getName());
					result.add(tmpDepartment);
					tree(subDepartment.getChildren(), prefixCode + "0", result, filterId);
				}
			} else {
				if (filterId == null || subDepartment.getId() != filterId) {
					tmpDepartment.setName(prefix + "┣━" + subDepartment.getName());
					result.add(tmpDepartment);
					tree(subDepartment.getChildren(), prefixCode + "1", result, filterId);
				}
			}
		}
	}

	/**
	 * 解析前缀，生成复制Department的名称的前缀
	 * 
	 * @param prefixCode
	 *            要解析对象
	 * @return String 生成复制Department的名称的前缀
	 */
	private static String parsePrefix(String prefixCode) {
		String str = " ";
		char[] array = prefixCode.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c == '1') {
				str += "┃  ";
			} else {
				str += "     ";
			}
		}
		return str;
	}
}