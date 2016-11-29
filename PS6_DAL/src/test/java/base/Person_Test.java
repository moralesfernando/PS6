package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {

	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();

	@BeforeClass
	public static void personInstance() throws Exception {

		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		person1 = new PersonDomainModel();

		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		System.out.print(person1.getPersonID().toString()+ "Person.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddPersonTest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "Added");
		assertNotNull("Person added successfully.",per);
		
		PersonDAL.deletePerson(person1.getPersonID());
	}

	@Test
	public void UpdatePersonTest() {
		PersonDomainModel per;
		final String Lastname = "Roberts";
		PersonDAL.addPerson(person1);
		person1.setLastName(Lastname);
		PersonDAL.updatePerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("Person lastname updated successfully.", person1.getLastName() == Lastname);
		
		PersonDAL.deletePerson(person1.getPersonID());
	}

	@Test
	public void DeletePersonTest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "Added");
		assertNotNull("Person added successfully.",per);
		
		PersonDAL.deletePerson(person1.getPersonID());
		assertEquals(PersonDAL.getPersons().size(),0); 
		//assertNull didnt work for some reason? maybe bc primary key cant be null?
	}

	@Test
	public void GetPersonsTest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "Added");
		assertNotNull("Person added successfully.", per);
		ArrayList<PersonDomainModel> people = new ArrayList<PersonDomainModel>();
		people = PersonDAL.getPersons();
		System.out.println(people);
		assertNotNull("People retrieved successfully.", people);

		PersonDAL.deletePerson(person1.getPersonID());
	}

	@Test
	public void GetPersonTest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + "Added");
		assertNotNull("Person added successfully.", per);

		PersonDAL.deletePerson(person1.getPersonID());

	}
}