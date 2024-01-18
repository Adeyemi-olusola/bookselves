package com.example.CRUDApplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.model.Books;
import com.example.CRUDApplication.repo.bookRepo;

@RestController
public class BookController {
    @Autowired
    private bookRepo bookRe;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Books>> getAllBooks() {

        try {
            List<Books> bookList = new ArrayList<>();
            bookRe.findAll().forEach(bookList::add);
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/getBookById{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Optional<Books> bookData = bookRe.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addBook")
    public ResponseEntity<Books> addBook(@RequestBody Books book) {
        Books bookObj = bookRe.save(book);
        
        return new ResponseEntity<>(bookObj, HttpStatus.OK);

    }

    // @PostMapping("/updateBookById/{id}")
    // public ResponseEntity<Books> updateBookbyId(@PathVariable Long id,@RequestBody Books newBookData) {

    //     Optional<Books> oldBookData = bookRe.findById(id);
    //     if(oldBookData.isPresent()){
    //       Books updatedBookData =  oldBookData.get();
    //      updatedBookData.setTitle(null);




    //     }


    // }

    @DeleteMapping("/deleteBook/")
    public ResponseEntity<Books> deleteBookById(@PathVariable Long id) {
        bookRe.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.OK);




    }
}
