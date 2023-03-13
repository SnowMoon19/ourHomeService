package com.fxj.recordmanager.mapper.record;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxj.recordmanager.entity.dao.record.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoMapper extends BaseMapper<Photo> {

    /**
     * 获取照片列表
     * @param albumId 相册id
     * @return 照片列表
     */
    List<Photo> getPhotoListByAlbumId(Integer albumId);

}
