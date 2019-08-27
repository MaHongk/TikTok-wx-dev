package com.stu.mapper;

import java.util.List;

import com.stu.pojo.vo.VideosVO;
import org.apache.ibatis.annotations.Param;

import com.stu.pojo.Videos;
import com.stu.utils.MyMapper;

public interface VideosMapperCustom extends MyMapper<Videos> {
    public List<VideosVO> queryAllVideos(@Param("videoDesc")String videoDesc,@Param("userId") String userId);
    /**
     * @Description: 查询关注的视频
     */
    public List<VideosVO> queryMyFollowVideos(String userId);

    /**
     * @Description: 查询点赞视频
     */
    public List<VideosVO> queryMyLikeVideos(@Param("userId") String userId);
    /**
     * @Description: 对视频喜欢的数量进行累加
     */
    public void addVideoLikeCount(String videoId);

    /**
     * @Description: 对视频喜欢的数量进行累减
     */
    public void reduceVideoLikeCount(String videoId);
}