package booklibrary.library_backend.service;

import booklibrary.library_backend.entity.database_obj.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookService extends IService<Book> {
    public List<Book> getBookListNotBorrow();
}
