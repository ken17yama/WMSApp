package com.yamada.springboot.domain.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Group {

	//	グループID
	private Integer groupId;

	//	グループ名
	private String groupName;

	//	登録日
	private Timestamp createdAt;

	//	更新日
	private Timestamp updatedAt;
}
