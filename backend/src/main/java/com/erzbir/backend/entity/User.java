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
 * @Data: 2024/5/29 09:41
 */
@Data
@TableName("user")
@Builder
@ToString
public class User implements Serializable {
    @TableId(value = "username", type = IdType.INPUT)
    private String username;
    private String password;
}
