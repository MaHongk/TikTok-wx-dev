package com.stu.service;

import com.stu.pojo.Users;
import com.stu.utils.PagedResult;

public interface UsersService {

	public PagedResult queryUsers(Users user, Integer page, Integer pageSize);
	
}
