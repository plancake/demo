package com.danieleocchipinti.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.entity.DocumentView;

@Repository
public interface DocumentViewRepository extends CrudRepository<DocumentView, Long> {
    
	List<DocumentView> findAllByDocument(Document document);
    
    DocumentView findOneById(int id);
}