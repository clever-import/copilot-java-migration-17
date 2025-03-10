package com.cleveritgroup.newmovierest.service;

import java.util.List;
import java.util.Optional;

import com.cleveritgroup.newmovierest.model.Movie;

public interface MovieServiceInt {
    List<Movie> getAllMovies();
    Optional<Movie> getMovieById(int id);
    Movie addMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(int id);
}
