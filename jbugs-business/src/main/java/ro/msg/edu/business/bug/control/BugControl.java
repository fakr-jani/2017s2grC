/**
 * 
 */
package ro.msg.edu.business.bug.control;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.AttachmentDTOMapper;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.bug.validator.BugValidator;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.mapper.UserDTOMapper;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

/**
 * Controller for Bug component.
 * 
 * @author Alex Noja
 * 
 */
@Stateless
public class BugControl implements Serializable {

	@EJB
	private BugDTOMapper bugDTOMapper;

	@EJB
	private AttachmentDTOMapper attachmentDTOMapper;

	@EJB
	private UserDTOMapper userDTOMapper;

	@EJB
	private BugDAO bugDAO;

	@EJB
	BugValidator bugValidator;

	@EJB
	UserDAO userDAO;

	public BugDTO updateBug(BugDTO bugDTO) throws TechnicalException {

		Bug persistedEntity = bugDAO.findEntity(bugDTO.getId());
		if (persistedEntity == null)
			throw new TechnicalException("The bug you are trying to update does not exist!");

		Bug receivedDTOToEntity = new Bug();
		bugDTOMapper.mapToEntity(bugDTO, receivedDTOToEntity);

		persistedEntity.setTitleBug(receivedDTOToEntity.getTitleBug());
		persistedEntity.setDescriptionBug(receivedDTOToEntity.getDescriptionBug());
		persistedEntity.setVersion(receivedDTOToEntity.getVersion());
		persistedEntity.setVersionFixed(receivedDTOToEntity.getVersionFixed());
		persistedEntity.setSeverity(receivedDTOToEntity.getSeverity());
		persistedEntity.setStatus(receivedDTOToEntity.getStatus());
		persistedEntity.setAssignedTo(receivedDTOToEntity.getAssignedTo());
		persistedEntity.setAttachments(receivedDTOToEntity.getAttachments());

		return bugDTOMapper.mapToDTO(persistedEntity);
	}

	public BugDTO updateBugStatus(BugDTO bugDTO) throws TechnicalException {
		Bug persistedEntity = bugDAO.findEntity(bugDTO.getId());
		if (persistedEntity == null)
			throw new TechnicalException("The status bug you are trying to update does not exist!");

		Bug receivedDTOToEntity = new Bug();
		bugDTOMapper.mapToEntity(bugDTO, receivedDTOToEntity);

		persistedEntity.setStatus(receivedDTOToEntity.getStatus());

		return bugDTOMapper.mapToDTO(persistedEntity);
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

		return bugEnitites.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity)).collect(Collectors.toList());

	}

	public List<BugDTO> findBugsThatCanBeClosed() {
		List<Bug> bugEnitites = bugDAO.findBugsThatCanBeClosed();

		return bugEnitites.stream().map(bugEntity -> bugDTOMapper.mapToDTO(bugEntity)).collect(Collectors.toList());

	}

	public BugDTO closeBug(BugDTO bugDTO) throws TechnicalException {
		Bug bug = bugDAO.findBugByTitle(bugDTO.getTitleBug());
		bug.setStatus(BugStatusType.CLOSED);
		return bugDTOMapper.mapToDTO(bug);
	}

	public List<String> findAssignerForFixedBug() {
		return bugDAO.findAssignerForFixedBug();
	}

	public List<String> findCreatorForRejectedBugs() {
		return bugDAO.findCreatorForRejectedBugs();
	}
}
