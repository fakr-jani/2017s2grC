package ro.msg.edu.business.bug.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.bug.control.BugControl;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BugFacade {

	@EJB
	BugControl bugControl;

	public List<BugDTO> findAllBugs() {
		return bugControl.findAllBugs();
	}

	public BugDTO updateBug(BugDTO bugDTO) throws TechnicalException {
		return bugControl.updateBug(bugDTO);
	}

	public BugDTO createBug(BugDTO bugDTO) throws TechnicalException {
		return bugControl.createBug(bugDTO);
	}

	public BugDTO updateBugStatus(BugDTO bugDTO) throws TechnicalException {
		return bugControl.updateBugStatus(bugDTO);
	}

	public BugDTO closeBug(BugDTO bugDTO) throws TechnicalException {
		return bugControl.closeBug(bugDTO);
	}
}
