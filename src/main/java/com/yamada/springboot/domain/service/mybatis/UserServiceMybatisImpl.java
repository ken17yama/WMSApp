package com.yamada.springboot.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.repository.mybatis.UserMapper;
import com.yamada.springboot.domain.service.UserService;

@Transactional
@Service("UserServiceMybatisImpl")
public class UserServiceMybatisImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public boolean insertOne(User user) {
		return userMapper.insertOne(user);
	}

	@Override
	public int count() {
		return userMapper.count();
	}

	@Override
	public List<User> selectMany() {
		return userMapper.selectMany();
	}

	@Override
	public User selectOne(String mail) {
		return userMapper.selectOne(mail);
	}

	@Override
	public Integer selectOneId(String mail) {
		return userMapper.selectOneId(mail);
	}

	@Override
	public String selectOnePassword(String mail) {
		return userMapper.selectOnePassword(mail);
	}

	@Override
	public boolean editPlace(String mail, String myPlace) {
		return userMapper.editPlace(mail, myPlace);
	}

	@Override
	public boolean deleteOne(String mail) {
		return userMapper.deleteOne(mail);
	}
	
	@Override
	public boolean updateOne(User user) {
		return userMapper.updateOne(user);
	};

}
