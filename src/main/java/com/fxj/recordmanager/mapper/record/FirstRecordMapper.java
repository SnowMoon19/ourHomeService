package com.fxj.recordmanager.mapper.record;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxj.recordmanager.entity.dao.record.FirstRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstRecordMapper extends BaseMapper<FirstRecord> {

    /**
     * 获取第一次记录列表
     * @param userId 用户id
     * @param anotherId 另一半id
     * @return 第一次记录列表
     */
    List<FirstRecord> getFirstRecordListByUserId(Integer userId, Integer anotherId);


    /**
     * 获取一条第一次记录
     * @param recordId 记录id
     * @return 对应的第一次记录
     */
    FirstRecord getFirstRecord(Integer recordId);

    /**
     * 更新一条第一次记录
     * @param firstRecord 待更新的第一次记录
     * @return 成功数
     * todo mybatis <set>每一个更新段后要加','最后的','，会自动过滤掉，不用担心全为null时SQL语句出错
     */
    int updateFirstRecord(FirstRecord firstRecord);
}
