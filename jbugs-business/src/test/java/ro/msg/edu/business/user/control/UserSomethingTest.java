package ro.msg.edu.business.user.control;

import javax.ejb.EJB;

import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.BusinessException;

public class UserSomethingTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDContol sut;

	@Test
	public void createUser_EmailValidationFail() throws BusinessException {
		// UserDTO testUser = new UserDTO();
		// testUser.setFirstname("John");
		// testUser.setLastname("Doe");
		// testUser.setEmail("notnull@mail.com");
		//
		// UserDTO createdUser = sut.createUser(testUser);
		//
		// UserDTO testUser2 = new UserDTO();
		// testUser2.setFirstname("Mary");
		// testUser2.setLastname("Jane");
		// testUser2.setEmail("unique@mail.com");
		//
		// try {
		// UserDTO createdUser2 = sut.createUser(testUser2);
		// } catch (BusinessException e) {
		// Assert.assertEquals("User already exists with given email
		// unique@mail.com", e.getMessage());
		// return;
		// }
		// Assert.fail("Email validation should fail!");
		// }

	}
}
