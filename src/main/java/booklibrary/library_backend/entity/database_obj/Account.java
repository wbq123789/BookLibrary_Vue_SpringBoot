/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.database_obj;

import booklibrary.library_backend.entity.BaseData;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 数据库Account表
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
@TableName("db_account")
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String email;
    String role;
    Date register_time;
}
