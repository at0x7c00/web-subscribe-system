package me.huqiao.wss.datamodel.service;
import java.util.List;

import me.huqiao.wss.datamodel.ModelProp;
/**
 * 数据模型Service接口
 * @author 
 *  Mon Jan 26 14:22:26 CST 2015
 */
public interface IDataModelService {
	public List<ModelProp> getDataModelAttributes(String dataModel);
}