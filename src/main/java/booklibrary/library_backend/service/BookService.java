package booklibrary.library_backend.service;

import booklibrary.library_backend.entity.database_obj.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import booklibrary.library_backend.entity.view_obj.response.borrowMessageViewObj;
import java.util.List;

public interface BookService extends IService<Book> {

    List<borrowMessageViewObj> getBorrowBookById(int id);
    String getBookCount();
}
