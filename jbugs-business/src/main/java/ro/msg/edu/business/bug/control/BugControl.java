package ro.msg.edu.business.bug.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.business.bug.validator.BugValidator;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.Bug;

@Stateless
public class BugControl {

	@EJB
	BugDAO bugDAO;

	@EJB
	BugDTOMapper bugDTOMapper;

	@EJB
	BugValidator bugValidator;

	Map<String, List<String>> statusDiagram;

	@PostConstruct
	public void init() {
		statusDiagram = new HashMap<>();
		statusDiagram.put("OPEN", new ArrayList<>(Arrays.asList("REJECTED", "IN_PROGRESS")));
		statusDiagram.put("IN_PROGRESS", new ArrayList<>(Arrays.asList("REJECTED", "INFO_NEEDED", "FIXED")));
		statusDiagram.put("INFO_NEEDED", new ArrayList<>(Arrays.asList("IN_PROGRESS")));
		statusDiagram.put("REJECTED", new ArrayList<>(Arrays.asList("CLOSED")));
		statusDiagram.put("FIXED", new ArrayList<>(Arrays.asList("OPEN", "CLOSED")));
	}

	public void validateStatus(BugDTO bugDTO) throws TechnicalException {
		Bug entity = bugDAO.findBugByTitle(bugDTO.getTitleBug());

		String currentBugStatus = entity.getStatus().getStatusType();
		List<String> possibleTransitionsFromCurrentStatus = statusDiagram.get(currentBugStatus);
		if (possibleTransitionsFromCurrentStatus == null)
			throw new TechnicalException("The status you provided is not a valid one!");

		String newBugStatus = bugDTO.getStatus().getStatusType();
		if (!possibleTransitionsFromCurrentStatus.contains(newBugStatus))
			throw new TechnicalException("You cannot go from " + currentBugStatus + "to " + newBugStatus);
	}

	public BugDTO updateBug(BugDTO bugDTO) throws TechnicalException {
		bugValidator.validateBugData(bugDTO);
		this.validateStatus(bugDTO);

		Bug entity = bugDAO.findBugByTitle(bugDTO.getTitleBug());
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

	public BugDTO createBug(BugDTO bugDTO) throws TechnicalException {
		bugValidator.validateBugData(bugDTO);
		Bug entity = new Bug();
		bugDTOMapper.mapToEntity(bugDTO, entity);

		bugDAO.persistEntity(entity);
		Bug persistedBug = bugDAO.findEntity(entity.getId());

		return bugDTOMapper.mapToDTO(persistedBug);
	}

}
