/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Admin
 *
 */
@Entity
@Table(name = "tblmasterdefensejury")
public class RawMasterDefenseJuryThesis implements Serializable{
    
    @Id
    @GeneratedValue
    private int MASDEFJury_ID;
    private String MASDEFJury_Code;
    private String MASDEFJury_ThesisCode;
    private String MASDEFJury_PresidentCode;
    private String MASDEFJury_SecretaryCode;
    private String MASDEFJury_Examiner1Code;
    private String MASDEFJury_Examiner2Code;
    private String MASDEFJury_MemberCode;
    private String MASDEFJury_SlotCode;
    private String MASDEFJury_RoomCode;
    private String MASDEFJury_DefenseSessionCode;
    private String MASDEFJury_StaffCode;

	public int getMASDEFJury_ID() {
		return MASDEFJury_ID;
	}

	public void setMASDEFJury_ID(int mASDEFJury_ID) {
		MASDEFJury_ID = mASDEFJury_ID;
	}

	public String getMASDEFJury_Code() {
		return MASDEFJury_Code;
	}

	public void setMASDEFJury_Code(String mASDEFJury_Code) {
		MASDEFJury_Code = mASDEFJury_Code;
	}

	public String getMASDEFJury_ThesisCode() {
		return MASDEFJury_ThesisCode;
	}

	public void setMASDEFJury_ThesisCode(String mASDEFJury_ThesisCode) {
		MASDEFJury_ThesisCode = mASDEFJury_ThesisCode;
	}

	public String getMASDEFJury_PresidentCode() {
		return MASDEFJury_PresidentCode;
	}

	public void setMASDEFJury_PresidentCode(String mASDEFJury_PresidentCode) {
		MASDEFJury_PresidentCode = mASDEFJury_PresidentCode;
	}

	public String getMASDEFJury_SecretaryCode() {
		return MASDEFJury_SecretaryCode;
	}

	public void setMASDEFJury_SecretaryCode(String mASDEFJury_SecretaryCode) {
		MASDEFJury_SecretaryCode = mASDEFJury_SecretaryCode;
	}

	public String getMASDEFJury_Examiner1Code() {
		return MASDEFJury_Examiner1Code;
	}

	public void setMASDEFJury_Examiner1Code(String mASDEFJury_Examiner1Code) {
		MASDEFJury_Examiner1Code = mASDEFJury_Examiner1Code;
	}

	public String getMASDEFJury_Examiner2Code() {
		return MASDEFJury_Examiner2Code;
	}

	public void setMASDEFJury_Examiner2Code(String mASDEFJury_Examiner2Code) {
		MASDEFJury_Examiner2Code = mASDEFJury_Examiner2Code;
	}

	public String getMASDEFJury_MemberCode() {
		return MASDEFJury_MemberCode;
	}

	public void setMASDEFJury_MemberCode(String mASDEFJury_MemberCode) {
		MASDEFJury_MemberCode = mASDEFJury_MemberCode;
	}

	public String getMASDEFJury_SlotCode() {
		return MASDEFJury_SlotCode;
	}

	public void setMASDEFJury_SlotCode(String mASDEFJury_SlotCode) {
		MASDEFJury_SlotCode = mASDEFJury_SlotCode;
	}

	public String getMASDEFJury_RoomCode() {
		return MASDEFJury_RoomCode;
	}

	public void setMASDEFJury_RoomCode(String mASDEFJury_RoomCode) {
		MASDEFJury_RoomCode = mASDEFJury_RoomCode;
	}

	public String getMASDEFJury_DefenseSessionCode() {
		return MASDEFJury_DefenseSessionCode;
	}

	public void setMASDEFJury_DefenseSessionCode(
			String mASDEFJury_DefenseSessionCode) {
		MASDEFJury_DefenseSessionCode = mASDEFJury_DefenseSessionCode;
	}

	public String getMASDEFJury_StaffCode() {
		return MASDEFJury_StaffCode;
	}

	public void setMASDEFJury_StaffCode(String mASDEFJury_StaffCode) {
		MASDEFJury_StaffCode = mASDEFJury_StaffCode;
	}
}
