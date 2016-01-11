/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "tblstaffs")
public class MasterThesisStaff implements Serializable{
    
    @Id
    @GeneratedValue
    private int Staff_ID;
    private String Staff_Code;
    private String Staff_Name;
    private String Staff_AsciiName;
    private String Staff_Email;
    private String Staff_Phone;
    private String Staff_University_Code;

	@OneToMany(mappedBy="supervisor")
	private Set<ListMasterThesis> listMasterThesis;


	public int getStaff_ID() {
		return Staff_ID;
	}


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


	public String getStaff_University_Code() {
		return Staff_University_Code;
	}


	public void setStaff_University_Code(String staff_University_Code) {
		Staff_University_Code = staff_University_Code;
	}


	public Set<ListMasterThesis> getListMasterThesis() {
		return listMasterThesis;
	}


	public void setListMasterThesis(Set<ListMasterThesis> listMasterThesis) {
		this.listMasterThesis = listMasterThesis;
	}
    
}
