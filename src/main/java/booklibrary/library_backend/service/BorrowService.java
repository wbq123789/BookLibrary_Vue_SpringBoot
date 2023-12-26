/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.service;

import booklibrary.library_backend.entity.database_obj.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
* @Description: Mybatis_PlusService->BorrowService
* @Author: 王贝强
* @Date: 2023/12/26
*/
public interface BorrowService extends IService<Borrow> {
    List<Borrow> getBorrowListByUserId(Integer user_id);
}
