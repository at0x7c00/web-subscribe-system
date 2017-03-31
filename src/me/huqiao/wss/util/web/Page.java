package me.huqiao.wss.util.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

/**
 * 分页对象<br/>
 * 实现对分页参数的封装，包括当前页、每页显示条数、总记录数
 *  <T> 存放到Page的List集合中的对象类型
 * 这里用一句话描述这个类的作用
 * @author NOVOTS
 * @version Version 1.0
 */
public class Page<T> {
	/** 总页数，通过总记录数和每页显示记录条数计算获得 */  
    private int countPage;  
    /** 总记录数 */  
    private int totalCount;  
    /**当前页，默认是第一页 */  
    private int pageNum = 1;  
    /**每页显示记录条数 ，默认是每页显示20条记录 */  
    private int numPerPage = 10;  
    /**排序字段*/
    private String orderField;
    /**升序降序*/
    private String orderDirection;
    /**开始索引，通过当前页和每页显示记录条数计算获得 */  
    private int startIndex;  
    /**结果集*/
    private List<T> list;
    /**操作开始时间*/
    private Date operateDateStart;
    /**操作结束时间*/
    private Date operateDateEnd;
    /**操作*/
    private String operator;
    /**操作类型*/
    private String operateType;
    
    public Page() {  
    }  
  
	/** 
     * 两个参数的构造方法，调用该构造方法需要另行设置结果list 
     *  
     * @param pageNum 
     *            当前页 
     * @param totalCount 
     *            总页数 
     */  
    public Page(int pageNum, int totalCount) {  
        this.pageNum = pageNum;  
        this.totalCount = totalCount;  
        calculate();  
    }  
  
    /** 
     * 能够设置一页显示多少条记录的构造方法 
     *  
     * @param pageNum 
     *            当前页 
     * @param totalCount 
     *            总记录数 
     * @param numPerPage 
     *            每页最多显示的记录条数 
     */  
    public Page(int pageNum, int totalCount, int numPerPage) {  
        this.totalCount = totalCount;  
        this.pageNum = pageNum;  
        this.numPerPage = numPerPage;  
        calculate();  
    }  
  
    /** 
     * 计算开始索引和总页数 
     */  
    public void calculate() {  
        // 计算开始索引  
        this.startIndex = (pageNum - 1) * numPerPage;  
        // 计算总页数  
        this.countPage = (totalCount % numPerPage == 0) ? (totalCount / numPerPage)  
                : (totalCount / numPerPage + 1);  
    }  
    /**
     * @return int  总页数
     */
    public int getCountPage() {  
        return countPage;  
    }  
    /**
     * @param startIndex 开始索引
     */
    public void setStartIndex(int startIndex) {  
        this.startIndex = startIndex;  
    }
    /**
     * @return int 页码
     */
    public int getPageNum() {
        return pageNum;
    }
    /**
     * @param pageNum 要设置的页码
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    /**
     * @return int 每页的显示的记录数
     */
    public int getNumPerPage() {
        return numPerPage;
    }
    /**
     * @param numPerPage 要设置的每页的显示的记录数
     */
    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
    /**
     * @return int 开始索引
     */
    public int getStartIndex() {
    	calculate();
        return startIndex;
    }
    /**
     * @param countPage 要设置的总页数
     */
    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }
    /**
     * @return List<T> 结果集
     */
    public List<T> getList() {
        return list;
    }
   /**
    * @param list 要设置的结果集
    */
    public void setList(List<T> list) {
        this.list = list;
    }
    /**
     * @return int 总记录数
     */
    public int getTotalCount() {
        return totalCount;
    }
   /**
    * @param totalCount 要设置的总记录数
    */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.calculate();
    }
    /**
     * @return String 排序字段
     */
	public String getOrderField() {
		return orderField;
	}
	/**
	 * @param orderField 要设置的排序字段
	 */
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	/**
	 * @return String 排序方向
	 */
	public String getOrderDirection() {
		return orderDirection;
	}
	/**
	 * @param orderDirection 要设置的排序方向
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	private int pageBarSize = 5;
	/**
	 * @return List<Integer> 分页栏索引
	 */
	public List<Integer> getPageBarIndex(){
		List<Integer> res = new ArrayList<Integer>();
		if(countPage<pageBarSize){
			for(int i = 1;i<=countPage;i++){
				res.add(i);
			}
			return res;
		}
		//尝试向左取两个
		int leftMove = pageNum - 2;
		if(leftMove>=0){
			int k = 0;
			int i = pageNum -1;
			for(;k<5 && i <=countPage;i++){
				res.add(i);
				k++;
			}
			if(k<5){
				i -= k;
				for(int x = 5 - k;x>0;x--){
					res.add(0,--i);
				}
			}
		}else{
			int i = 1;
			for(;i<=pageNum;i++){
				res.add(i);
			}
			res.add(i);
			res.add(++i);
			res.add(++i);
			for(;leftMove<0;leftMove++){
				res.add(++i);
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return "Page [countPage=" + countPage + ", totalCount=" + totalCount + ", pageNum=" + pageNum + ", numPerPage=" + numPerPage + ", orderField=" + orderField + ", orderDirection=" + orderDirection + ", startIndex=" + startIndex + ", list="
				+ list + ", pageBarSize=" + pageBarSize + "]";
	}
    /**
     * @return  Date 操作开始时间
     */
	public Date getOperateDateStart() {
		return operateDateStart;
	}
    /**
     * @param operateDateStart 要设置的操作开始时间
     */
	public void setOperateDateStart(Date operateDateStart) {
		this.operateDateStart = operateDateStart;
	}
    /**
     * @return operateDateEnd 操作结束时间
     */
	public Date getOperateDateEnd() {
		return operateDateEnd;
	}
    /**
     * @param operateDateEnd 要设置的操作结束时间
     */
	public void setOperateDateEnd(Date operateDateEnd) {
		this.operateDateEnd = operateDateEnd;
	}
	/** 
	 * @return String 操作人
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator 要设置的操作人
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return String 操作类型
	 */
	public String getOperateType() {
		return operateType;
	}
	/**
	 * @param operateType 要设置的操作类型
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	/**
	 * @return int 分页栏大小
	 */
	public int getPageBarSize() {
		return pageBarSize;
	}
    /**
     * @param pageBarSize 要设置的分页栏大小
     */
	public void setPageBarSize(int pageBarSize) {
		this.pageBarSize = pageBarSize;
	}

	private String areaKey;
	private String provinceKey;

	@Transient
	public String getAreaKey() {
		return areaKey;
	}

	public void setAreaKey(String areaKey) {
		this.areaKey = areaKey;
	}

	@Transient
	public String getProvinceKey() {
		return provinceKey;
	}

	public void setProvinceKey(String provinceKey) {
		this.provinceKey = provinceKey;
	}
}
