package booklibrary.library_backend.service.impl;

import booklibrary.library_backend.entity.database_obj.Borrow;
import booklibrary.library_backend.mapper.BorrowMapper;
import booklibrary.library_backend.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
}
