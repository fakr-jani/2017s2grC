package ro.msg.edu.business.user.boundary;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.dto.UserDTO;

public class UserFacadeTest extends AbstractIntegrationTest {

	@EJB
	private UserFacade sut;

	@Test
	public void createUser_succesfull() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("llalaldadaa@msggroup.com");

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertNotNull("The newly persisted user should have an id!", createdUser.getId());
	}

	@Test
	public void createUser_ActiveByDefault() throws BusinessException {
		UserDTO testUser = new UserDTO();

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertTrue("The newly persisted user should be active!", createdUser.isActive());
	}

	@Test
	public void deleteUser_test() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setId(1L);
		testUser.setFirstname("Max");
		testUser.setLastname("Mustermann");
		UserDTO updatedUser = sut.deleteUser(testUser);
		Assert.assertFalse(updatedUser.isActive());
	}

	@Test
	public void updateUser_test() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setId(5L);
		testUser.setFirstname("Some firstname");
		testUser.setLastname("Some last name");
		testUser.setEmail("haoosao@yahoo.com");
		testUser.setPassword("password");
		testUser.setPhoneNumber("0788888888");
		testUser.setUsername("user2");

		UserDTO updatedUser = sut.updateUser(testUser);
		Assert.assertEquals("firstname did not change", testUser.getFirstname(), updatedUser.getFirstname());

	}

	@Test
	public void updateUser_testFailure() throws BusinessException {
		UserDTO testUser = new UserDTO();

		testUser.setId(4L);
		testUser.setFirstname("firstname2");
		testUser.setLastname("lastname2");
		testUser.setEmail("email@yahoo.com");
		testUser.setPassword("1234");
		testUser.setPhoneNumber("44444");
		testUser.setUsername("user1");
		try {
			UserDTO updatedUser = sut.updateUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email@yahoo.com", e.getMessage());
			return;
		}
		Assert.fail("Update user should fail!");

	}

}
