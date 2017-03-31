package me.huqiao.wss.common.entity;

import java.util.List;
/**
 * select2 
 * @author NOVOTS
 * @version Version 1.0
 */
public class Select2<T> {

	private long total_count;
	
	private boolean incomplete_results = false;
	
	private List<T> items;

	public long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(long total_count) {
		this.total_count = total_count;
	}

	public boolean isIncomplete_results() {
		return incomplete_results;
	}

	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
