
package ro.msg.edu.business.bug.validator;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;

/**
 * 
 * @author Alex Noja
 * 
 */
@Stateless
public class BugValidator {

	public static final String VALID_BUG_VERSION_REGEX = "^[a-zA-Z0-9]{2,100}[.]{1}[a-zA-Z0-9]{1,100}$";

	public void validateBugData(BugDTO bugDTO) throws TechnicalException {
		validateTitle(bugDTO);
		validateDescription(bugDTO);
		validateVersion(bugDTO);
		validateSeverity(bugDTO);
	}

	public void validateTitle(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getTitleBug() == null)
			throw new TechnicalException("Title cannot be null!");
	}

	public void validateDescription(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getDescriptionBug().length() < 250)
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
