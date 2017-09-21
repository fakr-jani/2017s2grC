
package ro.msg.edu.business.bug.validator;


import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
public class BugValidator implements Serializable{

	@EJB
	BugDAO bugDAO;

	public static final int MIN_CHARACTERS_DESCRIPTION = 250;
	public static final String VALID_BUG_VERSION_REGEX = "^[0-9]{0,15}.[0-9]{0,15}$";

	public void validateBugData(BugDTO bugDTO) throws TechnicalException {
		isBugTitleValid(bugDTO);
		isBugDescriptionValid(bugDTO);
		isBugVersionValid(bugDTO);
		isBugSeverityValid(bugDTO);
	}

	public void isBugTitleValid(BugDTO bugDTO) throws TechnicalException {
		 Bug existingBugWithSameTitle = bugDAO.findBugByTitle(bugDTO.getTitleBug());
		if (existingBugWithSameTitle != null && existingBugWithSameTitle.getTitleBug().equals(bugDTO.getTitleBug()) ) {
			throw new TechnicalException("Bug already exists with given title " + bugDTO.getTitleBug());
		}
	}


	public void isBugDescriptionValid(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getDescriptionBug().length() < MIN_CHARACTERS_DESCRIPTION)
			throw new TechnicalException("Description must have minimum 250 characters!");
	}

	public boolean isBugVersionValid(BugDTO bugDTO) throws TechnicalException {
		Pattern pattern = Pattern.compile(VALID_BUG_VERSION_REGEX);
		Matcher match = pattern.matcher(bugDTO.getVersion());
		if (match.find()) {
			return true;
		} else {
			throw new TechnicalException("Invalid bug version!");
		}
	}

	public void isBugSeverityValid(BugDTO bugDTO) throws TechnicalException {
		if (bugDTO.getSeverity() == null)
			throw new TechnicalException("Severity cannot be null!");
	}

}
