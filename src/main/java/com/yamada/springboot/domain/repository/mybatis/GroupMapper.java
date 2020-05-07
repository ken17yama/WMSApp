package com.yamada.springboot.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yamada.springboot.domain.model.Group;

@Mapper
public interface GroupMapper {
	
	public boolean insertOne(Group group);
	
	public Integer insertOneReturn(Group group);
	
	public List<Group> selectMany();
	
	public List<Group> selectThat(List<Integer> myPlaceIntList);
	
	public Group selectOne(Integer groupId);
	
	public boolean deleteOne(Integer groupId);
	
	public boolean updateList(Group group);

}
