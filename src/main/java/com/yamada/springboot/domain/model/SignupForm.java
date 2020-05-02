package com.yamada.springboot.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	//	ユーザー名
	@NotBlank
	private String userName;

	//	メールアドレス
	@NotBlank
	@Email
	private String mail;

	//	パスワード
	@Pattern(regexp="^[a-zA-Z0-9]+$")
	private String password;

	//	誕生日
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

}
