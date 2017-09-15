/**
 * 
 */
package ro.msg.edu.business.bug.control;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
 * Controller for Bug component.
 * 
 * @author Alex Noja
 * 
 */
@Stateless
public class BugControl {

	@EJB
	private BugDTOMapper bugDTOMapper;

	@EJB
	private BugDAO bugDAO;

	@EJB
	BugValidator bugValidator;

	public BugDTO updateBug(BugDTO bugDTO) throws TechnicalException {
		bugValidator.validateBugData(bugDTO);

		Bug entity = bugDAO.findEntity(bugDTO.getId());
		if (entity == null)
			throw new TechnicalException("The bug does not exists!");

		entity.setTitleBug(bugDTO.getTitleBug());
		entity.setDescriptionBug(bugDTO.getDescriptionBug());
		entity.setVersion(bugDTO.getVersion());
		entity.setVersionFixed(bugDTO.getVersionFixed());
		entity.setSeverity(bugDTO.getSeverity());
		entity.setStatus(bugDTO.getStatus());
		entity.setAssignedTo(bugDTO.getAssignedTo());
		entity.setAttachments(bugDTO.getAttachments());

		return bugDTOMapper.mapToDTO(entity);
	}

	public BugDTO createBug(BugDTO bug) throws TechnicalException {

		bugValidator.validateBugData(bug);
		
		Bug bugEntity = new Bug();
		bugDTOMapper.mapToEntity(bug, bugEntity);

		bugEntity.setStatus(BugStatusType.OPEN);

		bugDAO.persistEntity(bugEntity);
		Bug persistedBug = bugDAO.findEntity(bugEntity.getId());

		return bugDTOMapper.mapToDTO(persistedBug);
	}

	public List<BugDTO> findAllBugs() {
		List<Bug> bugEnitites = bugDAO.findAllBugs();

		List<BugDTO> bugDTOs = bugEnitites.stream().map(e -> bugDTOMapper.mapToDTO(e)).collect(Collectors.toList());

		return bugDTOs;
	}
}
