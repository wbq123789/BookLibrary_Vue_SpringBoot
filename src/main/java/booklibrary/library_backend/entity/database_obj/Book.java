package booklibrary.library_backend.entity.database_obj;

import booklibrary.library_backend.entity.BaseData;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_book")
@AllArgsConstructor
public class Book implements BaseData {
    @TableId(type = IdType.AUTO)
    int bid;
    String title;
    String desc;
    String label;
}
