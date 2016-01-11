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
@Table(name = "tblpapercategory")
public class PaperCategory implements Serializable{
    
    @Id
    @GeneratedValue
    private int PCAT_ID;
    private String PCAT_Name;
    private String PCAT_Description;
    private String PCAT_Code;
    private String PCAT_Journal;
    private Integer PCAT_ConvertedHours;
    private int PCAT_Budget;
    
    public PaperCategory(){
    }
    
    @OneToMany(mappedBy="paperCategory")
    private Set<Papers> papers;

	public int getPCAT_ID() {
		return PCAT_ID;
	}

	public void setPCAT_ID(int pCAT_ID) {
		PCAT_ID = pCAT_ID;
	}

	public String getPCAT_Name() {
		return PCAT_Name;
	}

	public void setPCAT_Name(String pCAT_Name) {
		PCAT_Name = pCAT_Name;
	}

	public String getPCAT_Description() {
		return PCAT_Description;
	}

	public void setPCAT_Description(String pCAT_Description) {
		PCAT_Description = pCAT_Description;
	}

	public String getPCAT_Code() {
		return PCAT_Code;
	}

	public void setPCAT_Code(String pCAT_Code) {
		PCAT_Code = pCAT_Code;
	}

	public String getPCAT_Journal() {
		return PCAT_Journal;
	}

	public void setPCAT_Journal(String pCAT_Journal) {
		PCAT_Journal = pCAT_Journal;
	}

	public Set<Papers> getPapers() {
		return papers;
	}

	public void setPapers(Set<Papers> papers) {
		this.papers = papers;
	}

	public Integer getPCAT_ConvertedHours() {
		return PCAT_ConvertedHours;
	}

	public void setPCAT_ConvertedHours(Integer pCAT_ConvertedHours) {
		PCAT_ConvertedHours = pCAT_ConvertedHours;
	}

	public int getPCAT_Budget() {
		return PCAT_Budget;
	}

	public void setPCAT_Budget(int pCAT_Budget) {
		PCAT_Budget = pCAT_Budget;
	}
}
