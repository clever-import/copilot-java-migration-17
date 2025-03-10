package com.cleveritgroup.newmovierest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cleveritgroup.newmovierest.dao.MovieDAO;
import com.cleveritgroup.newmovierest.exception.MovieAlreadyExistsException;
import com.cleveritgroup.newmovierest.exception.MovieNotFoundException;
import com.cleveritgroup.newmovierest.model.Movie;

@Service
public class MovieService implements MovieServiceInt {

    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return movieDAO.findById(id);
    }

    @Override
    public Movie addMovie(Movie movie) {
        boolean movieExists = movieDAO.findAll().stream()
            .anyMatch(existingMovie -> existingMovie.getTitle().equalsIgnoreCase(movie.getTitle()));
        if (movieExists) {
            throw new MovieAlreadyExistsException("Movie with the same name already exists.");
        }
        return movieDAO.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        if (!movieDAO.existsById(movie.getId())) {
            throw new MovieNotFoundException("Movie not found.");
        }
        return movieDAO.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
        if (!movieDAO.existsById(id)) {
            throw new MovieNotFoundException("Movie not found.");
        }
        movieDAO.deleteById(id);
    }
}