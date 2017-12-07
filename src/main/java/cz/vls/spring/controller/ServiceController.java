package cz.vls.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cz.vls.spring.model.mssql.Service;
import cz.vls.spring.repositories.mssql.ServiceRepository;

@RestController
@RequestMapping(path="/services")
public class ServiceController {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@GetMapping(path="/catalogue")
	public @ResponseBody Iterable<Service> getAllServices() {
		
		// This returns a JSON or XML with the userCards
		return serviceRepository.findAll();
		
	}
	
}
