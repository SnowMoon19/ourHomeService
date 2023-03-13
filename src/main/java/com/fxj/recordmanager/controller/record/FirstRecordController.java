package com.fxj.recordmanager.controller.record;

import com.fxj.recordmanager.common.results.Result;
import com.fxj.recordmanager.entity.dao.record.FirstRecord;
import com.fxj.recordmanager.service.record.FirstRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/record/firstRecord")
@CrossOrigin
public class FirstRecordController {
    @Autowired
    private FirstRecordService firstRecordServiceImpl;

    /**
     * 通过用户id获取第一次记录列表
     */
    @PostMapping("/getFirstRecordListById")
    public Result getFirstRecordListByUserId(@RequestParam Integer userId,
                                             @RequestParam Integer anotherId) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("userId", userId);
        paramMap.put("anotherId", anotherId);
        return firstRecordServiceImpl.getFirstRecordListByUserId(paramMap);
    }

    /**
     * 添加一条第一次记录
     */
    @PostMapping("/addFirstRecord")
    public Result addFirstRecord(@RequestBody FirstRecord firstRecord) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("firstRecord", firstRecord);
        return firstRecordServiceImpl.addFirstRecord(paramMap);
    }

    /**
     * 确认一条第一次记录，待完成->已完成
     */
    @PostMapping("/confirmTodoFirstRecord")
    public Result updateFirstRecord(@RequestBody FirstRecord firstRecord) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("firstRecord", firstRecord);
        return firstRecordServiceImpl.updateFirstRecord(paramMap);
    }
}
