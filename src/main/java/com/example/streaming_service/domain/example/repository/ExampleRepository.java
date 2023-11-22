package com.example.streaming_service.domain.example.repository;

import com.example.streaming_service.domain.example.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

}
