package com.khayayphyu.dto.security;

import com.khayayphyu.dto.AbstractDto;
import com.khayayphyu.entity.security.Menu;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDto extends AbstractDto<Menu>{

	private static Long i = 0L;
	
	private String name;
	private String url;
	private String icon;
	private Long seqNo;

	private Integer isParent;
	private Long parentMenuId;
	private boolean authorized;
	
	private String permission;
	
	private MenuDto parentMenu;
	
	private boolean adminOnly;
	
	@Override
	public Menu toEntity() {
		Menu menu = new Menu();
		menu.setId(getId());
		menu.setName(name);
		menu.setUrl(url);
		menu.setIcon(icon);
		menu.setSeqNo(seqNo);
		menu.setIsParent(isParent);
		menu.setParentMenuId(parentMenuId);
		menu.setPermission(permission);
		return menu;
	}
	
	public static MenuDto create(Menu menu) {
		MenuDto menuDto = new MenuDto();
		
		menuDto.setId(menu.getId());
		menuDto.name = menu.getName();
		menuDto.url = menu.getUrl();
		menuDto.icon = menu.getIcon();
		menuDto.seqNo = menu.getSeqNo();
		menuDto.isParent = menu.getIsParent();
		menuDto.parentMenuId = menu.getParentMenuId();
		menuDto.permission = menu.getPermission();
		menuDto.adminOnly = menu.isAdminOnly();
		
		return menuDto;
	}
	
	public boolean isParent() {
		return isParent == 1;
	}

	public static MenuDto create(String name, String url, String icon) {
		MenuDto menu = new MenuDto();
		menu.setId(i++);
		menu.name = name;
		menu.url = url;
		menu.icon= icon;
		menu.isParent = 1;
		menu.authorized = true;
		menu.seqNo = menu.getId();
		return menu;
	}
	
	public static MenuDto create(MenuDto parent, String name, String url, String icon) {
		MenuDto menu = create(name, url, icon);
		menu.isParent = 2;
		menu.parentMenuId = parent.getId();
		return menu;
	}

}
