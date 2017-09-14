package ro.msg.edu.business.bug.control;

import java.util.Calendar;
import java.util.Date;

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
		newBug.setTitleBug("BugTitle");
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

		Assert.assertEquals("Something happened.Update Bug Operation was not a success!", bug.getTitleBug(),
				bugPersisted.getTitleBug());
	}

	@Test(expected = TechnicalException.class)
	public void updateBug_FindBugByTitle_Fail() throws TechnicalException {
		BugDTO newBug = new BugDTO();
		newBug.setTitleBug("BugTitle2");
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
	}

	@Test(expected = TechnicalException.class)
	public void updateBug_ValidateStatus_Fail() throws TechnicalException {
		BugDTO newBug = new BugDTO();
		newBug.setTitleBug("BugTitle");
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
		newBug.setStatus(BugStatusType.CLOSED);

		BugDTO bugPersisted = sut.updateBug(newBug);
	}
}
