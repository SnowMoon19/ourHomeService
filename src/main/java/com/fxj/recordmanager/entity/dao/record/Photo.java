package com.fxj.recordmanager.entity.dao.record;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@TableName("record_photo")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {
    private Integer photoId;

    private Integer albumId;

    private String photoUrl;

    private Integer creatorId;

    private String extra;

    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
