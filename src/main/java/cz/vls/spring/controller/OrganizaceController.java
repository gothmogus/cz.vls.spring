package cz.vls.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cz.vls.spring.model.oracle.Lide;
import cz.vls.spring.model.oracle.OrganizacniJednotka;
import cz.vls.spring.repositories.oracle.LideRepository;
import cz.vls.spring.repositories.oracle.OrganizacniJednotkaRepository;

@RestController
@RequestMapping(path="/organization")
public class OrganizaceController {
	
	@Autowired
	private OrganizacniJednotkaRepository ojRepository;
	
	@Autowired
	private LideRepository lideRepository;
	
	@GetMapping(path="/units")
	public @ResponseBody Iterable<OrganizacniJednotka> getAllUnits() {
		
		// This returns a JSON or XML with the users
		return ojRepository.findAll();
		
	}
	
	@GetMapping(path="/people")
	public @ResponseBody Iterable<Lide> getAllPeople() {
		
		return lideRepository.findAll();
		
	}
	
	/*
	@GetMapping(path="/people/{id}")
	public ResponseEntity<Lide> getPersonById(@PathVariable(value = "id") Integer id) {
		
	    Lide person = lideRepository.findById( id );
	    
	    if(person == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body( person );
	}
	*/
	
	@GetMapping(path="/people/{oscislo}")
	public @ResponseBody Iterable<Lide> getPeopleByOscislo( @PathVariable(value = "oscislo") Integer oscislo ) {
		
		return lideRepository.findAllByOscislo(oscislo);
		
	}
	
}
