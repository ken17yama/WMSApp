package com.yamada.springboot.domain.service;

import java.util.List;

import com.yamada.springboot.domain.model.Group;

public interface GroupService {
	
	public boolean insertOne(Group group);
	
	public Integer insertOneReturn(Group group);
	
	public List<Group> selectMany();

	public List<Group> selectThat(List<Integer> myPlaceIntList);
	
	public Group selectOne(Integer groupId);
	
	public boolean deleteOne(Integer groupId);

}
