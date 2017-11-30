package cz.vls.spring.repositories.mssql;

import org.springframework.data.repository.CrudRepository;

import cz.vls.spring.model.mssql.UserCard;

public interface UserCardRepository extends CrudRepository<UserCard, Long> {

}
