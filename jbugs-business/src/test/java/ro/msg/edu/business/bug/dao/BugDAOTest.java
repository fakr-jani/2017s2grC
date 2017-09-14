package ro.msg.edu.business.bug.dao;

import java.util.Date;

import javax.ejb.EJB;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;
import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.user.entity.User;

public class BugDAOTest extends AbstractIntegrationTest {

	@EJB
	private BugDAO sut;

	@Ignore
	@Test
	public void findBugByTitle_Success() throws TechnicalException {
		Bug bug = new Bug();
		bug.setTitleBug("BugTitle");
		bug.setDescriptionBug("BugDescription");
		bug.setVersion("1.0.0");
		bug.setTargetDate(new Date(2017, 12, 12));
		bug.setSeverity(BugSeverityType.LOW);
		bug.setCreatedBy(new User());
		sut.persistEntity(bug);

		Bug bugPersisted = sut.findBugByTitle("BugTitle");
		Assert.assertEquals("", "BugTitle", bugPersisted.getTitleBug());
	}
}
