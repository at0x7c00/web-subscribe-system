package me.huqiao.wss.sys.entity.propertyeditor;

import java.beans.PropertyEditorSupport;

import me.huqiao.wss.sys.entity.Role;
import me.huqiao.wss.sys.service.IRoleService;
/**
 * 角色编辑器
 * @author NOVOTS
 * @version Version 1.0
 */
public class RoleEditor extends PropertyEditorSupport {
	private IRoleService roleService;

	public RoleEditor(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String getAsText() {
		Role role = (Role) getValue();
		if (role == null)
			return "";
		return String.valueOf(role.getId());
	}

	public void setAsText(String id) throws IllegalArgumentException {
		Role role = new Role();
		Integer integerId = null;
		try {
			integerId = Integer.parseInt(id);
		} catch (Exception e) {
		}
		role = roleService.getById(Role.class, integerId);
		setValue(role);
	}
}