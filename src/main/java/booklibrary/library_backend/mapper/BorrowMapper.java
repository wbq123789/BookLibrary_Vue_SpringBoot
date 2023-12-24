package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
