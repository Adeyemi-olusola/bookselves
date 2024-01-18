package com.example.CRUDApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUDApplication.model.Books;

@Repository

public interface bookRepo extends JpaRepository<Books, Long> {

}
