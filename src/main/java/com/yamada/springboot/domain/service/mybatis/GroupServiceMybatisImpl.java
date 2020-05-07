package com.yamada.springboot.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yamada.springboot.domain.model.Group;
import com.yamada.springboot.domain.repository.mybatis.GroupMapper;
import com.yamada.springboot.domain.service.GroupService;

@Transactional
@Service("GroupServiceMybatisImpl")
public class GroupServiceMybatisImpl implements GroupService {
	
	@Autowired
	GroupMapper groupMapper;

	@Override
	public boolean insertOne(Group group) {
		return groupMapper.insertOne(group);
	}

	@Override
	public Integer insertOneReturn(Group group) {
		groupMapper.insertOneReturn(group);

		return group.getGroupId();
	}

	@Override
	public List<Group> selectMany() {
		return groupMapper.selectMany();
	}

	@Override
	public List<Group> selectThat(List<Integer> myPlaceIntList) {
		return groupMapper.selectThat(myPlaceIntList);
	}

	@Override
	public Group selectOne(Integer groupId) {
		return groupMapper.selectOne(groupId);
	}
	
	@Override
	public boolean deleteOne(Integer groupId) {
		return groupMapper.deleteOne(groupId);
	}

}
