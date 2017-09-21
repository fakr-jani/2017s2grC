package ro.msg.edu.business.bug.dao;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;

public class BugDAOTest extends AbstractIntegrationTest {
	@EJB
	private BugDAO bugDAO;

	@Test
	public void findAssignerForFixedBug_Succes() {
		List<String> usernames = bugDAO.findAssignerForFixedBug();
		Assert.assertEquals("SmithJ", usernames.get(0));
	}
}
