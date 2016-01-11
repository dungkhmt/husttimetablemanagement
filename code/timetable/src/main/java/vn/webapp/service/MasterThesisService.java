/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.HashSet;
import java.util.List;

import vn.webapp.model.DefenseSession;
import vn.webapp.model.ListMasterThesis;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.MasterThesis;
import vn.webapp.model.Staff;
import vn.webapp.model.Student;

public interface MasterThesisService {

	public List<MasterThesis> listMasterThesis();
	
	public MasterThesis loadMasterThesisById(int masterThesis_ID);
	  
    public void editAMasterThesis(int ThesisID, String ThesisCode, String ThesisName, Student student, Staff supervisor, HashSet<SpecializationKeyword> specializationKeywords);
    
    public int saveAMasterThesis(String ThesisCode, String ThesisName, String studentCode, String supervisor, HashSet<SpecializationKeyword> specializationKeywords, Student student);

    public int removeAMasterThesis(int ThesisID);
    
    public List<ListMasterThesis> getListMasterThesis();
    
    public List<DefenseSession> listDefenseSession();
}
