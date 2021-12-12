package com.umesh.crm.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.crm.entity.CustomerEntity;
 
 
@Repository
public interface CustomerRepository 
extends CrudRepository<CustomerEntity, Long> {
 
}
