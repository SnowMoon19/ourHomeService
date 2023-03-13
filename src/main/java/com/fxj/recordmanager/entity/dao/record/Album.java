package com.fxj.recordmanager.entity.dao.record;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@TableName("record_album")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album {
    @TableId(value = "albumId", type = IdType.AUTO)
    private Integer albumId;

    private String name;

    private Integer creatorId;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
