package com.DTedutech.task1.Repository;

import com.DTedutech.task1.Entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EventDao extends JpaRepository<Event,Integer> , PagingAndSortingRepository<Event,Integer>{


    Optional<Event> findByIdAndType(Integer id, String type);


    Page<Event> findByType(String type, PageRequest of);
}
