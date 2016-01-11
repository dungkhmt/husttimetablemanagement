package vn.webapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblfaculty")
public class Faculty implements Serializable{
	
	@Id
	@GeneratedValue
	private int Faculty_ID;
	private String Faculty_Code;
	private String Faculty_VNName;
	private String Faculty_EngName;
		
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="Faculty_UniversityCode", referencedColumnName = "University_Code", insertable = false, updatable = false)
	private University university;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="faculty", cascade = CascadeType.ALL)
	private Set<Department> listDepartment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="faculty", cascade = CascadeType.ALL)
	private Set<MasterClass> listMasterClass;
	
	public Set<MasterClass> getListMasterClass() {
		return listMasterClass;
	}

	public void setListMasterClass(Set<MasterClass> listMasterClass) {
		this.listMasterClass = listMasterClass;
	}
	public int getFaculty_ID() {
		return Faculty_ID;
	}

	public void setFaculty_ID(int faculty_ID) {
		Faculty_ID = faculty_ID;
	}

	public String getFaculty_Code() {
		return Faculty_Code;
	}

	public void setFaculty_Code(String faculty_Code) {
		Faculty_Code = faculty_Code;
	}

	public String getFaculty_VNName() {
		return Faculty_VNName;
	}

	public void setFaculty_VNName(String faculty_VNName) {
		Faculty_VNName = faculty_VNName;
	}

	public String getFaculty_EngName() {
		return Faculty_EngName;
	}

	public void setFaculty_EngName(String faculty_EngName) {
		Faculty_EngName = faculty_EngName;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Set<Department> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(Set<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}	
}
