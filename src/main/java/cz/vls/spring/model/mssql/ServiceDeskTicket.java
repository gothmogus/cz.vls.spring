package cz.vls.spring.model.mssql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ALVAO_Ticket_Custom")
public class ServiceDeskTicket {
	
	@Id
	@Column(name = "TicketId")
	private Integer id;
	private String TicketTag;
	private String TicketSubject;
	private String NextAction;
	private Date NextActionDeadline;
	private String Solver;
	private String State;
	private String Section;
	
	@Column(name = "UserLogin")
	private String userLogin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTicketTag() {
		return TicketTag;
	}
	public void setTicketTag(String ticketTag) {
		TicketTag = ticketTag;
	}
	public String getTicketSubject() {
		return TicketSubject;
	}
	public void setTicketSubject(String ticketSubject) {
		TicketSubject = ticketSubject;
	}
	public String getNextAction() {
		return NextAction;
	}
	public void setNextAction(String nextAction) {
		NextAction = nextAction;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy ", timezone="UTC")
	public Date getNextActionDeadline() {
		return NextActionDeadline;
	}
	
	public void setNextActionDeadline(Date nextActionDeadline) {
		NextActionDeadline = nextActionDeadline;
	}
	
	public String getSolver() {
		return Solver;
	}
	public void setSolver(String solver) {
		Solver = solver;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getSection() {
		return Section;
	}
	public void setSection(String section) {
		Section = section;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	
	

}
