package me.huqiao.wss.sys.dao;
import java.util.List;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.sys.entity.Role;

/**
 * 角色Dao接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IRoleDao extends IBaseDao<Role> {

    
	/**
	 * 查询记录数
	 * @param  role 角色查询对象
	 * @return Long 记录数量
	 */
    Long findRowCount(Role role);

   
    /**
     * 分页查询角色信息
     * @param role 角色查询对象
     * @param first 开始值
     * @param maxNum 最大数量
     * @param orderField 排序字段
     * @param orderDirection 排序方向
     * @return List<Role> 角色列表
     */
    List<Role> findPage(Role role, int first, int maxNum,String orderField,String orderDirection);

    /**
     * 按照角色Id查询
     * @param roleIds 角色ID 数组
     * @return List<Role> 角色列表
     */
	List<Role> findByIds(Integer[] roleIds);
}
