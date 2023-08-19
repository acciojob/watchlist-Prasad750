package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movieDB=new HashMap<>();

    HashMap<String,Director> directorDb=new HashMap<>();

    HashMap<String, List<String>> movieDirectPairDb=new HashMap<>();

    public void addMovie(Movie movie) {
        movieDB.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        directorDb.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(movieDB.containsKey(movieName) && directorDb.containsKey(directorName))
        {
            List<String> movieList=new ArrayList<>();

            if(movieDirectPairDb.containsKey(directorName))
            {
                movieList=movieDirectPairDb.get(directorName);
            }
            movieList.add(movieName);
            movieDirectPairDb.put(directorName,movieList);
        }
    }


    public Movie getMovieByName(String movieName) {
        return movieDB.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return directorDb.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> movieList=new ArrayList<>();
        if(movieDirectPairDb.containsKey(directorName))
        {
            movieList=movieDirectPairDb.get(directorName);
        }
        return movieList;
    }

    public List<String> findAllMovies() {

        return new ArrayList<>(movieDB.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movieList;
        if(movieDirectPairDb.containsKey(directorName))
        {
            movieList=movieDirectPairDb.get(directorName);

            for(String movie:movieList)
            {
                movieDB.remove(movie);
            }
            movieDirectPairDb.remove(directorName);
        }
        directorDb.remove(directorName);
    }

    public void deleteAllDirectors() {

        HashSet<List<String>> movieSet=new HashSet<>();
        for (String directors:movieDirectPairDb.keySet())
        {
            movieSet.add(movieDirectPairDb.get(directors));
        }

        for(List<String> list:movieSet)
        {
            for (String movie:list)
            {
                movieDB.remove(movie);
            }
        }

        directorDb.clear();
    }
}
