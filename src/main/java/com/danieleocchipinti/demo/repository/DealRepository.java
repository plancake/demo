package com.danieleocchipinti.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieleocchipinti.demo.entity.Deal;
import com.danieleocchipinti.demo.entity.Document;
import com.danieleocchipinti.demo.entity.User;

@Repository
public interface DealRepository extends CrudRepository<Deal, Long> {
    
	public Deal findOneById(int id);
    
    public List<Deal> findAllBySeller(User seller);
}