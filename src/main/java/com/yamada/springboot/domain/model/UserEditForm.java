package com.yamada.springboot.domain.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserEditForm {

	//	ユーザー名
	@NotBlank(message="{require_check}")
	private String userName;

	//	メールアドレス
	@NotBlank(message="{require_check}")
	@Email(message="{email_check}")
	private String mail;

	//	パスワード
	@Pattern(regexp="^[a-zA-Z0-9]{8,}|^$", message="{pattern_check}")
	private String password;

	//	誕生日
	@NotNull(message="{require_check}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

}
