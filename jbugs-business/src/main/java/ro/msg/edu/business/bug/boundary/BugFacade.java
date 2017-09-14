/**
 * 
 */
package ro.msg.edu.business.bug.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.bug.control.BugControl;
import ro.msg.edu.business.bug.dto.BugDTO;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BugFacade {

	@EJB
	BugControl bugControl;

	public List<BugDTO> findAllBugs() {
		return bugControl.findAllBugs();
	}
}
