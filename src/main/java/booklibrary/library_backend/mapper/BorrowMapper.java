package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
    @Select("select * from db_borrow where user_id = ${uid}")
    List<Borrow> getListBorrowByUid(Integer uid);
}
