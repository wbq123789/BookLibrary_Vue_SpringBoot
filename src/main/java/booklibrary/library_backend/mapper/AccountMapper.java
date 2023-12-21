package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
