package com.stu.mapper;

import java.util.List;

import com.stu.pojo.Comments;
import com.stu.pojo.vo.CommentsVO;
import com.stu.utils.MyMapper;

public interface CommentsMapperCustom extends MyMapper<Comments> {
	
    public List<CommentsVO> queryComments(String videoId);
}