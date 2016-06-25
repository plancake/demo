package com.danieleocchipinti.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.Deal;
import com.danieleocchipinti.demo.entity.Document;

@Repository
public interface DealRepository extends CrudRepository<Deal, Long> {
    
    Deal findOneById(int id);
}