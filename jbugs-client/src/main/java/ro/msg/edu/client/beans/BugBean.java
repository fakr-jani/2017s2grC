package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.JBugsException;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;
import ro.msg.edu.persistence.bug.entity.enums.BugStatusType;

@ManagedBean
@SessionScoped
public class BugBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	String userName = (String) session.getAttribute("username");
	UserDTO user = new UserDTO();

	List<BugSeverityType> severityList = Arrays.asList(BugSeverityType.values());

	@EJB
	private BugFacade bugFacade;

	@EJB
	private UserFacade userFacade;

	private BugDTO newBug = new BugDTO();

	private String userAssigned;

	private BugDTO selectedBug = new BugDTO();

	private List<BugDTO> allBugsForEditStatusView = new ArrayList<>();

	private List<AttachmentDTO> tempAttachmentList = new ArrayList<>();

	private Date date2;

	private String selectedSeverity;

	private static final String ADD_BUG = "addBug";

	private static final String EDIT_BUGS = "editBugs";

	private static final String CLOSE_BUG = "closeBug";

	@PostConstruct
	public void init() {
		this.allBugsForEditStatusView = bugFacade.findAllBugs();
	}

	public List<BugDTO> getAllBugs() {
		return bugFacade.findAllBugs();
	}

	public List<UserDTO> getAllUsers() {
		return userFacade.findAllUsers();
	}

	public BugDTO getSelectedBug() {
		return selectedBug;
	}

	public void setSelectedBug(BugDTO selectedBug) {
		this.selectedBug = selectedBug;
	}

	public List<BugDTO> getAllBugsForEditStatusView() {
		return allBugsForEditStatusView;
	}

	public void setAllBugsForEditStatusView(List<BugDTO> allBugsForEditStatusView) {
		this.allBugsForEditStatusView = allBugsForEditStatusView;
	}

	public List<BugSeverityType> getAllBugSeverityTypes() {
		return new ArrayList<>(Arrays.asList(BugSeverityType.class.getEnumConstants()));
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public List<BugSeverityType> getSeverityList() {
		return severityList;
	}

	public void setSeverityList(List<BugSeverityType> severityList) {
		this.severityList = severityList;
	}

	public String getSelectedSeverity() {
		return selectedSeverity;
	}

	public void setSelectedSeverity(String selectedSeverity) {
		this.selectedSeverity = selectedSeverity;
	}

	public BugDTO getNewBug() {
		return newBug;
	}

	public void setNewBug(BugDTO newBug) {
		this.newBug = newBug;
	}

	public String getUserAssigned() {
		return userAssigned;
	}

	public void setUserAssigned(String userAssigned) {
		this.userAssigned = userAssigned;
	}

	public String getShowUsername() {
		return userName;
	}

	public String updateBug() {
		try {

			tempAttachmentList.forEach(attachment -> attachment.setBug(selectedBug));
			List<AttachmentDTO> existingAttachmentsInSelectedBug = selectedBug.getAttachments();
			if (existingAttachmentsInSelectedBug != null) {
				existingAttachmentsInSelectedBug.addAll(tempAttachmentList);
			}

			bugFacade.updateBug(this.selectedBug);
			this.tempAttachmentList = new ArrayList<>();
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return EDIT_BUGS;
	}

	public void addUploadedFile(FileUploadEvent fileUploadEvent) {
		AttachmentDTO attachmentDTO = new AttachmentDTO();
		attachmentDTO.setFileBytes(fileUploadEvent.getFile().getContents());
		attachmentDTO.setFileName(fileUploadEvent.getFile().getFileName());

		this.tempAttachmentList.add(attachmentDTO);

		addMessage(fileUploadEvent.getFile().getFileName() + " " + getMessageFromProperty("#{msg['is.uploaded']}"));
	}

	public void removeAttachment(AttachmentDTO attachmentDTO) {
		attachmentDTO.setBug(null);
		this.selectedBug.getAttachments().remove(attachmentDTO);
		addMessage(getMessageFromProperty("#{msg['file.deleted']}"));
	}

	public String enterUpdateMode(BugDTO bug) {
		this.selectedBug = bug;
		return EDIT_BUGS;
	}

	public String leaveUpdateMode() {
		selectedBug = new BugDTO();
		return EDIT_BUGS;
	}

	public boolean verifyEditRendered(BugDTO bug) {
		return (selectedBug != null && bug.getId().equals(selectedBug.getId()));
	}

	public List<BugDTO> getBugsThatCanBeClosed() {
		return bugFacade.findBugsThatCanBeClosed();
	}

	public String closeBug(BugDTO bugDTO) {
		try {
			bugFacade.closeBug(bugDTO);
			addMessage(bugDTO.getTitleBug() + " " + getMessageFromProperty("#{msg['close.bug.status']}"));
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return CLOSE_BUG;
	}

	public String doCreateBug() {
		try {
			user = userFacade.findUserbyUsername(userName);
			newBug.setCreatedBy(user);
			user = userFacade.findUserbyUsername(userAssigned);
			newBug.setAssignedTo(user);
			newBug.setSeverity(BugSeverityType.valueOf(selectedSeverity));
			newBug.setTargetDate(date2);

			if (user == null) {
				FacesMessage message = new FacesMessage("Username-ul introdus este gresit!");
			} else {
				BugDTO bugCreated = bugFacade.createBug(newBug);

				tempAttachmentList.forEach(attachment -> attachment.setBug(bugCreated));
				bugCreated.setAttachments(new ArrayList<>(tempAttachmentList));
				bugFacade.updateBug(bugCreated);
				tempAttachmentList = new ArrayList<>();

				addMessage(newBug.getTitleBug() + " " + getMessageFromProperty("#{msg['bug.added']}"));
			}
		} catch (JBugsException e) {
			handleExceptioni18n(e);
		}
		return ADD_BUG;
	}

	public String getMessageFromProperty(String messageProperty) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, messageProperty, String.class);
	}

	public void onCellEdit(CellEditEvent cellEditEvent) {
		BugDTO updatedBug = getAllBugs().get(cellEditEvent.getRowIndex());
		updatedBug.setStatus((BugStatusType) cellEditEvent.getNewValue());
		try {
			bugFacade.updateBugStatus(updatedBug);
			FacesMessage msg = new FacesMessage("Status Edited for " + updatedBug.getTitleBug());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
	}

}