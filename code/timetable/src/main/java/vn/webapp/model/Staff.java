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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.validator.cfg.context.Cascadable;


/**
 * @author Admin
 *
 */
@Entity
@Table(name = "tblstaffs")
public class Staff implements Serializable{
    
    @Id
    @GeneratedValue
    private int Staff_ID;
    
    private String Staff_University_Code;
    private String Staff_Code;
    private String Staff_Name;
    private String Staff_AsciiName;
    private String Staff_Email;
    private String Staff_Department_Code;
    private String Staff_Phone;
    private String Staff_Category_Code;
    private String Staff_User_Code;
               
   	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)  
    @JoinTable(name = "tblstaffspecializationkeywords", 
    		   joinColumns = { @JoinColumn(name = "STFKW_StaffCode", referencedColumnName = "Staff_Code") }, 
    		   inverseJoinColumns = { @JoinColumn(name = "STFKW_KeywordCode", referencedColumnName = "KW_Code") })  
    private Set<SpecializationKeyword> listSpecializationKeywords = new HashSet<SpecializationKeyword>();
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy="supervisor", cascade = CascadeType.ALL)
	private Set<MasterThesis> listMasterThesis = new HashSet<MasterThesis>();
    
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Staff_User_Code", referencedColumnName = "User_Code", insertable=false, updatable = false)
    private Users user;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Staff_Category_Code", referencedColumnName = "Staff_Category_Code", insertable=false, updatable = false)
    public StaffCategory staffCategory;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Staff_Department_Code", referencedColumnName = "Department_Code", insertable=false, updatable = false)
    private Department department;
    
    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Staff_University_Code", referencedColumnName = "University_Code")
    private University university;*/

    
    public Set<MasterThesis> getListMasterThesis() {
		return listMasterThesis;
	}

	public void setListMasterThesis(Set<MasterThesis> listMasterThesis) {
		this.listMasterThesis = listMasterThesis;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getStaff_ID() {
		return Staff_ID;
	}

	public Set<SpecializationKeyword> getListSpecializationKeywords() {
		return listSpecializationKeywords;
	}

	public void setListSpecializationKeywords(
			Set<SpecializationKeyword> listSpecializationKeywords) {
		this.listSpecializationKeywords = listSpecializationKeywords;
	}

	/*public Set<MasterThesis> getListMasterThesis() {
		return listMasterThesis;
	}

	public void setListMasterThesis(Set<MasterThesis> listMasterThesis) {
		this.listMasterThesis = listMasterThesis;
	}*/

	public void setStaff_ID(int staff_ID) {
		Staff_ID = staff_ID;
	}

	public String getStaff_Code() {
		return Staff_Code;
	}

	public void setStaff_Code(String staff_Code) {
		Staff_Code = staff_Code;
	}

	public String getStaff_Name() {
		return Staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}

	public String getStaff_AsciiName() {
		return Staff_AsciiName;
	}

	public void setStaff_AsciiName(String staff_AsciiName) {
		Staff_AsciiName = staff_AsciiName;
	}

	public String getStaff_Email() {
		return Staff_Email;
	}

	public void setStaff_Email(String staff_Email) {
		Staff_Email = staff_Email;
	}

	public String getStaff_Phone() {
		return Staff_Phone;
	}

	public void setStaff_Phone(String staff_Phone) {
		Staff_Phone = staff_Phone;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public StaffCategory getStaffCategory() {
		return staffCategory;
	}

	public void setStaffCategory(StaffCategory staffCategory) {
		this.staffCategory = staffCategory;
	}

	public String getStaff_University_Code() {
		return Staff_University_Code;
	}

	public void setStaff_University_Code(String staff_University_Code) {
		Staff_University_Code = staff_University_Code;
	}

	public String getStaff_Department_Code() {
		return Staff_Department_Code;
	}

	public void setStaff_Department_Code(String staff_Department_Code) {
		Staff_Department_Code = staff_Department_Code;
	}

	public String getStaff_Category_Code() {
		return Staff_Category_Code;
	}

	public void setStaff_Category_Code(String staff_Category_Code) {
		Staff_Category_Code = staff_Category_Code;
	}

	public String getStaff_User_Code() {
		return Staff_User_Code;
	}

	public void setStaff_User_Code(String staff_User_Code) {
		Staff_User_Code = staff_User_Code;
	}

	/*public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}*/	
}
