package ro.msg.edu.business.user.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dto.UserDTO;

public class UserCRUDTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControl sut;

	@Test
	public void createUser_EmailValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("unique@mail.com");

		try {
			UserDTO createdUser = sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email unique@mail.com", e.getMessage());
			return;
		}
		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Mary");
		testUser2.setLastname("Jane");
		testUser2.setEmail("unique@mail.com");

		try {
			UserDTO createdUser2 = sut.createUser(testUser2);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email unique@mail.com", e.getMessage());
			return;
		}
		Assert.fail("Email validation should fail!");
	}

	@Test
	public void deleteUser_Validation() {
		UserDTO userTest = new UserDTO();
		UserDTO userTest2 = new UserDTO();

		String username = "foldn";
		userTest.setUsername(username);

		userTest2 = sut.deleteUser(userTest);

		Assert.assertEquals(userTest2.isActive(), false);

	}

	@Test
	public void updateUser_Validation() {
		UserDTO testUser = new UserDTO();
		String username = "foldn";
		String firstname = "Harold";
		String lastname = "Nimrod";
		String email = "nimi@gmail.com";
		String password = "pass";
		String phoneNumber = "112";

		testUser.setFirstname(firstname);
		testUser.setLastname(lastname);
		testUser.setUsername(username);
		testUser.setEmail(email);
		testUser.setPassword(password);
		testUser.setPhoneNumber(phoneNumber);

		UserDTO userTest2 = new UserDTO();
		try {
			userTest2 = sut.updateUser(testUser);
			Assert.assertEquals(userTest2.getFirstname(), firstname);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
		}

	}

}