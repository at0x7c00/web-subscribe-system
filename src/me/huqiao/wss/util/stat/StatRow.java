package me.huqiao.wss.util.stat;

import java.util.ArrayList;
import java.util.List;


/**
 * 统计表行
 * @author NOVOTS
 * @version Version 1.0
 */
public class StatRow<R, C, D> {
    /**行头*/
	private R rowHead;
	/**表格集合*/
	private List<StatTd<C, D>> tds = new ArrayList<StatTd<C, D>>();
	/**汇总表格*/
	private StatTd<C, D> total = new StatTd<C, D>();
    /**
     * @return R 表头
     */
	public R getRowHead() {
		return rowHead;
	}
    /**
     * @param row 要设置的表头
     */
	public void setRowHead(R row) {
		this.rowHead = row;
	}
    /**
     * @return List<StatTd<C, D>> 列列表
     */
	public List<StatTd<C, D>> getTds() {
		return tds;
	}
   /**
    * @param tds 要设置的列数组
    */
	public void setTds(List<StatTd<C, D>> tds) {
		this.tds = tds;
	}
    /**
     * @return  StatTd<C, D> 列统计结果
     */
	public StatTd<C, D> getTotal() {
		return total;
	}
   /**
    * @param total 要设置多列的总数
    */
	public void setTotal(StatTd<C, D> total) {
		this.total = total;
	}
	/**
	 * 添加列，设置是否需要统计
	 * @param td 加入的列
	 * @param isCalcuteTotal 是否需要汇总    
	 */
	public void addTds(StatTd<C, D> td, Boolean isCalcuteTotal) {
		tds.add(td);
		if (isCalcuteTotal) {
			calcuteTotal(td);
		}
	}
    /**
     * 统计总数
     * @param td 要计算的列
     */
	private void calcuteTotal(StatTd<C, D> td) {
		total.setCount(total.getCount() + td.getCount());
	}

}
