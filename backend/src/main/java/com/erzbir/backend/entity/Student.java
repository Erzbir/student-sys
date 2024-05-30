package com.erzbir.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:30
 */
@Data
@Builder
@TableName("stu")
@ToString
public class Student implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String sex;
    private String major;
    private String grade;
}
