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

	public Integer selectOneId(String mail);

	public String selectOnePassword(String mail);
	
	public boolean editPlace(String mail, String myPlace);

	public boolean deleteOne(String mail);
	
	public boolean updateOne(User user);

}
