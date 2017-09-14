// package ro.msg.edu.business.user.control;
//
// import java.util.Optional;
//
// import javax.ejb.EJB;
//
// import org.junit.Assert;
// import org.junit.Test;
//
// import ro.msg.edu.business.AbstractIntegrationTest;
// import ro.msg.edu.business.bug.dao.BugDAO;
// import ro.msg.edu.business.common.exception.JBugsException;
// import ro.msg.edu.business.common.exception.TechnicalException;
// import ro.msg.edu.business.user.dao.UserDAO;
// import ro.msg.edu.business.user.dto.UserDTO;
// import ro.msg.edu.persistence.bug.entity.Bug;
// import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;
// import ro.msg.edu.persistence.user.entity.User;
//
// public class UserCRUDControlTest extends AbstractIntegrationTest {
//
// @EJB
// private UserCRUDControl sut;
//
// @EJB
// private UserDAO userDAO;
//
// @EJB
// private BugDAO bugDAO;
//
// @Test(expected = TechnicalException.class)
// public void createUser_EmailValidationFail() throws TechnicalException {
// UserDTO testUser = new UserDTO();
// testUser.setFirstname("John");
// testUser.setPassword("123");
// testUser.setLastname("Doe");
// testUser.setEmail("john_s@msggroup.com");
// String[] nameRoles = { "ADMINISTRATOR" };
// sut.createUser(testUser, nameRoles);
//
// }
//
// @Test
// public void deleteUser_Success() throws TechnicalException {
// UserDTO testUser = new UserDTO();
// testUser.setFirstname("Mary");
// testUser.setPassword("123");
// testUser.setLastname("Dan");
// testUser.setEmail("mary_doe@msggroup.com");
// String[] nameRoles = { "ADMINISTRATOR" };
// UserDTO persisted = sut.createUser(testUser, nameRoles);
// UserDTO deletedUser = sut.deleteUser(persisted);
// Assert.assertEquals(false, deletedUser.isActive());
//
// }
//
// @Test
// public void verifyUserExists_Success() throws JBugsException {
// UserDTO testUser = new UserDTO();
// testUser.setFirstname("Marta");
// testUser.setLastname("Doe");
// testUser.setUsername("MarthaDoe");
// testUser.setPassword("123");
// testUser.setEmail("martha@msggroup.com");
//
// String[] nameRoles = { "ADMINISTRATOR" };
// UserDTO persisted = sut.createUser(testUser, nameRoles);
// boolean existUser = sut.verifyUserExists(persisted);
// Assert.assertEquals(existUser, true);
//
// }
//
// @Test
// public void deleteUser_hasTaskValidationFail() throws TechnicalException {
// UserDTO testUser = new UserDTO();
// testUser.setFirstname("Marta");
// testUser.setLastname("Dosse");
// testUser.setUsername("MarthaDoe");
// testUser.setPassword("123sa");
// testUser.setPhoneNumber("+40788787697");
// testUser.setEmail("marthssasaa@msggroup.com");
// String[] nameRoles = { "ADMINISTRATOR" };
//
// UserDTO persisted = sut.createUser(testUser, nameRoles);
// Bug bug = new Bug();
// Optional<User> user = userDAO.findUserByUsername(persisted.getUsername());
// bug.setAssignedTo(user.get());
// bug.setTitleBug("My not so awesome bug");
// bug.setDescriptionBug("404");
// bug.setStatus(BugStatusType.IN_PROGRESS);
// bugDAO.persistEntity(bug);
// UserDTO deletedUser = sut.deleteUser(persisted);
// Assert.assertEquals(false, deletedUser.isActive());
//
// }
//
// }