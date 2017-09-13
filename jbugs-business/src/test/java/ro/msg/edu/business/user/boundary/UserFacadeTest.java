package ro.msg.edu.business.user.boundary;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dto.UserDTO;

public class UserFacadeTest extends AbstractIntegrationTest {

	@EJB
	private UserFacade sut;


	@Ignore
	@Test
	public void createUser_succesfull() throws TechnicalException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertNotNull("The newly persisted user should have an id!", createdUser.getId());
	}


	@Ignore
	@Test
	public void createUser_ActiveByDefault() throws TechnicalException {
		UserDTO testUser = new UserDTO();

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertTrue("The newly persisted user should be active!", createdUser.isActive());
	}

}
