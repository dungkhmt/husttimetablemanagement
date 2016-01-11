/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tblmasterthesis")
public class MasterThesis implements Serializable{
    
    @Id
    @GeneratedValue
    private int Thesis_ID;
    private String Thesis_Name;
    private String Thesis_Code;
    private String Thesis_StudentCode;
    private String Thesis_SupervisorCode;
    
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "Thesis_StudentCode", referencedColumnName = "Student_Code", nullable = false, insertable=false, updatable=false)
	private Student student;
		
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "Thesis_SupervisorCode", referencedColumnName = "Staff_Code", nullable = false, insertable=false, updatable=false)
	private Staff supervisor;
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
	@JoinTable(name = "tblmasterthesisspecializationkeywords", 
	  		   joinColumns = { @JoinColumn(name = "MTKW_ThesisCode", referencedColumnName = "Thesis_Code") }, 
	   		   inverseJoinColumns = { @JoinColumn(name = "MTKW_KWCode", referencedColumnName = "KW_Code") })  
	private Set<SpecializationKeyword> listSpecializationKeywords = new HashSet<SpecializationKeyword>();

	public int getThesis_ID() {
		return Thesis_ID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
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

	public Set<SpecializationKeyword> getListSpecializationKeywords() {
		return listSpecializationKeywords;
	}

	public void setListSpecializationKeywords(
			Set<SpecializationKeyword> listSpecializationKeywords) {
		this.listSpecializationKeywords = listSpecializationKeywords;
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
}
