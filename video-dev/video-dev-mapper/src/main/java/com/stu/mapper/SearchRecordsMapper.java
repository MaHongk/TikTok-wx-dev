package com.stu.mapper;

import java.util.List;

import com.stu.pojo.SearchRecords;
import com.stu.utils.MyMapper;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
	
	public List<String> getHotwords();
}