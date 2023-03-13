package com.fxj.recordmanager.mapper.record;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxj.recordmanager.entity.dao.record.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumMapper extends BaseMapper<Album> {
    /**
     * 获取用户的相册列表，需要两个人的原因是创建者不一定
     * @param userId 用户id
     * @param anotherId 另一半id
     * @return 相册列表
     */
    List<Album> getAlbumListByUserId(Integer userId, Integer anotherId);

    /**
     * 添加一个相册
     * @param album 相册实体
     * @return 插入返回的相册id
     */
    Integer addAlbum(Album album);

    Integer deleteAlbum(Integer albumId);

}
