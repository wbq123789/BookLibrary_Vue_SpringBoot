/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.database_obj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: 数据库借阅信息表
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
@Accessors(chain = true)
@TableName("db_borrow")
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer userId;
    Integer bookId;
    Date borrowTime;
}
