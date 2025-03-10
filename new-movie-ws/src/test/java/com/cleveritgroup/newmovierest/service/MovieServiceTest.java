package com.cleveritgroup.newmovierest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cleveritgroup.newmovierest.dao.MovieDAO;
import com.cleveritgroup.newmovierest.exception.MovieAlreadyExistsException;
import com.cleveritgroup.newmovierest.exception.MovieNotFoundException;
import com.cleveritgroup.newmovierest.model.Movie;

public class MovieServiceTest {

    @Mock
    private MovieDAO movieDAO;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieDAO.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        when(movieDAO.findById(1)).thenReturn(Optional.of(movie));

        Optional<Movie> result = movieService.getMovieById(1);
        assertTrue(result.isPresent());
    }

    @Test
    public void testAddMovie() {
        Movie movie = new Movie();
        movie.setTitle("New Movie");
        when(movieDAO.findAll()).thenReturn(Arrays.asList());
        when(movieDAO.save(movie)).thenReturn(movie);

        Movie result = movieService.addMovie(movie);
        assertNotNull(result);
    }

    @Test
    public void testAddMovieAlreadyExists() {
        Movie movie = new Movie();
        movie.setTitle("Existing Movie");
        when(movieDAO.findAll()).thenReturn(Arrays.asList(movie));

        assertThrows(MovieAlreadyExistsException.class, () -> movieService.addMovie(movie));
    }

    @Test
    public void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        when(movieDAO.existsById(1)).thenReturn(true);
        when(movieDAO.save(movie)).thenReturn(movie);

        Movie result = movieService.updateMovie(movie);
        assertNotNull(result);
    }

    @Test
    public void testUpdateMovieNotFound() {
        Movie movie = new Movie();
        movie.setId(1);
        when(movieDAO.existsById(1)).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie(movie));
    }

    @Test
    public void testDeleteMovie() {
        when(movieDAO.existsById(1)).thenReturn(true);
        doNothing().when(movieDAO).deleteById(1);

        assertDoesNotThrow(() -> movieService.deleteMovie(1));
    }

    @Test
    public void testDeleteMovieNotFound() {
        when(movieDAO.existsById(1)).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie(1));
    }
}