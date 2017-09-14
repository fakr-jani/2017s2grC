package ro.msg.edu.business.user.validator;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;

public class UserValidatorTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControl sut;

	@EJB
	private UserDAO userDAO;

	@EJB
	private BugDAO bugDAO;

	@Test
	public void usernameGenerator_test() throws TechnicalException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Dorin");
		testUser.setLastname("Danescu");
		testUser.setPassword("1234");
		testUser.setUsername("somethig");
		testUser.setPhoneNumber("+40788877697");
		testUser.setEmail("dorin_danescu@msggroup.com");
		String[] nameRoles = { "ADMINISTRATOR" };

		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Dorin");
		testUser2.setLastname("Danescu");
		testUser2.setPassword("1234");
		testUser2.setPhoneNumber("+407888776");
		testUser2.setEmail("dorin_danescu2@msggroup.com");
		UserDTO persisted = sut.createUser(testUser, nameRoles);
		UserDTO persisted2 = sut.createUser(testUser2, nameRoles);

		Assert.assertNotEquals("Users with same name should have different username", persisted.getUsername(),
				persisted2.getUsername());
	}

	@Test(expected = TechnicalException.class)
	public void createUser_EmailValidationFail() throws TechnicalException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setPassword("123");
		testUser.setLastname("Doe");
		testUser.setEmail("john_smith@msgssds.com");
		testUser.setUsername("DoeJohn");
		testUser.setPhoneNumber("+4047500437");
		String[] nameRoles = { "ADMINISTRATOR" };
		sut.createUser(testUser, nameRoles);

	}

}
