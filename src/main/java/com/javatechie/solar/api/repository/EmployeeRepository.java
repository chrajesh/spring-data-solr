package com.javatechie.solar.api.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.javatechie.solar.api.model.Employee;

public interface EmployeeRepository extends SolrCrudRepository<Employee, Integer>{

	Employee findByName(String name);

}
