package com.stu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stu.enums.BGMOperatorTypeEnum;
import com.stu.mapper.BgmMapper;
import com.stu.mapper.UsersReportMapperCustom;
import com.stu.mapper.VideosMapper;
import com.stu.pojo.Bgm;
import com.stu.pojo.BgmExample;
import com.stu.pojo.Videos;
import com.stu.pojo.vo.Reports;
import com.stu.service.VideoService;
import com.stu.utils.JsonUtils;
import com.stu.utils.PagedResult;
import com.stu.web.util.ZKCurator;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private BgmMapper bgmMapper;
    @Autowired
    private Sid sid;
    @Autowired
    private ZKCurator zkCurator;
    @Autowired
    private UsersReportMapperCustom usersReportMapperCustom;

    @Override
    public void addBgm(Bgm bgm) {
        String bgmId = sid.nextShort();
        bgm.setId(bgmId);
        bgmMapper.insert(bgm);
        Map<String, String> map = new HashMap<>();
        map.put("operTyper", BGMOperatorTypeEnum.ADD.type);
        map.put("path", bgm.getPath());
        zkCurator.sendBgmOperator(bgmId, JsonUtils.objectToJson(map));
    }

    @Override
    public PagedResult queryBgmList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        BgmExample example = new BgmExample();
        List<Bgm> list = bgmMapper.selectByExample(example);
        PageInfo<Bgm> pageList = new PageInfo<>(list);

        PagedResult result = new PagedResult();
        result.setTotal(pageList.getPages());
        result.setRows(list);
        result.setPage(page);
        result.setRecords(pageList.getTotal());

        return result;
    }

    @Override
    public void deleteBgm(String id) {
        Bgm bgm = bgmMapper.selectByPrimaryKey(id);
        bgmMapper.deleteByPrimaryKey(id);
        Map<String, String> map = new HashMap<>();
        map.put("operTyper", BGMOperatorTypeEnum.DELETE.type);
        map.put("path", bgm.getPath());
        zkCurator.sendBgmOperator(id, JsonUtils.objectToJson(map));
    }

    @Override
    public PagedResult queryReportList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Reports> reportsList = usersReportMapperCustom.selectAllVideoReport();

        PageInfo<Reports> pageList = new PageInfo<Reports>(reportsList);

        PagedResult grid = new PagedResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(reportsList);
        grid.setPage(page);
        grid.setRecords(pageList.getTotal());

        return grid;
    }

    @Override
    public void updateVideoStatus(String videoId, Integer status) {
        Videos video = new Videos();
        video.setId(videoId);
        video.setStatus(status);
        videosMapper.updateByPrimaryKeySelective(video);
    }
}
