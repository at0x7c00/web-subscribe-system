package me.huqiao.wss.sys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import me.huqiao.wss.common.service.impl.BaseServiceImpl;
import me.huqiao.wss.sys.dao.IRoleDao;
import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.Role;
import me.huqiao.wss.sys.service.IFunctionPointService;
import me.huqiao.wss.sys.service.IRoleService;
import me.huqiao.wss.util.web.Page;

import org.springframework.stereotype.Service;

/**
 * 角色Service实现
 * @author NOVOTS
 * @version Version 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Resource
    private IRoleDao roleDao;
    @Resource
    private IFunctionPointService functionPointService;
    
    @Override
    public Page<Role> getPage(Role role,Page pageInfo) {
        int countRecord = roleDao.findRowCount(role).intValue();
        Page<Role>  page = new Page<Role>(pageInfo == null?0:pageInfo.getPageNum(),countRecord,pageInfo.getNumPerPage());
        List<Role> roleList = roleDao.findPage(role,page.getStartIndex(), page.getNumPerPage(),pageInfo.getOrderField(),pageInfo.getOrderDirection());
        page.setList(roleList);
        page.setOrderDirection(pageInfo.getOrderDirection()==null?"asc":pageInfo.getOrderDirection());
        page.setOrderField(pageInfo.getOrderField()==null?"id":pageInfo.getOrderField());
        return page;
    }

	@Override
	public void setPrivilege(Role role, Integer[] functionPointIds) {
		Set<FunctionPoint> oldPoints = role.getFunctionPoints();
		if(oldPoints == null){
			oldPoints = new HashSet<FunctionPoint>();
		}
		List<FunctionPoint> newPoints = functionPointService.findFunctionPointByIds(functionPointIds);
		List<FunctionPoint> forRemove = new ArrayList<FunctionPoint>(); 
		for(FunctionPoint p : oldPoints){
			if(!newPoints.contains(p)){
				forRemove.add(p);
			}
		}
		oldPoints.removeAll(forRemove);
		oldPoints.addAll(newPoints);
		role.setFunctionPoints(oldPoints);
		update(role);
	}

	@Override
	public List<Role> findRoleByIds(Integer[] roleIds) {
		return  roleDao.findByIds(roleIds);
	}
}
