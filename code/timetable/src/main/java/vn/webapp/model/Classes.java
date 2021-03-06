/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblclasses")
public class Classes implements Serializable{
    
    @Id
    @GeneratedValue
    private int Classes_ID;
    private String Classes_Code;
    private String Classes_Name;
    private String Classes_AsciiName;
    private String Classes_FacultyCode;
    private String Classes_Year;
	public int getClasses_ID() {
		return Classes_ID;
	}
	public void setClasses_ID(int classes_ID) {
		Classes_ID = classes_ID;
	}
	public String getClasses_Code() {
		return Classes_Code;
	}
	public void setClasses_Code(String classes_Code) {
		Classes_Code = classes_Code;
	}
	public String getClasses_Name() {
		return Classes_Name;
	}
	public void setClasses_Name(String classes_Name) {
		Classes_Name = classes_Name;
	}
	public String getClasses_AsciiName() {
		return Classes_AsciiName;
	}
	public void setClasses_AsciiName(String classes_AsciiName) {
		Classes_AsciiName = classes_AsciiName;
	}
	public String getClasses_FacultyCode() {
		return Classes_FacultyCode;
	}
	public void setClasses_FacultyCode(String classes_FacultyCode) {
		Classes_FacultyCode = classes_FacultyCode;
	}
	public String getClasses_Year() {
		return Classes_Year;
	}
	public void setClasses_Year(String classes_Year) {
		Classes_Year = classes_Year;
	}
    
}
