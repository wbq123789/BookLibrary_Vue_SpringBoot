/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
* @Description: MybatisMapper->BorrowMapper
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
