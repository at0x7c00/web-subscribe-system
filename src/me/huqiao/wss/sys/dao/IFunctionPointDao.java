package me.huqiao.wss.sys.dao;
import java.util.List;

import me.huqiao.wss.common.dao.IBaseDao;
import me.huqiao.wss.sys.entity.FunctionPoint;
/**
 * 权限Dao接口
 * @author NOVOTS 
 * @version Version 1.0
 */
public interface IFunctionPointDao extends IBaseDao<FunctionPoint> {

    /**
     * 查询记录数
     * @param functionPoint
     * @return Long 记录数
     */
    Long findRowCount(FunctionPoint functionPoint);

   
    /**
     * 分页查询角色信息
     * @param functionPoint 权限对象
     * @param first
     * @param maxNum
     * @param orderField
     * @param orderDirection
     * @return 权限列表
     */
    List<FunctionPoint> findPage(FunctionPoint functionPoint, int first, int maxNum,String orderField,String orderDirection);
    
    /**
     * 获取顶级权限
     * @return List<FunctionPoint> 权限列表
     */
    public List<FunctionPoint> getTopLevelFunctionPoints();
    
    
    /** 
	 * 获取临近的兄弟
	 * @param p
	 * @param type  0:表示比自己小的，1表示比自己大的
	 * @return 如果没找到返回null
	 */
	public FunctionPoint getNearByBrother(FunctionPoint p,int type);
    /**
     * 获取最大排序值
     * @param parentId 父亲ID
     * @return Integer 最大值
     */
	public Integer getMaxOrderNum(Integer parentId);
	
	/**
	 * 获取下一个主键值
	 * @return Integer 主键值
	 */
	public Integer getNextId();
	
	/**
	 * 查询多个权限菜单
	 * @param ids ID数组
	 * @return List<FunctionPoint>权限列表
	 */
	public List<FunctionPoint> findByIds(Integer[] ids);
	
}
