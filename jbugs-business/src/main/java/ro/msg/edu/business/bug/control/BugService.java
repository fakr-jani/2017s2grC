package ro.msg.edu.business.bug.control;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ro.msg.edu.business.bug.dao.BugDAO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.bug.dto.mapper.BugDTOMapper;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

/**
 * Class for table filter
 * 
 * @author maresb
 *
 */
@ManagedBean(name = "bugService")
@ViewScoped
public class BugService implements Serializable {

	@EJB
	private BugDAO bugDAO;

	@EJB
	private BugDTOMapper bugMapper;

	public List<BugStatusType> getStatus() {
		return Arrays.asList(BugStatusType.values());
	}

	public List<BugSeverityType> getSeverity() {
		return Arrays.asList(BugSeverityType.values());
	}

	public List<BugDTO> getBugs() {
		return bugMapper.mapToDTOs(bugDAO.findAllBugs());
	}

}
