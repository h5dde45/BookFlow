package ru.books.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.books.dao.interfaces.BookDao;
import ru.books.entities.Author;
import ru.books.entities.Book;
import ru.books.entities.Genre;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    private ProjectionList bookProjection;

    public BookDaoImpl() {
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"), "id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("image"), "image");
        bookProjection.add(Projections.property("genre"), "genre");
        bookProjection.add(Projections.property("pageCount"), "pageCount");
        bookProjection.add(Projections.property("isbn"), "isbn");
        bookProjection.add(Projections.property("publisher"), "publisher");
        bookProjection.add(Projections.property("author"), "author");
        bookProjection.add(Projections.property("publishYear"), "publishYear");
        bookProjection.add(Projections.property("descr"), "descr");
    }


    @Override
    @Transactional
    public List<Book> getBooks() {
        List<Book> books = createBookList(createBookCriteria());
        return books;
    }

    @Override
    @Transactional
    public List<Book> getBooks(Author author) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions
        .ilike("author.fio",author.getFio(),MatchMode.ANYWHERE)));
        return books;
    }

    @Override
    @Transactional
    public List<Book> getBooks(String bookName) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions
                .ilike("b.name",bookName,MatchMode.ANYWHERE)));
        return books;
    }

    @Override
    @Transactional
    public List<Book> getBooks(Genre genre) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions
                .ilike("genre.name",genre.getName(),MatchMode.ANYWHERE)));
        return books;
    }

    @Override
    @Transactional
    public List<Book> getBooks(Character letter) {
        return null;
    }

    private DetachedCriteria createBookCriteria() {
        DetachedCriteria bookListCriteria =
                DetachedCriteria.forClass(Book.class, "b");
        createAliases(bookListCriteria);
        return bookListCriteria;
    }

    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }

    private List<Book> createBookList(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(
                sessionFactory.getCurrentSession());
        criteria.addOrder(Order.asc("b.name"))
                .setProjection(bookProjection)
                .setResultTransformer(Transformers.
                        aliasToBean(Book.class));
        return criteria.list();
    }
}
