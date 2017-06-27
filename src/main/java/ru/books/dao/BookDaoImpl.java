package ru.books.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.books.entities.Author;
import ru.books.entities.Book;
import ru.books.entities.Genre;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    private List<Book> books;

    @Override
    @Transactional
    public List<Book> getBooks() {
        books=(List<Book>) sessionFactory.getCurrentSession()
                .createCriteria(Book.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
        return books;
    }

    @Override
    public List<Book> getBooks(Author author) {
        return null;
    }

    @Override
    public List<Book> getBooks(String bookName) {
        return null;
    }

    @Override
    public List<Book> getBooks(Genre genre) {
        return null;
    }

    @Override
    public List<Book> getBooks(Character letter) {
        return null;
    }
}
