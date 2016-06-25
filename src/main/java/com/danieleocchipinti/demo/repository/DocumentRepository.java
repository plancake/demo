package com.danieleocchipinti.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danieleocchipinti.demo.entity.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    public List<Document> findByFilename(String filename);
    
    public List<Document> findAllByOrderByUploadedAtDesc();
    
    Document findOneById(int id);
}