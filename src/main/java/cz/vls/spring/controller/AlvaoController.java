package cz.vls.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cz.vls.spring.model.mssql.ServiceDeskTicket;
import cz.vls.spring.repositories.mssql.ServiceDeskTicketRepository;

@RestController
@RequestMapping(path="/alvao")
public class AlvaoController {

	@Autowired
	private ServiceDeskTicketRepository serviceDeskTicketRepository;
	
	@GetMapping(path="/tickets/{login:.+}")
	public @ResponseBody Iterable<ServiceDeskTicket> getTicketsByLogin( @PathVariable(value = "login") String login ) {
		
		// add VLSCR\
		login = "VLSCR\\" .concat(login);
		
		return serviceDeskTicketRepository.findAllByLogin(login);
		
	}
	
}
