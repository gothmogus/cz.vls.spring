package cz.vls.spring.repositories.mssql;

import org.springframework.data.repository.CrudRepository;

import cz.vls.spring.model.mssql.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByName( String name );
	
}
