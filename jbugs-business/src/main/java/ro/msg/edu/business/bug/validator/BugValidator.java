
package ro.msg.edu.business.bug.validator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.persistence.bug.entity.Bug;

/**
 * Validator for Bug component.
 * 
 * @author Alex Noja
 * 
 */
@Stateless
public class BugValidator {

	@EJB
	BugDAO bugDAO;

	public static final String VALID_BUG_VERSION_REGEX = "^[a-zA-Z0-9]{2,100}[.]{1}[a-zA-Z0-9]{1,100}$";
	public static final int MIN_CHARACTERS_DESCRIPTION = 250;

	public void validateBugData(BugDTO bugDTO) throws TechnicalException {
		validateTitle(bugDTO);
		validateDescription(bugDTO);
		validateVersion(bugDTO);
		validateSeverity(bugDTO);
	}

	public void validateTitle(BugDTO bugDTO) throws TechnicalException {
		Bug existingBugWithSameTitle = bugDAO.findBugByTitle(bugDTO.getTitleBug());
		if (existingBugWithSameTitle != null && existingBugWithSameTitle.getId() != bugDTO.getId()) {
			throw new TechnicalException("Bug already exists with given title " + bugDTO.getTitleBug());
		}
	}

	public void validateDescription(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getDescriptionBug().length() < MIN_CHARACTERS_DESCRIPTION)
			throw new TechnicalException("Description must have minimum 250 characters!");
	}

	public void validateVersion(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getVersion().matches(VALID_BUG_VERSION_REGEX))
			throw new TechnicalException("Invalid bug version!");

	}

	public void validateSeverity(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getSeverity() == null)
			throw new TechnicalException("Severity cannot be null!");
	}

}
