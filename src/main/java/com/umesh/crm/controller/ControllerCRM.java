package com.umesh.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umesh.crm.entity.CustomerEntity;
import com.umesh.crm.exception.RecordNotFoundException;
import com.umesh.crm.services.CustomerService;

@Controller
@RequestMapping("/")
public class ControllerCRM {

	@Autowired
	CustomerService service;


	@RequestMapping(path = {"/"})
	public String getAllCustomers(Model model) {
		List<CustomerEntity> list = service.getAllCustomers();

		model.addAttribute("customers", list);
		return "list-customers";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editCustomerById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			CustomerEntity entity = service.getCustomerById(id.get());
			model.addAttribute("customer", entity);
		} else {
			model.addAttribute("customer", new CustomerEntity());
		}
		return "add-edit-customer";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteCustomerById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createCustomer", method = RequestMethod.POST)
	public String createOrUpdateCustomer(CustomerEntity customer) {
		service.createOrUpdateCustomer(customer);
		return "redirect:/";
	}
}
