package com.greatlearning.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.library.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	Optional<Customer> findByUsername(String username);

}
