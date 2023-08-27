package com.khayayphyu.entity.security;

import com.khayayphyu.entity.AbstractEntity;
import com.khayayphyu.entity.convertor.BooleanConvertor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="Menu") 
@EqualsAndHashCode(callSuper = true)
public class Menu extends AbstractEntity {

	@Column(name = "Name")
	private String name;

	@Column(name = "SeqNo")
	private Long seqNo;

	@ManyToOne
	@JoinColumn(name = "Parent_Id")
	private Menu parent;

	@Column(name = "Url")
	private String url;
	
	@Column(name="IsParent")
	private Integer isParent;
	
	@Column(name="ParentMenuId")
	private Long parentMenuId;

	@Column(name="Icon")
	private String icon;

	@Column(name="Permission")
	private String permission;
	
	@Column(name="AdminOnly")
	@Convert(converter = BooleanConvertor.class)
	private boolean adminOnly;

}

