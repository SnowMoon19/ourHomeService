package com.fxj.recordmanager.service.impl.record;

import com.fxj.recordmanager.common.results.Result;
import com.fxj.recordmanager.common.results.ResultCodeEnum;
import com.fxj.recordmanager.entity.dao.record.FirstRecord;
import com.fxj.recordmanager.mapper.record.FirstRecordMapper;
import com.fxj.recordmanager.service.record.FirstRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FirstRecordServiceImpl implements FirstRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FirstRecordMapper firstRecordMapper;

    @Override
    public Result getFirstRecordListByUserId(Map<String, Object> paramMap) {
        Integer userId = (Integer) paramMap.get("userId");
        Integer anotherId = (Integer) paramMap.get("anotherId");

        if (userId == null || anotherId == null) {
            logger.error("getFirstRecordListByUserId -err, userId not found");
            return Result.build(ResultCodeEnum.USER_ID_NOT_FOUND);
        }

        List<FirstRecord> firstRecordList = firstRecordMapper.getFirstRecordListByUserId(userId, anotherId);
        return Result.ok(firstRecordList);
    }

    @Override
    public Result addFirstRecord(Map<String, Object> paramMap) {
        FirstRecord firstRecord = (FirstRecord) paramMap.get("firstRecord");
        if (firstRecord.getCreatorId() == null) {
            logger.error("addFirstRecord -err, creatorId not found, firstRecord={}", firstRecord);
            return Result.build(ResultCodeEnum.MAIN_ID_NOT_FOUND);
        }

        if (firstRecordMapper.insert(firstRecord) != 1) {
            logger.error("addFirstRecord -err, insert error");
            return Result.build(ResultCodeEnum.SQL_INSERT_ERROR);
        }
        return Result.ok();
    }

    @Override
    public Result updateFirstRecord(Map<String, Object> paramMap) {
        FirstRecord firstRecord = (FirstRecord) paramMap.get("firstRecord");
        Integer recordId  = firstRecord.getRecordId();
        FirstRecord origin = firstRecordMapper.getFirstRecord(recordId);
        if (origin == null) {
            logger.error("updateFirstRecord -err, recordId not found, firstRecord={}", firstRecord);
            return Result.build(ResultCodeEnum.MAIN_ID_NOT_FOUND);
        }

        if (firstRecordMapper.updateFirstRecord(firstRecord) != 1) {
            logger.error("updateFirstRecord -err, update error, firstRecord={}", firstRecord);
            return Result.build(ResultCodeEnum.SQL_UPDATE_ERROR);
        }
        return Result.ok();
    }
}
