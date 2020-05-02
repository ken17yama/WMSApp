package com.yamada.springboot.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yamada.springboot.domain.model.User;

@Mapper
public interface UserMapper {

	public boolean insertOne(User user);

	public int count();

	public List<User> selectMany();

	public User selectOne(String mail);

	public boolean deleteOne(String mail);

}
