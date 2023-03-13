package com.fxj.recordmanager.service.impl.record;

import com.fxj.recordmanager.common.constants.Constants;
import com.fxj.recordmanager.common.results.Result;
import com.fxj.recordmanager.common.results.ResultCodeEnum;
import com.fxj.recordmanager.entity.dao.record.Album;
import com.fxj.recordmanager.entity.dao.record.Photo;
import com.fxj.recordmanager.mapper.record.AlbumMapper;
import com.fxj.recordmanager.mapper.record.PhotoMapper;
import com.fxj.recordmanager.service.record.PhotoService;
import com.fxj.recordmanager.utils.io.CreateFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Result getAlbumListByUserId(Map<String, Object> paramMap) {
        Integer userId = (Integer) paramMap.get("userId");
        Integer anotherId = (Integer) paramMap.get("anotherId");

        if (userId == null || anotherId == null) {
            logger.error("getAlbumListByUserId -err, userId not found");
            return Result.build(ResultCodeEnum.USER_ID_NOT_FOUND);
        }

        List<Album> albumList = albumMapper.getAlbumListByUserId(userId, anotherId);
        return Result.ok(albumList);
    }

    @Override
    public Result addAlbum(Map<String, Object> paramMap) {
        Album album = (Album) paramMap.get("album");
        if (album.getCreatorId() == null) {
            logger.error("addAlbum -err, creatorId not found, album={}", album);
            return Result.build(ResultCodeEnum.MAIN_ID_NOT_FOUND);
        }

        if (albumMapper.insert(album) != 1) {
            logger.error("addAlbum -err, insert error");
            return Result.build(ResultCodeEnum.SQL_INSERT_ERROR);
        }
        // 创建对应的目录
        String dir = Constants.filePath + album.getAlbumId();
        if (!CreateFileUtil.createDir(dir)) {
            logger.error("addAlbum -err, io error");
            // 创建失败时将数据删除
            albumMapper.deleteAlbum(album.getAlbumId());
            return Result.build(ResultCodeEnum.IO_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result getPhotoList(Map<String, Object> paramMap) {
        Integer albumId = (Integer) paramMap.get("albumId");

        if (albumId == null) {
            logger.error("getPhotoList -err, albumId not found");
            return Result.build(ResultCodeEnum.USER_ID_NOT_FOUND);
        }

        List<Photo> photoList = photoMapper.getPhotoListByAlbumId(albumId);
        return Result.ok(photoList);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Result uploadPhotoList(Map<String, Object> paramMap) {
        MultipartFile[] files = (MultipartFile[]) paramMap.get("file");
        Integer albumId = (Integer) paramMap.get("albumId");
        Integer creatorId = (Integer) paramMap.get("creatorId");
        List<Photo> photoList = new ArrayList<>();

        // 上传照片到指定目录，同时插入数据库
        for (MultipartFile multipartFile : files) {
            // 数据路径，jpg格式图片
            String url = Constants.filePath + albumId + "/"
                    + UUID.randomUUID().toString().replace("-", "").toLowerCase()
                    + Constants.photoType;
            File dest = new File(url);
            Photo photo = new Photo();
            photo.setPhotoUrl(url);
            photo.setAlbumId(albumId);
            photo.setCreatorId(creatorId);
            try {
                multipartFile.transferTo(dest);
                if (photoMapper.insert(photo) != 1) {
                    logger.error("uploadPhotoList -err, insert error");
                    throw new SQLException();
                }
            } catch (IOException | SQLException e) {
                logger.error("uploadPhotoList -err, io error");
                return Result.build(ResultCodeEnum.IO_ERROR);
            }
            photoList.add(photo);
        }
        return Result.ok(photoList);
    }
}
