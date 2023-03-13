package com.fxj.recordmanager.controller.record;

import com.fxj.recordmanager.common.results.Result;
import com.fxj.recordmanager.entity.dao.record.Album;
import com.fxj.recordmanager.service.record.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/record/photos")
@CrossOrigin
public class PhotoController {

    @Autowired
    private PhotoService photoServiceImpl;

    /**
     * 通过用户id获取第一次记录列表
     */
    @PostMapping("/getAlbumListById")
    public Result getFirstRecordListByUserId(@RequestParam Integer userId,
                                             @RequestParam Integer anotherId) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("userId", userId);
        paramMap.put("anotherId", anotherId);
        return photoServiceImpl.getAlbumListByUserId(paramMap);
    }

    /**
     * 添加一条第一次记录
     */
    @PostMapping("/addAlbum")
    public Result addFirstRecord(@RequestBody Album album) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("album", album);
        return photoServiceImpl.addAlbum(paramMap);
    }


    /**
     * 获取照片列表
     */
    @PostMapping("/getPhotoList")
    public Result getPhotoList(@RequestParam Integer albumId) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("albumId", albumId);
        return photoServiceImpl.getPhotoList(paramMap);
    }

    /**
     * 上传照片列表
     */
    @PostMapping("/uploadPhotoList")
    public Result uploadPhotoList(@RequestParam("file") MultipartFile[] file,
                                  @RequestParam("creatorId") Integer creatorId,
                                  @RequestParam("albumId") Integer albumId) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("albumId", albumId);
        paramMap.put("file", file);
        paramMap.put("creatorId", creatorId);
        return photoServiceImpl.uploadPhotoList(paramMap);
    }


}
