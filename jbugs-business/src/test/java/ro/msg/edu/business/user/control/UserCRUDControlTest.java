package ro.msg.edu.business.user.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;
import ro.msg.edu.persistence.user.entity.User;

/**
 * Tests for UserControl and validations
 * 
 * @author maresb
 *
 */
public class UserCRUDControlTest extends AbstractIntegrationTest {

	@EJB
	private UserCRUDControl sut;

	@EJB
	private UserDAO userDAO;

	@EJB
	private BugDAO bugDAO;

	@Test
	public void verifyUserExists_Success() throws JBugsException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("CevaNume");
		testUser.setLastname("Smith");
		testUser.setUsername("SmithC");
		testUser.setPassword("123");
		testUser.setEmail("Ceva_nume@msggroup.com");
		testUser.setPhoneNumber("+407098884443");

		String[] nameRoles = { "ADMINISTRATOR" };
		UserDTO persisted = sut.createUser(testUser, nameRoles);
		boolean existUser = sut.verifyUserExists(persisted);
		Assert.assertEquals(existUser, true);

	}

	@Test
	public void deleteUser_Success() throws TechnicalException, ParseException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Marta");
		testUser.setLastname("Dosse");
		testUser.setUsername("DossMa");
		testUser.setPassword("123sa");
		testUser.setPhoneNumber("+40788787697");
		testUser.setEmail("marthss@msggroup.com");
		String[] nameRoles = { "ADMINISTRATOR" };

		UserDTO persisted = sut.createUser(testUser, nameRoles);

		Bug bug = new Bug();
		Optional<User> user = userDAO.findUserByUsername(persisted.getUsername());
		bug.setAssignedTo(user.get());
		bug.setTitleBug("My not so awesome bug");
		bug.setDescriptionBug(
				"500-description too long to write something interesting******************************************************8*********************************************************************************************************************************************");
		bug.setCreatedBy(user.get());
		bug.setSeverity(BugSeverityType.CRITICAL);
		bug.setVersion("1.1.2");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2019");
		bug.setTargetDate(d);
		bug.setStatus(BugStatusType.CLOSED);
		bugDAO.persistEntity(bug);
		UserDTO deletedUser = sut.deleteUser(persisted);
		Assert.assertEquals(false, deletedUser.isActive());

	}

	@Test(expected = TechnicalException.class)
	public void deleteUser_hasTaskValidationFail() throws TechnicalException, ParseException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Marta");
		testUser.setLastname("Martus");
		testUser.setUsername("MarDoe");
		testUser.setPassword("123sa");
		testUser.setPhoneNumber("+40788787697");
		testUser.setEmail("marthssasaa@msggroup.com");
		String[] nameRoles = { "ADMINISTRATOR" };

		UserDTO persisted = sut.createUser(testUser, nameRoles);
		Bug bug = new Bug();
		Optional<User> user = userDAO.findUserByUsername(persisted.getUsername());
		bug.setAssignedTo(user.get());
		bug.setTitleBug("My awesome bug");
		bug.setDescriptionBug(
				"500-description too long to write something interesting******************************************************8*********************************************************************************************************************************************");
		bug.setStatus(BugStatusType.IN_PROGRESS);
		bug.setCreatedBy(user.get());
		bug.setSeverity(BugSeverityType.CRITICAL);
		bug.setVersion("1.3.2");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/4019");
		bug.setTargetDate(d);
		bugDAO.persistEntity(bug);

		UserDTO deletedUser = sut.deleteUser(persisted);
		Assert.assertEquals(true, deletedUser.isActive());

	}

}