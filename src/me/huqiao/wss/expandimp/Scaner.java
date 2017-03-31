package me.huqiao.wss.expandimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.huqiao.wss.datamodel.ModelProp;
import me.huqiao.wss.util.bean.BeanPropUtil;


/**
 * 对象属性遍历器
 * @author NOVOTS
 * @version Version 1.0
 */
public class Scaner {

	private List<String> tryToDisplayOfComplexObject;

	public List<String> getTryToDisplayOfComplexObject() {
		return tryToDisplayOfComplexObject;
	}

	/**
	 * 设置尝试显示的复杂对象属性(适用于导出)
	 * 
	 * @param tryToDisplayOfComplexObject
	 */
	public void setTryToDisplayOfComplexObject(List<String> tryToDisplayOfComplexObject) {
		this.tryToDisplayOfComplexObject = tryToDisplayOfComplexObject;
	}
	
	/**
	 * 扫描数据集合，生成扫描结果
	 * @param dataList 对象列表
	 * @param props 属性列表
	 * @return List<ScanResult> 扫描结果列表
	 */
	public List<ScanResult> doScan(List dataList,List<ModelProp> props){
		List<ScanResult> res = new ArrayList<ScanResult>();
		for(Object data : dataList){
			res.add(scanOnObject(data,props));
		}
		return res;
		
	}

	/**
	 * 扫描单个数据对象
	 * @param data 对象
	 * @param props 属性列表
	 * @return ScanResult 扫描结果
	 */
	private ScanResult scanOnObject(Object data, List<ModelProp> props) {
		ScanResult result = new ScanResult();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		for(ModelProp prop : props){
			Object propValue;
			try {
				propValue = BeanPropUtil.readProp(data,prop.getName());
				if(propValue instanceof Set){
					//读取集合类型的数据为一个List<Map>
					List<Map<String,String>> setPropList = new ArrayList<Map<String,String>>();
					for(Object setElement : (Set)propValue){
						Map<String,String> setPropMap = new HashMap<String,String>();
						for(ModelProp childProp : prop.getChildren()){
							Object childPropValue = BeanPropUtil.readProp(setElement,childProp.getName());
							String formatVal = BeanPropUtil.format(childPropValue, tryToDisplayOfComplexObject);
							//System.out.println("read prop:" + childProp.getAccessName()+" = " + formatVal);
							setPropMap.put(childProp.getAccessName(),formatVal);
						}
						setPropList.add(setPropMap);
					}
					result.setRowCountIfBigThan(setPropList.size());
					dataMap.put(prop.getAccessName(), setPropList);
				}else{
					//读取简单数据类型为字符串
					dataMap.put(prop.getAccessName(), BeanPropUtil.format(propValue, tryToDisplayOfComplexObject));
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		result.setData(dataMap);
		return result;
	}
	
	public static void main(String[] args) {
		Set<String> strs = new HashSet<String>();
		System.out.println(strs instanceof Set);
	}
	
	
}
