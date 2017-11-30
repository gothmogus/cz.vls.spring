package cz.vls.spring.repositories.oracle;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.vls.spring.model.oracle.Lide;

public interface LideRepository extends CrudRepository<Lide, Long> {
	
	public Lide findByOscislo( Integer oscislo );
	
	public Lide findById( Integer id );
	
	@Query(value = "FROM Lide WHERE OSCISLO = :oscislo ORDER BY PRIJMENI, JMENO")
	public List<Lide> findAllByOscislo( @Param("oscislo") Integer oscislo ); 
}
