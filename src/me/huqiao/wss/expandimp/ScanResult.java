package me.huqiao.wss.expandimp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 遍历结果
 * @author huqiao
 */
public class ScanResult {

	private Map<String,Object> data = new HashMap<String,Object>();
	
	private int rowCount = 1;
	
	/**
	 * 从Excel里读取数据时，表示excel的行号
	 */
	private int excelRowNum;
	
	/**
	 * 读取遍历结果的一行
	 * @param row 行数
	 * @return Map<String,String> 遍历结果
	 */
	public Map<String,String> readRow(int row){
		Map<String,String> result = new HashMap<String,String>();
		if(data==null){
			return result;
		}
		for(Map.Entry<String, Object> entry : data.entrySet()){
			if(entry.getValue() instanceof String){
				if(row==0){
					result.put(entry.getKey(), (String)entry.getValue());
				}
			}else{
				List<Map<String,String>> setPropMapList = (List<Map<String,String>> )entry.getValue();
				if(setPropMapList.size() - 1 < row){
					continue;
				}
				Map<String,String> setPropMap = setPropMapList.get(row);
				for(Map.Entry<String, String> setPropMapEntry : setPropMap.entrySet()){
					result.put(setPropMapEntry.getKey(), setPropMapEntry.getValue());
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 设置行数
	 * @param rowCount
	 */
	public void setRowCountIfBigThan(int rowCount){
		this.rowCount = rowCount > this.rowCount ? rowCount : this.rowCount;
	}
	
	/**
	 * 获取行数
	 * @return int 行数
	 */
	public int rowCount(){
		return rowCount;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ScanResult [data=" + data + ", rowCount=" + rowCount + "]";
	}

	public int getExcelRowNum() {
		return excelRowNum;
	}

	public void setExcelRowNum(int excelRowNum) {
		this.excelRowNum = excelRowNum;
	}
	
	
}
