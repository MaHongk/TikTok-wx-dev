package com.stu.service;

import com.stu.pojo.Bgm;
import com.stu.pojo.Users;

import java.util.List;

public interface BgmService {

    //查询背景音乐列表
    public List<Bgm> queryBgmList();
    //根据id查询bgm信息
    public Bgm queryBgmById(String bgmId);

}
