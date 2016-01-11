package vn.webapp.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import vn.webapp.model.MasterThesisStaff;
import vn.webapp.model.MasterThesisStudent;

@Entity
@Table(name = "tblmasterthesis")
public class ListMasterThesis {
	@Id
    @GeneratedValue
    private int Thesis_ID;
    private String Thesis_Name;
    private String Thesis_Code;
    private String Thesis_StudentCode;
    private String Thesis_SupervisorCode;
    
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "Thesis_StudentCode", referencedColumnName = "Student_Code", insertable = false, updatable = false)
	private MasterThesisStudent student;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Thesis_SupervisorCode", referencedColumnName = "Staff_Code", insertable = false, updatable = false)
	private MasterThesisStaff supervisor;

	public int getThesis_ID() {
		return Thesis_ID;
	}

	public void setThesis_ID(int thesis_ID) {
		Thesis_ID = thesis_ID;
	}

	public String getThesis_Name() {
		return Thesis_Name;
	}

	public void setThesis_Name(String thesis_Name) {
		Thesis_Name = thesis_Name;
	}

	public String getThesis_Code() {
		return Thesis_Code;
	}

	public void setThesis_Code(String thesis_Code) {
		Thesis_Code = thesis_Code;
	}

	public String getThesis_StudentCode() {
		return Thesis_StudentCode;
	}

	public void setThesis_StudentCode(String thesis_StudentCode) {
		Thesis_StudentCode = thesis_StudentCode;
	}

	public String getThesis_SupervisorCode() {
		return Thesis_SupervisorCode;
	}

	public void setThesis_SupervisorCode(String thesis_SupervisorCode) {
		Thesis_SupervisorCode = thesis_SupervisorCode;
	}

	public MasterThesisStaff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(MasterThesisStaff supervisor) {
		this.supervisor = supervisor;
	}

	public MasterThesisStudent getStudent() {
		return student;
	}

	public void setStudent(MasterThesisStudent student) {
		this.student = student;
	}
	
	
}
