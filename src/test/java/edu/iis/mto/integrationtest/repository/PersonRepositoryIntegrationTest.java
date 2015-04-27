package edu.iis.mto.integrationtest.repository;

import edu.iis.mto.integrationtest.model.Person;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.assertEquals;

public class PersonRepositoryIntegrationTest extends IntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testCanAccessDbAndGetTestData() {
        List<Person> foundTestPersons = personRepository.findAll();
        assertEquals( 2, foundTestPersons.size() );
    }

    @DirtiesContext
    @Test
    public void testSaveNewPersonAndCheckIsPersisted() {
        long count = personRepository.count();
        personRepository.save( a( person().withId( count + 1 )
                .withFirstName( "Roberto" ).withLastName( "Mancini" ) ) );
        assertEquals( count + 1, personRepository.count() );
        assertEquals( "Mancini", personRepository.findOne( count + 1 )
                .getLastName() );
    }

    @DirtiesContext
    @Test
    public void testDeletePerson_CheckRepositoryCountEquals1() {
        long count = personRepository.count();
        personRepository.delete( count );
        assertEquals( count - 1, personRepository.count() );
    }

    private Person a( PersonBuilder builder ) {
        return builder.build();
    }

}
