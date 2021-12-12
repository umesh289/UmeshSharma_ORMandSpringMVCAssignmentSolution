package com.umesh.crm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.crm.entity.CustomerEntity;
import com.umesh.crm.exception.RecordNotFoundException;
 
@Service
public class CustomerService {
     
    @Autowired
    CustomerRepository repository;
     
    public List<CustomerEntity> getAllCustomers()
    {
        List<CustomerEntity> result = (List<CustomerEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<CustomerEntity>();
        }
    }
     
    public CustomerEntity getCustomerById(Long id) throws RecordNotFoundException 
    {
        Optional<CustomerEntity> customer = repository.findById(id);
         
        if(customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public CustomerEntity createOrUpdateCustomer(CustomerEntity entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<CustomerEntity> customer = repository.findById(entity.getId());
             
            if(customer.isPresent()) 
            {
            	CustomerEntity newEntity = customer.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteCustomerById(Long id) throws RecordNotFoundException 
    {
        Optional<CustomerEntity> customer = repository.findById(id);
         
        if(customer.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

	
}