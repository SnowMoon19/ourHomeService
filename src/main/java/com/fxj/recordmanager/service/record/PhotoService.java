package com.fxj.recordmanager.service.record;

import com.fxj.recordmanager.common.results.Result;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PhotoService {
    Result getAlbumListByUserId(Map<String, Object> paramMap);

    Result addAlbum(Map<String, Object> paramMap);

    Result getPhotoList(Map<String, Object> paramMap);

    Result uploadPhotoList(Map<String, Object> paramMap);

}
