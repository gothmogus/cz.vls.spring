package cz.vls.spring.repositories.mssql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.vls.spring.model.mssql.ServiceDeskTicket;

public interface ServiceDeskTicketRepository extends CrudRepository<ServiceDeskTicket, Long> {

	public ServiceDeskTicket findById( Integer id );
	
	@Query(value = "FROM ServiceDeskTicket WHERE UserLogin = :login ORDER BY TicketId DESC")
	public List<ServiceDeskTicket> findAllByLogin( @Param("login") String login );
	
}
