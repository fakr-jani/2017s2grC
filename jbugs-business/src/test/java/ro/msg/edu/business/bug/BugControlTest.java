/**
 * 
 */
package ro.msg.edu.business.bug;

import java.text.ParseException;
import java.util.Optional;

import javax.ejb.EJB;

import ro.msg.edu.business.AbstractIntegrationTest;
import ro.msg.edu.business.bug.control.BugControl;
import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.control.UserCRUDControl;
import ro.msg.edu.business.user.dao.UserDAO;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.Bug;
import ro.msg.edu.persistence.user.entity.User;

/**
 * 
 * @author Alex Noja
 * 
 */
public class BugControlTest extends AbstractIntegrationTest {
	
	@EJB
	private BugDAO bugDAO;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private BugControl sut;

	public void createBug_Success() throws TechnicalException, ParseException {
	
		
		

	
		
	
		

	}

}
