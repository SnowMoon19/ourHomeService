package com.fxj.recordmanager.service.record;

import com.fxj.recordmanager.common.results.Result;

import java.util.Map;

public interface FirstRecordService {
    Result getFirstRecordListByUserId(Map<String, Object> paramMap);

    Result addFirstRecord(Map<String, Object> paramMap);

    Result updateFirstRecord(Map<String, Object> paramMap);
}
