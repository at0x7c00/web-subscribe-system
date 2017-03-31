package me.huqiao.wss.util.stat;

import java.util.ArrayList;
import java.util.List;


/**
 * 统计表
 * @author NOVOTS
 * @version Version 1.0
 */
public class StatTable<R, C, D> {
    /**表标题*/
	private String title="";
	/**行头集合*/
	private List<R> rowHeads;
	/**列头集合*/
	private List<C> colHeads;
	/**行集合*/
	private List<StatRow<R, C, D>> trs = new ArrayList<StatRow<R, C, D>>();
	/**汇总行*/
	private StatRow<R, C, D> total = new StatRow<R, C, D>();
    /**
     * @return List<R> 行标题
     */
	public List<R> getRowHeads() {
		return rowHeads;
	}
    /**
     * @param rowHeads 要设置的行标题
     */
	public void setRowHeads(List<R> rowHeads) {
		this.rowHeads = rowHeads;
	}
    /**
     * @return List<C> 列标题
     */
	public List<C> getColHeads() {
		return colHeads;
	}
	/**
	 * @param colHeads 要设置的列标题
	 */
	public void setColHeads(List<C> colHeads) {
		this.colHeads = colHeads;
	}
    /**
     * @return List<StatRow<R, C, D>> 行集合
     */
	public List<StatRow<R, C, D>> getTrs() {
		return trs;
	}
    /**
     * @param trs 要设置的行集合
     */
	public void setTrs(List<StatRow<R, C, D>> trs) {
		this.trs = trs;
	}
	/**
	 * @return StatRow<R, C, D> 行汇总
	 */
	public StatRow<R, C, D> getTotal() {
		return total;
	}
	/**
	 * @param total 要设置的行汇总
	 */
	public void setTotal(StatRow<R, C, D> total) {
		this.total = total;
	}
	
	/**
	 * @return String 标题
	 */
	public String getTitle() {
		return title;
	}
   /**
    * @param title 要设置的标题
    */
	public void setTitle(String title) {
		this.title = title;
	}

	
	/**
	 * 添加行
	 * @param tr 添加的行
	 * @param isCalcuteTotal 是否计算标志
	 */
	public void addTr(StatRow<R, C, D> tr, Boolean isCalcuteTotal) {
		trs.add(tr);
		if (isCalcuteTotal) {
			calcuteTotal(tr);
		}
	}
    /**
     * 计算指定行
     * @param tr 要计算的行
     */
	public void calcuteTotal(StatRow<R, C, D> tr) {
		StatTd<C, D> totalTd = null;
		for (StatTd<C, D> td : tr.getTds()) {// 集合的合计
			totalTd = findTd(td.getColHead(), total.getTds());
			totalTd.setCount(totalTd.getCount() + td.getCount());
		}
		this.total.getTotal().setCount(
				this.total.getTotal().getCount() + tr.getTotal().getCount());
		;// 累计合计行合计
	}

	/**
	 *  从集合中搜索，没有则创建一个
	 * @param colHead 列标题
	 * @param tds 列列表
	 * @return StatTd<C, D> 要统计的表格
	 */
	private StatTd<C, D> findTd(C colHead, List<StatTd<C, D>> tds) {
		StatTd<C, D> newTotalTd = null;
		int index = tds.indexOf(colHead);
		if (index != -1) {
			return tds.get(index);
		}
		newTotalTd = new StatTd<C, D>();
		newTotalTd.setColHead(colHead);
		tds.add(newTotalTd);
		return newTotalTd;

	}
}
