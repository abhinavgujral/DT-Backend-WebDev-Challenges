package com.DTedutech.task1.Repository;

import com.DTedutech.task1.Entity.Event;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface EventDao extends JpaRepository<Event,Integer> , PagingAndSortingRepository<Event,Integer>{


    Page<Event> findByType(String type, Pageable p);
}
