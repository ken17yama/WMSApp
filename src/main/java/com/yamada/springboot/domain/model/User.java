package com.yamada.springboot.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	//	ID
	private Integer userId;
	//	ユーザー名
	private String userName;
	//	メールアドレス
	private String mail;
	//	パスワード
	private String password;
	//	誕生日
	private Date birthday;
	//	権限
	private String role;

}
