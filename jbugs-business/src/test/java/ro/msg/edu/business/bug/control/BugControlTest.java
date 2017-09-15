package ro.msg.edu.business.bug.control;

import java.util.Calendar;
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
	public void updateBug_Success() throws TechnicalException {
		BugDTO bug = new BugDTO();
		bug.setId(1l);
		bug.setTitleBug("BugTitle");
		bug.setDescriptionBug(
				"BugDescription********************************************************************************************************************************************************************************************************************************************");
		bug.setVersion("1.0.0");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		Date date = calendar.getTime();
		bug.setTargetDate(date);
		bug.setTargetDate(date);
		bug.setSeverity(BugSeverityType.LOW);
		bug.setStatus(BugStatusType.OPEN);

		sut.createBug(bug);

		BugDTO newBug = new BugDTO();
		newBug.setId(1l);
		newBug.setTitleBug("BugTitle1");
		newBug.setDescriptionBug(
				"BugDescriptionUPDATED*************************************************************************************************************************************************************************************************************************************");
		newBug.setVersion("2.0.0");
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 2017);
		calendar1.set(Calendar.MONTH, 12);
		calendar1.set(Calendar.DAY_OF_MONTH, 12);
		Date date1 = calendar1.getTime();
		newBug.setTargetDate(date1);
		newBug.setSeverity(BugSeverityType.HIGH);
		newBug.setStatus(BugStatusType.IN_PROGRESS);

		BugDTO bugPersisted = sut.updateBug(newBug);

		Assert.assertEquals("Something happened.Update Bug Operation was not a success!", "BugTitle1",
				bugPersisted.getTitleBug());
	}

	@Test(expected = TechnicalException.class)
	public void updateBug_FindBugById_Fail() throws TechnicalException {
		BugDTO newBug = new BugDTO();
		newBug.setId(4l);
		newBug.setTitleBug("BugTitle4");
		newBug.setDescriptionBug(
				"BugDescriptionUPDATED*************************************************************************************************************************************************************************************************************************************");
		newBug.setVersion("2.0.0");
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 2017);
		calendar1.set(Calendar.MONTH, 12);
		calendar1.set(Calendar.DAY_OF_MONTH, 12);
		Date date1 = calendar1.getTime();
		newBug.setTargetDate(date1);
		newBug.setSeverity(BugSeverityType.HIGH);
		newBug.setStatus(BugStatusType.IN_PROGRESS);

		sut.updateBug(newBug);
	}

	@Test
	public void findAllBugs_Success() throws TechnicalException {
		BugDTO bug = new BugDTO();
		bug.setTitleBug("BugTitle3");
		bug.setDescriptionBug(
				"BugDescription********************************************************************************************************************************************************************************************************************************************");
		bug.setVersion("1.0.0");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		Date date = calendar.getTime();
		bug.setTargetDate(date);
		bug.setTargetDate(date);
		bug.setSeverity(BugSeverityType.LOW);
		bug.setStatus(BugStatusType.OPEN);

		sut.createBug(bug);

		List<BugDTO> bugs = sut.findAllBugs();

		Assert.assertEquals("Where are my bugs?", 2, bugs.size());
	}

}
