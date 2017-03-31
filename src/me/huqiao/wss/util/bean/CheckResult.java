package me.huqiao.wss.util.bean;

public class CheckResult {

	private boolean changed;
	private String info;
	
	public CheckResult(boolean changed, String info) {
		super();
		this.changed = changed;
		this.info = info;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
