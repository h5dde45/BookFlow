package ru.books.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.books.dao.interfaces.BookDao;
import ru.books.entities.Book;

import java.util.List;

@Component
public class LibraryFacade {
    private BookDao bookDao;
    private List<Book> books;

    @Autowired
    public void setBookDao(BookDao bookDao){
        this.bookDao=bookDao;
        books=bookDao.getBooks();
    }

    public List<Book> getBooks() {
        return books;
    }
}
