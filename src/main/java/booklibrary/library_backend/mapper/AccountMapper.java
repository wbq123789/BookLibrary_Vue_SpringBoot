/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
* @Description: Mybatis_PlusMapper->AccountMapper
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
