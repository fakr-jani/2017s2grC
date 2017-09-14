package ro.msg.edu.business.bug.validator;

import javax.ejb.Stateless;

import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;

@Stateless
public class BugValidator {

	public void validateBugData(BugDTO bugDTO) throws TechnicalException {
	}

}
