/*package ro.msg.edu.business.bug.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void updateBug_Success() throws TechnicalException, ParseException {
		BugDTO bug = new BugDTO();

		bug.setTitleBug("BugTitle");
		bug.setDescriptionBug(
				"BugDescription********************************************************************************************************************************************************************************************************************************************");
		bug.setVersion("1.0.0");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("21/12/2019");
		bug.setTargetDate(date);
		bug.setSeverity(BugSeverityType.LOW);
		bug.setStatus(BugStatusType.OPEN);

		sut.createBug(bug);

		BugDTO newBug = new BugDTO();
		newBug.setTitleBug("BugTitle1");
		newBug.setDescriptionBug(
				"BugDescriptionUPDATED*************************************************************************************************************************************************************************************************************************************");
		newBug.setVersion("2.0.0");
		Date date1 = sdf.parse("21/12/2019");
		newBug.setTargetDate(date1);
		newBug.setSeverity(BugSeverityType.HIGH);
		newBug.setStatus(BugStatusType.IN_PROGRESS);

		BugDTO bugPersisted = sut.updateBug(newBug);

		Assert.assertEquals("Something happened.Update Bug Operation was not a success!", "BugTitle1",
				bugPersisted.getTitleBug());
	}

	@Test(expected = TechnicalException.class)
	public void updateBug_FindBugById_Fail() throws TechnicalException, ParseException {
		BugDTO newBug = new BugDTO();
		newBug.setId(4l);
		newBug.setTitleBug("BugTitle4");
		newBug.setDescriptionBug(
				"BugDescriptionUPDATED*************************************************************************************************************************************************************************************************************************************");
		newBug.setVersion("2.0.0");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("22/12/2019");
		newBug.setTargetDate(date);
		newBug.setSeverity(BugSeverityType.HIGH);
		newBug.setStatus(BugStatusType.IN_PROGRESS);

		sut.updateBug(newBug);
	}

	@Test
	public void findAllBugs_Success() throws TechnicalException, ParseException {
		BugDTO bug = new BugDTO();
		bug.setTitleBug("BugTitle3");
		bug.setDescriptionBug(
				"BugDescription********************************************************************************************************************************************************************************************************************************************");
		bug.setVersion("1.0.0");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("21/12/2017");
		bug.setTargetDate(date);
		bug.setTargetDate(date);
		bug.setSeverity(BugSeverityType.LOW);
		bug.setStatus(BugStatusType.OPEN);

		sut.createBug(bug);

		List<BugDTO> bugs = sut.findAllBugs();
		Assert.assertEquals("Where are my bugs?", 2, bugs.size());
	}

}
*/