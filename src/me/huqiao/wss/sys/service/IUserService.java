package me.huqiao.wss.sys.service;

import java.util.List;

import me.huqiao.wss.common.service.IBaseService;
import me.huqiao.wss.sys.entity.User;
import me.huqiao.wss.util.web.Page;

/**
 * 
 * 用户service
 * @author NOVOTS
 * @version Version 1.0
 */
public interface IUserService extends IBaseService<User> {
	/**
	 * 分页查询用户信息
	 * @param user 查询对象 
	 * @param pageInfo 分页查询对象
	 * @return  Page<User> 分页查询结果
	 */
	public Page<User> getPage(User user, Page pageInfo);

	/**
	 *  设置角色
	 * @param user 用户对象
	 * @param roleIds 角色id数组
	 */

	public void setRoles(User user, Integer[] roleIds);
	
	/**
	 * 获取所有用户
	 * @return List<User> 用户列表
	 */
	public List<User> eagerListAll();

	/**
	 * 查询活动状态的用户信息
	 * @param user 用户对象
	 * @return List<User> 用户列表
	 */
	public List<User> findAllActive(User user);
	

	/**
     *根据关键词搜索得到分页对象
	 * @param  queryKey 查询关键字
	 * @param  pageInfo 分页信息对象
	 * @return Page<User> 分页结果
	 * 
	 */
	Page<User> queryByKey(String queryKey, Page<User> pageInfo);

	/**
	 *  根据用户名获取用户列表
	 * @param userNames 用户名数组
	 * @return List<User> 用户列表
	 * 
	 */
	List<User> queryById(String[] userNames);

}
