/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.entity.database_obj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description: 数据库book表
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Data
@TableName("db_book")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @TableId(type = IdType.AUTO)
    Integer bid;
    String author;
    String title;
    @TableField(value = "`desc`")
    String desc;
    String label;
}
