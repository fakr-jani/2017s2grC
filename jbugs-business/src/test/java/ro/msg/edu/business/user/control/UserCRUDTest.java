package ro.msg.edu.business.user.control;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
<<<<<<< HEAD
import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.common.exception.TechnicalException;
=======
import ro.msg.edu.business.common.exception.BusinessException;
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
import ro.msg.edu.business.user.dto.UserDTO;

public class UserCRUDTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControl sut;

	@Test
<<<<<<< HEAD
	public void createUser_EmailValidationFail() throws TechnicalException {
=======
	public void createUser_EmailValidationFail() throws BusinessException {
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("unique@mail.com");

<<<<<<< HEAD
		UserDTO createdUser = sut.createUser(testUser);
=======
		try {
			UserDTO createdUser = sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email unique@mail.com", e.getMessage());
			return;
		}
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Mary");
		testUser2.setLastname("Jane");
		testUser2.setEmail("unique@mail.com");

<<<<<<< HEAD
		UserDTO createdUser2 = sut.createUser(testUser2);
=======
		try {
			UserDTO createdUser2 = sut.createUser(testUser2);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email unique@mail.com", e.getMessage());
			return;
		}
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
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
<<<<<<< HEAD
	public void updateUser_Validation() throws TechnicalException {
=======
	public void updateUser_Validation() {
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
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
<<<<<<< HEAD
		} catch (JBugsException e) {
=======
		} catch (BusinessException e) {
>>>>>>> cb0ce5acfce2edaa4ea7666a1d5578bcd568be67
			// TODO Auto-generated catch block
		}

	}

}