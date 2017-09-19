package ro.msg.edu.business.bug.control;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

public class BugControlTest extends AbstractIntegrationTest {
	@EJB
	private BugControl sut;

	@Test
	public void findAllBugs_Success() throws TechnicalException {
		List<BugDTO> bugs = sut.findAllBugs();

		Assert.assertEquals(4, bugs.size());
	}

	@Test
	public void closeBug_Succes() throws TechnicalException {
		Date date = new Date();
		BugDTO bug = new BugDTO();
		bug.setTitleBug("Bug3");
		bug.setDescriptionBug(
				"BugDescription********************************************************************************************************************************************************************************************************************************************");
		bug.setVersion("1.0.0");
		bug.setTargetDate(date);
		bug.setSeverity(BugSeverityType.LOW);
		bug.setStatus(BugStatusType.FIXED);
		BugDTO bugDTO = sut.closeBug(bug);
		Assert.assertEquals( "CLOSED",bugDTO.getStatus().toString());
	}
}
