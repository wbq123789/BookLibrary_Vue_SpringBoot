package booklibrary.library_backend.service;

import booklibrary.library_backend.entity.database_obj.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BorrowService extends IService<Borrow> {
    List<Object> getBorrowListByUserId(Integer user_id);
}
