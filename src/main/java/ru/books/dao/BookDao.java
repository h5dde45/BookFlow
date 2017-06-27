package ru.books.dao;

import ru.books.entities.Author;
import ru.books.entities.Book;
import ru.books.entities.Genre;

import java.util.List;

public interface BookDao {

    List<Book> getBooks();
    List<Book> getBooks(Author author);
    List<Book> getBooks(String bookName);
    List<Book> getBooks(Genre genre);
    List<Book> getBooks(Character letter);

}
