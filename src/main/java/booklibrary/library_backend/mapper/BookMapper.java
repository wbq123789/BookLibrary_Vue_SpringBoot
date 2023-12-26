/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.mapper;

import booklibrary.library_backend.entity.database_obj.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
* @Description: MybatisMapper->BookMapper
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
