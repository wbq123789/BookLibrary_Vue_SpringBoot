package booklibrary.library_backend.entity.database_obj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_borrow")
@AllArgsConstructor
public class Borrow {
    @TableId(type = IdType.AUTO)
    int id;
    int user_id;
    int book_id;
}
