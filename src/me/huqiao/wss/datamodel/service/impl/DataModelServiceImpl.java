package me.huqiao.wss.datamodel.service.impl;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import me.huqiao.wss.datamodel.ModelProp;
import me.huqiao.wss.datamodel.PropUtil;
import me.huqiao.wss.datamodel.service.IDataModelService;

import org.springframework.stereotype.Service;
/**
 * 数据模型实现
 * @author 
 * Mon Jan 26 14:23:38 CST 2015
 */
@Service
public class DataModelServiceImpl implements IDataModelService {
	
	public List<ModelProp> getDataModelAttributes(String dataModel){
		List<ModelProp> res = new ArrayList<ModelProp>();
		try {
			Class c = Class.forName(dataModel);
			traceForProp(res,c);
		} catch (ClassNotFoundException e) {
		}
		return res;
	}
	
	private void traceForProp(List<ModelProp> bag,Class c){
		if(c.getSuperclass()!=null){
			traceForProp(bag,c.getSuperclass());
		}
		for(Field f : c.getDeclaredFields()){
			ModelProp prop = new ModelProp(f.getName(),"props." + c.getCanonicalName() + "." + f.getName());
			parseTypeDescAndNullable(prop,c,f.getName());
			bag.add(prop);
		}
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
		
		Class returnType = method.getReturnType();
		
		Column col = method.getAnnotation(Column.class);
		if(col!=null){
			String typeDesc = "";
			if(returnType.isEnum()){
				String avaliableEnumValue = getAvaliableEnumValues(returnType);
				typeDesc+="枚举值,可用枚举值：" + avaliableEnumValue;
			}else{
				typeDesc = returnType.getCanonicalName();
			}
			prop.setTypeDesc(typeDesc);
			prop.setNullable(col.nullable());
			return;
		}
		prop.setTypeDesc(returnType.getCanonicalName());
		JoinColumn joinCol = method.getAnnotation(JoinColumn.class);
		if(joinCol!=null){
			prop.setNullable(joinCol.nullable());
			prop.setTypeDesc(prop.getTypeDesc()+",多对一");
			return;
		}
		JoinTable joinTable = method.getAnnotation(JoinTable.class);
		if(joinTable!=null){
			prop.setNullable(true);
			prop.setTypeDesc(prop.getTypeDesc()+",多对多");
			return;
		}
		OneToMany oneToMany = method.getAnnotation(OneToMany.class);
		if(oneToMany!=null){
			prop.setNullable(true);
			prop.setTypeDesc(prop.getTypeDesc()+",一对多");
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
	
}
