package me.huqiao.wss.util.stat;

import java.util.ArrayList;
import java.util.List;


/**
 * 统计表表格
 * @author NOVOTS
 * @version Version 1.0
 */
public class StatTd<C, D> {
    /**列头*/
	private C colHead;
	/**总数*/
	private Long count = 0l;
	/**表格内容集合*/
	private List<D> contents = new ArrayList<D>();
	/**占比*/
	private Float percent = 0f;
    /**
     * @return Float 占比
     */
	public Float getPercent() {
		return percent;
	}
   /**
    * @param percent 要设置的占比
    */
	public void setPercent(Float percent) {
		this.percent = percent;
	}
   /**
    * @return C 列头
    */
	public C getColHead() {
		return colHead;
	}
   /**
    * @param col 要设置列
    */
	public void setColHead(C col) {
		this.colHead = col;
	}
	/**
	 * @return Long 总数
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count 要设置总数
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @return List<D> 表格内容集合
	 */
	public List<D> getContents() {
		return contents;
	}
	/**
	 * @param contents 要设置表格内容集合
	 */
	public void setContents(List<D> contents) {
		this.contents = contents;
	}

}
