package edu.iis.mto.integrationtest.repository;

import edu.iis.mto.integrationtest.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public List<Person> findByFirstNameLike( String firstName );
}
