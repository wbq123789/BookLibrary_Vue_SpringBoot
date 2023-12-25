package booklibrary.library_backend.entity.database_obj;

import booklibrary.library_backend.entity.BaseData;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_book")
@AllArgsConstructor
public class Book{
    @TableId(type = IdType.AUTO)
    Integer bid;
    String author;
    String title;
    @TableField(value = "`desc`")
    String desc;
    String label;
}
