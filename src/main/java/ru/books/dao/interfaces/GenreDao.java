package ru.books.dao.interfaces;

import ru.books.entities.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getGenres();
}
