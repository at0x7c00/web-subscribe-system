package me.huqiao.wss.sys.dao;

import java.util.List;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.sys.entity.User;

/**
 * 用户表dao接口
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IUserDao extends IBaseDao<User> {
	
	/**
	 * 查询用户数量
	 * @param user 查询对象
	 * @return 用户数量
	 */
	Long findRowCount(User user);

	/**
	 * 用户分页查询
	 * @param user 查询对象
	 * @param first 开始值
	 * @param maxNum 最大数量
	 * @param orderField 排序字段
	 * @param orderDirection 排序方式
	 * @return List<User>用户列表
	 */
	
	List<User> findPage(User user, int first, int maxNum, String orderField, String orderDirection);

	/**
	 * 查询有效用户
	 * @param user 查询对象
	 * @return List<User> 有效用户列表
	 */
	List<User> findAllActive(User user);
	
	/**
	 * 按搜索关键字查询用户
	 * @param  queryKey 查询关键字
	 * @return List<User> 用户列表
	 */
	List<User> findByQueryKey(String queryKey);

	/**
	 * 根据关键词查询用户数量
	 * @param queryKey 查询关键字
	 * @return Long 用户数量
	 */
	Long findRowCount(String queryKey);
	
    /**
     * 查找多个用户
     * @param userNames 用户名数组
     * @return List<User> 员工列表
     */
	List<User> findListByUserName(String[] userNames);
}
