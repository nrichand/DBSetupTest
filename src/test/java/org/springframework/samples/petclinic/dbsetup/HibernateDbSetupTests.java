package org.springframework.samples.petclinic.dbsetup;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import java.util.Collection;

import javax.sql.DataSource;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.Clinic;
import org.springframework.samples.petclinic.Specialty;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		//"classpath:org/springframework/samples/petclinic/AbstractClinicTests-context.xml",
		"classpath:org/springframework/samples/petclinic/jpa/applicationContext-entityManager.xml",
		"classpath:org/springframework/samples/petclinic/jpa/applicationContext-hibernateAdapter.xml",
		"classpath:org/springframework/samples/petclinic/jpa/applicationContext-jpaCommon.xml"
		})
public class HibernateDbSetupTests {

	
	@Autowired DataSource dataSource;
	
	@Autowired
	@Qualifier("clinic")
	Clinic dao;
	
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
		// When
		Collection<Specialty> specialty = dao.findSpecialty("Nat");
		
		// Then
		Assertions.assertThat( specialty).isNotNull();
	}
	
}
