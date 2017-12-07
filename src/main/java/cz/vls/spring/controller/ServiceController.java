package cz.vls.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.vls.spring.model.mssql.Service;
import cz.vls.spring.repositories.mssql.ServiceRepository;

@Controller
@RequestMapping(path="/services")
public class ServiceController {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@RequestMapping(path="/catalogue")
	public String getAllServices( Model model ) {
		
		Iterable<Service> services = serviceRepository.findAll();
		
        model.addAttribute("services", services);
        return "catalogue";
    }
	
}
