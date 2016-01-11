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
@Table(name = "tblprojectcategory")
public class TopicCategory implements Serializable{
    
    @Id
    @GeneratedValue
    private int PROJCAT_ID;
    private String PROJCAT_Name;
    private String PROJCAT_Description;
    private String PROJCAT_Code;
    public TopicCategory(){
    }
    
    @OneToMany(mappedBy="topicCategory")
    private Set<Topics> topics;
	public int getPROJCAT_ID() {
		return PROJCAT_ID;
	}

	public void setPROJCAT_ID(int pROJCAT_ID) {
		PROJCAT_ID = pROJCAT_ID;
	}

	public String getPROJCAT_Name() {
		return PROJCAT_Name;
	}

	public void setPROJCAT_Name(String pROJCAT_Name) {
		PROJCAT_Name = pROJCAT_Name;
	}

	public String getPROJCAT_Description() {
		return PROJCAT_Description;
	}

	public void setPROJCAT_Description(String pROJCAT_Description) {
		PROJCAT_Description = pROJCAT_Description;
	}

	public String getPROJCAT_Code() {
		return PROJCAT_Code;
	}

	public void setPROJCAT_Code(String pROJCAT_Code) {
		PROJCAT_Code = pROJCAT_Code;
	}

	public Set<Topics> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topics> topics) {
		this.topics = topics;
	}
}
