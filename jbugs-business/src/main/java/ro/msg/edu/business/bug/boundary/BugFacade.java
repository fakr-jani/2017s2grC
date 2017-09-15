package ro.msg.edu.business.bug.boundary;

import javax.ejb.EJB;

import ro.msg.edu.business.bug.control.BugControl;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.common.exception.TechnicalException;

/**
 * Boundary for Bug component.
 * 
 * @author Alex Noja
 * 
 */
public class BugFacade {

	@EJB
	private BugControl bugControl;

	@EJB
	private BugDTOMapper bugDTOMapper;
	
	public BugDTO createBug(BugDTO bug) throws TechnicalException{
		
		return bugControl.createBug(bug);
	}

}
