package ro.msg.edu.client.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.BugDTO;

@ManagedBean
@ViewScoped
public class BugBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BugFacade bugFacade;

	private BugDTO bugDTO = new BugDTO();

	private BugDTO selectedBug = new BugDTO();

	public List<BugDTO> getAllBugs() {
		return bugFacade.findAllBugs();
	}

}
