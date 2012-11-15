package org.springframework.samples.petclinic.dbsetup;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:org/springframework/samples/petclinic/AbstractClinicTests-context.xml"})
public class HibernateDbSetupTests {

	
	@Autowired DataSource dataSource;
	
	
	@Before
    public void prepare() throws Exception {
		Operation operation = sequenceOf(
				CommonOperations.DELETE_ALL,
				Operations.insertInto("specialties")
						.columns("id", "name")
						.values(1L, "Matthieu")
						.values(2L, "Nat")
						.build());

		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
    }
	

	@Test
	public void should_work() {
		// Given
		
		// When
		
		// Then
	}
	
}
