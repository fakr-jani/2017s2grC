// package ro.msg.edu.business.user.boundary;
//
// import javax.ejb.EJB;
//
// import org.junit.Assert;
// import org.junit.Test;
//
// import ro.msg.edu.business.AbstractIntegrationTest;
// import ro.msg.edu.business.common.exception.BusinessException;
// import ro.msg.edu.business.user.boundary.UserFacade;
// import ro.msg.edu.business.user.dto.UserDTO;
//
// public class UserFacadeTest extends AbstractIntegrationTest {
//
// @EJB
// private UserFacade sut;
//
// @Test
// public void createUser_succesfull() throws BusinessException {
// UserDTO testUser = new UserDTO();
// testUser.setFirstname("John");
// testUser.setLastname("Doe");
//
// UserDTO createdUser = sut.createUser(testUser);
//
// Assert.assertNotNull("The newly persisted user should have an id!",
// createdUser.getId());
// }
//
// @Test
// public void createUser_ActiveByDefault() throws BusinessException {
// UserDTO testUser = new UserDTO();
//
// UserDTO createdUser = sut.createUser(testUser);
//
// Assert.assertTrue("The newly persisted user should be active!",
// createdUser.isActive());
// }
//
// }
