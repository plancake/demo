package com.danieleocchipinti.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    public List<Document> findByFilename(String filename);
    
    public List<Document> findAllByOrderByUploadedAtDesc();
    
    Document findOneById(int id);
}