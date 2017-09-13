package ro.msg.edu.business.user.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dto.UserDTO;

public class UserCRUDControlTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControl sut;


	@Test
	public void createUser_EmailValidationFail() throws TechnicalException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setPassword("123");
		testUser.setLastname("Doe");
		testUser.setEmail("john_s@msggroup.com");

		try {
			UserDTO createdUser = sut.createUser(testUser);
		} catch (Exception e) {
			Assert.assertEquals("User already exists with given email john_s@msggroup.com", e.getMessage());
		}
	}


	@Test
	public void deleteUser_Success() {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Mary");
		testUser.setPassword("123");
		testUser.setLastname("Dan");
		testUser.setEmail("mary_doe@msggroup.com");

		try {
			UserDTO persisted = sut.createUser(testUser);
			UserDTO deletedUser = sut.deleteUser(persisted);
			Assert.assertEquals(false, deletedUser.isActive());
		} catch (JBugsException e) {
		}
	}


	@Test
	public void verifyUserExists_Success() throws JBugsException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Marta");
		testUser.setLastname("Doe");
		testUser.setUsername("MarthaDoe");
		testUser.setPassword("123");
		testUser.setEmail("martha@msggroup.com");

		UserDTO persisted = sut.createUser(testUser);
		boolean existUser = sut.verifyUserExists(persisted);
		Assert.assertEquals(existUser, true);

	}

}