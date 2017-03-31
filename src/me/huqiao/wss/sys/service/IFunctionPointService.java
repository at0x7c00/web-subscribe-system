package me.huqiao.wss.sys.service;

import java.util.Collection;
import java.util.List;

import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.util.web.Page;

/**
 * 权限Service接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IFunctionPointService extends IBaseService<FunctionPoint> {

    /**
     * 得到分页信息
     * @param functionPoint 权限 
     * @return Page<FunctionPoint> 权限分页结果
     */
    public Page<FunctionPoint> getPage(FunctionPoint functionPoint,Page pageInfo);
    
    /**
     * 获取顶级权限 
     * @return  List<FunctionPoint>  权限列表
     */
    public List<FunctionPoint> getTopLevelFunctionPoints();
    
    
    /**
     * 树状显示topDepartments到result中
     * @param topPoints 最高权限集合
     * @param prefixCode 前一个code
     * @param result 结果
     * @param filterId 
     */
    public void tree(Collection<FunctionPoint> topPoints, String prefixCode,List<FunctionPoint> result,Integer filterId);
    
    /**
     * 获取最大指定父级菜单下的最大排序值
     * @param parentId 父类工单ID
     * @return Integer 最大排序数
     */
    public Integer getMaxOrderNum(Integer parentId);
    
    /**
     * 指定菜单下移
     * @param id
     */
    public void down(Integer id);
    
    /**
     * 指定菜单上移
     * @param id
     */
    public void up(Integer id);
    
    /**
	 * 按照id查询权限菜单
	 * @param ids
	 * @return List<FunctionPoint> 权限列表
	 */
	public List<FunctionPoint> findFunctionPointByIds(Integer[] ids);
	
	/**
	 * 查询用户的所有权限
	 * @param user
	 * @return List<FunctionPoint> 权限列表
	 */
	public List<FunctionPoint> getFunctionPointsByUser(User user);
	
	/**
	 * 获取下一个id主键值
	 * @return Integer 下一个主键
	 */ 
	public Integer getNextId();
	
    
}
