package me.huqiao.wss.expandimp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import me.huqiao.wss.datamodel.ModelProp;
import me.huqiao.wss.datamodel.ModelPropType;
import me.huqiao.wss.datamodel.PropUtil;

/**
 * 属性解析器
 * @author NOVOTS
 * @version v1.0
 */
public class PropParser {

	private List<String> propsIgnoreOfComplexObject;
	private boolean ignoreSet = false;
	
	public List<String> getPropsIgnoreOfComplexObject() {
		return propsIgnoreOfComplexObject;
	}
	/**
	 * 设置忽略显示的复杂对象属性（适用于导出和导入）
	 * @param propsIgnoreOfComplexObject
	 */
	public void setPropsIgnoreOfComplexObject(List<String> propsIgnoreOfComplexObject) {
		this.propsIgnoreOfComplexObject = propsIgnoreOfComplexObject;
	}
	
	
	/**
	 * 解析字段
	 * @param modelName 数据模型名称（类名称）
	 * @return 解析到的字段集合
	 */
	public List<ModelProp> parseProp(String modelName){
		List<ModelProp> propBag = new ArrayList<ModelProp>();
		try {
			Class c  = Class.forName(modelName);
			
			traceForNormalProp(c,propBag,null);
			
			if(!isIgnoreSet()){
				traceForSetProp(c,propBag,null);
			}
			
			return propBag;
		} catch (ClassNotFoundException e) {
			return propBag;
		}
	}
	
	/**
	 * 扫描集合类字段
	 * @param c
	 * @param propBag
	 */
	private void traceForSetProp(Class c, List<ModelProp> propBag,String accessName) {
		if(c.getSuperclass()!=null){
			traceForSetProp(c.getSuperclass(),propBag,accessName);
		}
		for(Field f : c.getDeclaredFields()){
			if(isIgnoreProp(c,f.getName())){
				continue;
			}
			ModelProp prop = new ModelProp(f.getName(),"props." + c.getCanonicalName() + "." + f.getName());
			parseTypeDescAndNullable(prop,c,f.getName());
			if(!prop.isSet()){
				continue;
			}
			try {
				Class propClass = Class.forName(prop.getPropClassName());
				List<ModelProp> childrenPropBag = new ArrayList<ModelProp>();
				traceForNormalProp(propClass,childrenPropBag,prop.getName());
				prop.setChildren(childrenPropBag);
				prop.setClassName(c.getCanonicalName());
				prop.setAccessName((accessName==null ? "" : accessName + ".") + f.getName());
				propBag.add(prop);
			} catch (ClassNotFoundException e) {
			}
		}
		
	}
	/**
	 * 扫描简单字段
	 * @param c
	 * @param propBag
	 */
	private void traceForNormalProp(Class c, List<ModelProp> propBag,String accessName) {
		if(c.getSuperclass()!=null){
			traceForNormalProp(c.getSuperclass(),propBag,accessName);
		}
		for(Field f : c.getDeclaredFields()){
			if(isIgnoreProp(c,f.getName())){
				continue;
			}
			ModelProp prop = new ModelProp(f.getName(),"props." + c.getCanonicalName() + "." + f.getName());
			parseTypeDescAndNullable(prop,c,f.getName());
			if(prop.isSet()){
				continue;
			}
			prop.setAccessName((accessName==null ? "" : accessName + ".") + f.getName());
			prop.setClassName(c.getCanonicalName());
			if(!prop.isTransiented()){
				propBag.add(prop);
			}
		}
		
	}
	
	/**
	 * 检查是否为忽略字段 
	 * @param name 字段名
	 * @return boolean 是否忽略结果
	 */
	private boolean isIgnoreProp(Class c ,String name) {
		//if(c==Unit.class && name.equals("id")){
		//	return false;
		//}
		return propsIgnoreOfComplexObject!=null && propsIgnoreOfComplexObject.contains(name);
	}
	
	private void parseTypeDescAndNullable(ModelProp prop,Class c,String fieldName){
		String methodName = fieldNameToMethodName(fieldName);
		Method method = null;
		try {
			method =  c.getDeclaredMethod(methodName);
		} catch (Exception e) {
			methodName = "is"+methodName.substring(3);
			try {
				method =  c.getDeclaredMethod(methodName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(method==null){//没找到对应的方法
			prop.setTypeDesc("");
			prop.setNullable(true);
			return;
		}
		
		
		Transient transientAnn = method.getAnnotation(Transient.class);
		prop.setTransiented(transientAnn!=null);
		
		//默认是属性所在类本身，下面有可能会被覆盖
		prop.setPropClassName(c.getCanonicalName());
		
		Class returnType = method.getReturnType();
		prop.setTypeDesc(returnType.getCanonicalName());
		//普通类型
		Column col = method.getAnnotation(Column.class);
		if(col!=null){
			prop.setNullable(col.nullable());
		}
		
		Enumerated  enumerated = method.getAnnotation(Enumerated.class);
		if(enumerated!=null){
			prop.setType(ModelPropType.ENUM);
		}
		JoinColumn joinCol = method.getAnnotation(JoinColumn.class);
		if(joinCol!=null){
			prop.setNullable(joinCol.nullable());
			prop.setTypeDesc(prop.getTypeDesc());
			prop.setType(ModelPropType.MANY2ONE);
			return;
		}
		
		JoinTable joinTable = method.getAnnotation(JoinTable.class);
		ManyToMany manyToMany  = method.getAnnotation(ManyToMany.class);
		if(joinTable!=null){
			prop.setNullable(true);
			prop.setTypeDesc(prop.getTypeDesc());
			prop.setType(ModelPropType.MANY2MANY);
			if(manyToMany!=null){
				prop.setPropClassName(manyToMany.targetEntity().getCanonicalName());
				prop.setSet(true);
			}
			return;
		}
		OneToMany oneToMany = method.getAnnotation(OneToMany.class);
		if(oneToMany!=null){
			prop.setNullable(true);
			prop.setTypeDesc(prop.getTypeDesc());
			prop.setType(ModelPropType.ONE2MANY);
			prop.setPropClassName(oneToMany.targetEntity().getCanonicalName());
			prop.setMappedBy(oneToMany.mappedBy());
			prop.setSet(true);
			return;
		}
		prop.setNullable(true);
	}
	private String getAvaliableEnumValues(Class returnType) {
		StringBuffer res = new StringBuffer();
		int i = 0;
		for(Object obj : returnType.getEnumConstants()){
			try {
				if(i>0){
					res.append(",");
				}
				res.append(obj).append("=").append(PropUtil.getValue(obj, "description"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		return res.toString();
	}
	
	private  String fieldNameToMethodName(String fieldName){
		if(fieldName.length()<=1){
			return "get" + fieldName.toUpperCase();
		}
		return "get" + fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}
	
	public static void main(String[] args) {
		PropParser parser = new PropParser();
		for(ModelProp prop : parser.parseProp("me.huqiao.wss.expandimp.Teacher")){
			System.out.println("["+prop.getPropClassName()+"]"+prop.getAccessName() + "-" + prop.getName() + "- isSet:" + prop.isSet());
			if(prop.getChildren()!=null){
				for(ModelProp child : prop.getChildren()){
					System.out.println("["+child.getPropClassName()+"]"+child.getAccessName() + "-" + child.getName());
				}
			}
		}
	}
	
	public void ignoreSet(boolean ignoreSet){
		this.ignoreSet = ignoreSet;
	}
	
	public boolean isIgnoreSet(){
		return ignoreSet;
	}
	
}
