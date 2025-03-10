package com.cleveritgroup.newmovierest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cleveritgroup.newmovierest.model.Movie;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Integer> {
}