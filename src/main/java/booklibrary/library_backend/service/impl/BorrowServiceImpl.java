package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.mapper.BorrowMapper;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
    @Resource
    BorrowMapper borrowMapper;
    @Override
    public List<Object> getBorrowListByUserId(Integer user_id) {
        QueryWrapper<Borrow> wrapper=new QueryWrapper<>();
        wrapper
                .select("bid")
                        .eq("user_id",user_id);
        return Collections.singletonList(borrowMapper.selectList(wrapper));
    }

}
