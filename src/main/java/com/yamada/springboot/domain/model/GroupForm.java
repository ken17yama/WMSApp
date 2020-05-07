package com.yamada.springboot.domain.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class GroupForm {

	//	グループ名
	@NotBlank
	private String groupName;

	//	グループリスト
	@NotBlank
	private List<String> groupList;

}
