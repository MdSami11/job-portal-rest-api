package com.example.jobportal.repository;


import com.example.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


    List<Job> findByTitleContainingIgnoreCase(String keyword);


    List<Job> findByLocationContainingIgnoreCase(String location);


}