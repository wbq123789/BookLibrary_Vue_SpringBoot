/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.mapper.BorrowMapper;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
/**
* @Description: Mybatis_PlusService->BorrowService实现类
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
    /**
     * @Description: 获取用户借阅信息
     * @Param: [user_id]
     * @return: List<borrow>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @Override
    public List<Borrow> getBorrowListByUserId(Integer user_id) {
        QueryWrapper<Borrow> wrapper=new QueryWrapper<>();
        wrapper.select("bookId","borrowTime")
                .eq("userId",user_id);
        return this.list(wrapper);
    }

}
