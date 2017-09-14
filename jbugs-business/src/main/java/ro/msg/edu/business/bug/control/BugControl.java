/**
 * 
 */
package ro.msg.edu.business.bug.control;

import javax.ejb.EJB;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.bug.validator.BugValidator;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;
import ro.msg.edu.persistence.user.entity.User;

/**
 * 
 * @author Alex Noja
 * 
 */
public class BugControl {

	@EJB
	private BugDTOMapper bugDTOMapper;

	@EJB
	private BugDAO bugDAO;

	@EJB
	private BugStatusType bugStatusType;
	
	@EJB
	private BugValidator bugValidator;

	public BugDTO createBug(BugDTO bug) throws TechnicalException {

		bugValidator.validateBugData(bug);
		
		Bug bugEntity = new Bug();
		bugDTOMapper.mapToEntity(bug, bugEntity);

		bugEntity.setStatus(bugStatusType.OPEN);

		bugDAO.persistEntity(bugEntity);
		Bug persistedBug = bugDAO.findEntity(bugEntity.getId());

		return bugDTOMapper.mapToDTO(persistedBug);

	}

}
