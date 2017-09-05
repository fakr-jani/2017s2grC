package ro.msg.edu.business.user.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.persistence.user.entity.User;

public class UserCRUDTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControler sut;

	// @Test
	// public void createUser_EmailValidationFail() throws BusinessException {
	// UserDTO testUser = new UserDTO();
	// testUser.setFirstname("John");
	// testUser.setLastname("Doe");
	// testUser.setEmail("unique@mail.com");
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
	//
	// @Test
	// public void deleteUser_ValidCase() {
	// String username = "janem";
	// User testUser = sut.deleteUser(username);
	//
	// Assert.assertEquals(testUser.isActive(), false);
	//
	// }

	@Test
	public void updateUser_ValidCase() {
		String username = "mustm";
		String firstname = "Nimrod";
		String lastname = "Foldvari";
		String email = "nim@gmail.com";
		String password = "nim";
		String phoneNumber = "0764681743";
		User testUser = sut.updateUser(username, firstname, lastname, email, password, phoneNumber);
		Assert.assertEquals(testUser.getFirstname(), firstname);
	}

}
