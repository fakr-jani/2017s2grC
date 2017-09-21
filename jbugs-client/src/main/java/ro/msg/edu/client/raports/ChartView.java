package ro.msg.edu.client.raports;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.apache.commons.collections4.map.HashedMap;
import org.primefaces.model.chart.PieChartModel;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.BugDTO;

@ManagedBean
public class ChartView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BugFacade bugFacade;

	private PieChartModel pieModelStatus;
	private PieChartModel pieModelAssignedUsers;
	private PieChartModel pieModelCreatorUsers;

	@PostConstruct
	public void init() {
		createPieModelStatus();
		createPieModelUsers();
		createPieModelUsersOpenBug();
	}

	public PieChartModel getPieModelStatus() {
		return pieModelStatus;
	}

	public PieChartModel getPieModelAssignedUsers() {
		return pieModelAssignedUsers;
	}

	public PieChartModel getPieModelCreatorUsers() {
		return pieModelCreatorUsers;
	}

	private void createPieModelStatus() {
		pieModelStatus = new PieChartModel();
		List<BugDTO> listBug = bugFacade.findAllBugs();
		Map<String, Integer> bugs = new HashedMap<>();

		for (BugDTO bug : listBug)
			bugs.put(bug.getStatus().toString(), 0);

		for (BugDTO bug : listBug)
			bugs.put(bug.getStatus().toString(), bugs.get(bug.getStatus().toString()) + 1);
		for (String key : bugs.keySet()) {
			pieModelStatus.set(key, bugs.get(key));
		}

		pieModelStatus.setTitle("Status Raport");
		pieModelStatus.setLegendPosition("e");
		pieModelStatus.setFill(false);
		pieModelStatus.setShowDataLabels(true);
		pieModelStatus.setDiameter(150);
	}

	private void createPieModelUsers() {
		pieModelAssignedUsers = new PieChartModel();
		List<String> usernames = bugFacade.findAssignerForFixedBug();
		Map<String, Integer> users = new HashedMap<>();
		for (String username : usernames)
			users.put(username, 0);

		for (String username : usernames)
			users.put(username, users.get(username) + 1);
		for (String key : users.keySet()) {
			pieModelAssignedUsers.set(key, users.get(key));
		}

		pieModelAssignedUsers.setTitle("User Raport for Fixed Bugs");
		pieModelAssignedUsers.setLegendPosition("e");
		pieModelAssignedUsers.setFill(false);
		pieModelAssignedUsers.setShowDataLabels(true);
		pieModelAssignedUsers.setDiameter(150);
	}

	private void createPieModelUsersOpenBug() {
		pieModelCreatorUsers = new PieChartModel();
		List<String> usernames = bugFacade.findCreatorForRejectedBugs();
		Map<String, Integer> users = new HashedMap<>();
		for (String username : usernames)
			users.put(username, 0);

		for (String username : usernames) {
			users.put(username, users.get(username) + 1);
		}
		for (String key : users.keySet()) {
			pieModelCreatorUsers.set(key, users.get(key));
		}

		pieModelCreatorUsers.setTitle("User Raport for Created and Rejcted Bugs");
		pieModelCreatorUsers.setLegendPosition("e");
		pieModelCreatorUsers.setFill(false);
		pieModelCreatorUsers.setShowDataLabels(true);
		pieModelCreatorUsers.setDiameter(150);
	}

}