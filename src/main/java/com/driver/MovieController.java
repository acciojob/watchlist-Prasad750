package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/movies")
public class MovieController {
@Autowired
MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        movieService.addMovie(movie);
        return new ResponseEntity<>("success", HttpStatus.FOUND);
    }


    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        movieService.addDirector(director);
        return new ResponseEntity<>("success",HttpStatus.FOUND);
    }


    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName)
    {
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>("success",HttpStatus.FOUND);
    }

    @GetMapping("/get-movie-by-name/{movieName}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movieName") String movieName)
    {
        Movie resp=movieService.getMovieByName(movieName);
        return new ResponseEntity<>(resp,HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{directorName}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("directorName") String directorName)
    {
        Director resp= movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(resp,HttpStatus.FOUND);
    }

    @GetMapping("get-movies-by-director-name/{directorName}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("directorName") String directorName)
    {
        List<String> resp=movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(resp,HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> resp=movieService.findAllMovies();
        return new ResponseEntity<>(resp,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName)
    {
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("success",HttpStatus.FOUND);
    }

    public ResponseEntity<String> deleteAllDirectors()
    {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("success",HttpStatus.FOUND);
    }

}
