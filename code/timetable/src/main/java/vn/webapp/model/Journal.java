/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.cfg.context.Cascadable;


@Entity
@Table(name = "tbljournalindex")
public class Journal implements Serializable{
    
    @Id
    @GeneratedValue
    private int JNAL_ID;
    private String JNAL_IndexCode;
    private String JNAL_Description;
    
    public Journal(){
    }

	public int getJNAL_ID() {
		return JNAL_ID;
	}

	public void setJNAL_ID(int jNAL_ID) {
		JNAL_ID = jNAL_ID;
	}

	public String getJNAL_IndexCode() {
		return JNAL_IndexCode;
	}

	public void setJNAL_IndexCode(String jNAL_IndexCode) {
		JNAL_IndexCode = jNAL_IndexCode;
	}

	public String getJNAL_Description() {
		return JNAL_Description;
	}

	public void setJNAL_Description(String jNAL_Description) {
		JNAL_Description = jNAL_Description;
	}
}
