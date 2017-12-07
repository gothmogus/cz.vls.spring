package cz.vls.spring.repositories.mssql;

import org.springframework.data.repository.CrudRepository;

import cz.vls.spring.model.mssql.Service;

public interface ServiceRepository extends CrudRepository<Service, Long> {

}
