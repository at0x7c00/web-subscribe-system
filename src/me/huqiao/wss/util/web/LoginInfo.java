package me.huqiao.wss.util.web;

import java.util.List;

import me.huqiao.wss.sys.entity.FunctionPoint;
import me.huqiao.wss.sys.entity.User;

/**
 * 登录信息<br/>
 * 保存用户的登录信息，包括User、FunctionPoint等信息
 * @author NOVOTS
 * @version Version 1.0
 */
public class LoginInfo {
	/**
	 * <p>Title:LoginInfo无参构造方法 </p>
	 * <p>Description:LoginInfo无参构造方法  </p>
	 */
	public LoginInfo(){}
	/**
	 * <p>Title:登陆信息构造方法 </p>
	 * <p>Description:登陆信息带参数构造方法 </p>
	 * @param user 用户对象
	 * @param topFunctionPointst 权限
	 */
	public LoginInfo(User user,List<FunctionPoint> topFunctionPointst){
		this.user = user;
		this.topFunctionPoints = topFunctionPointst;
	}
    /**用户*/
	private User user;
	/**权限列表*/
	private List<FunctionPoint> topFunctionPoints;
	
	/**
	 * @return User 用户
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user 要设置的用户
	 */
	public void setUser(User user) {
		this.user = user;
	} 
	/**
	 * @return List<FunctionPoint> 权限类表
	 */
	public List<FunctionPoint> getTopFunctionPoints() {
		return topFunctionPoints;
	}
	/**
	 * @param topFunctionPoints 要设置的权限
	 */
	public void setTopFunctionPoints(List<FunctionPoint> topFunctionPoints) {
		this.topFunctionPoints = topFunctionPoints;
	}

}
