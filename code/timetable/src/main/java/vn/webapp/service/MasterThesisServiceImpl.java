/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.DefenseSessionsDAO;
import vn.webapp.dao.ListMasterThesisDAO;
import vn.webapp.dao.MasterThesisDAO;
import vn.webapp.dao.StaffSpecialKeyWordsDAO;
import vn.webapp.dao.StudentDAO;
import vn.webapp.model.DefenseSession;
import vn.webapp.model.ListMasterThesis;
import vn.webapp.model.MasterThesis;
import vn.webapp.model.RawMasterThesis;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.StaffSpecializationKeywords;
import vn.webapp.model.Student;

@Service("masterThesisService")
public class MasterThesisServiceImpl implements MasterThesisService {

    @Autowired
    private MasterThesisDAO masterThesisDAO;
    
    @Autowired
    private ListMasterThesisDAO listMasterThesisDAO;
    
    @Autowired
    private DefenseSessionsDAO listDefenseSessionDAO;
    
    @Autowired
    private StudentDAO studentDAO;
    
    @Autowired
    private StaffSpecialKeyWordsDAO staffSpecialKeyWordsDAO;
    
    /**
     * Get MasterThesis list
     * @param String
     * @return object
     */
    @Override
    public List<MasterThesis> listMasterThesis(){
    	try {
            return masterThesisDAO.listMasterThesis();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
       
    /**
     * Get an MasterThesis by id
     * @param String
     * @return object
     */
     
    public MasterThesis loadMasterThesisById(int masterThesisID){
    	try {
            return masterThesisDAO.getMasterThesisById(masterThesisID);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a MasterThesis
     * @param String
     * @return int
     */
    @Override
    public void editAMasterThesis(int ThesisID, String ThesisCode, String ThesisName, Student student, Staff supervisor, HashSet<SpecializationKeyword> specializationKeywords){
    	
    	 MasterThesis masterThesis = masterThesisDAO.getMasterThesisById(ThesisID);
    	 if(masterThesis != null){
    	   	masterThesis.setThesis_Name(ThesisName);
    	   	masterThesis.setThesis_Code(ThesisCode);
    	   	//masterThesis.setStudent(student);
    	   	//masterThesis.setSupervisor(supervisor);
    		masterThesis.setListSpecializationKeywords(specializationKeywords);
    	   	   		 
    		masterThesisDAO.editAMasterThesis(masterThesis);
	    	return;
	    }
    	else
    		return;
    }
    
    /**
     * Save a MasterThesis
     * @param String
     * @return int
     */
    @Override
    public int saveAMasterThesis(String ThesisCode, String ThesisName, String studentCode, String supervisor, 
    								HashSet<SpecializationKeyword> specializationKeywords, Student student){
    	
    	// Save info for MasterThesis
    	RawMasterThesis masterThesis = new RawMasterThesis();
    	masterThesis.setThesis_Name(ThesisName);
	   	masterThesis.setThesis_Code(ThesisCode);
	   	masterThesis.setThesis_StudentCode(studentCode);
	   	masterThesis.setThesis_SupervisorCode(supervisor);
	   	
	   	// Save special keywords for a staff
	   	if(specializationKeywords != null)
	   	{
	   		for (SpecializationKeyword specializationKeyword : specializationKeywords) {
	   			
	   			StaffSpecializationKeywords staffSpecializationKeywords = staffSpecialKeyWordsDAO.getStaffSpecializationKeywordsByStaffAndCode(supervisor, specializationKeyword.getKW_Code());
	   			if(staffSpecializationKeywords == null)
	   			{
	   				StaffSpecializationKeywords newStaffSpecializationKeywords = new StaffSpecializationKeywords();
	   				
	   				newStaffSpecializationKeywords.setSTFKW_KeywordCode(specializationKeyword.getKW_Code());
	   				newStaffSpecializationKeywords.setSTFKW_StaffCode(supervisor);
	   				staffSpecialKeyWordsDAO.saveAStaffSpecializationKeywords(newStaffSpecializationKeywords);
	   			}
	   				
			}
	   	}
		
		// Change status of a Student
		student.setStudent_StatusID(1);
		studentDAO.editAStudent(student);
		
		return masterThesisDAO.saveAMasterThesis(masterThesis);
    }
    
    /**
     * Remove a MasterThesis
     * @param int
     * @return int
     */
    @Override
    public int removeAMasterThesis(int masterThesisID){
    	return masterThesisDAO.removeAMasterThesis(masterThesisID);
    }
    
    /**
     * 
     */
    @Override
    public List<ListMasterThesis> getListMasterThesis(){
    	try {
            return listMasterThesisDAO.getListMasterThesis();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get DefenseSession list
     * @param String
     * @return object
     */
    @Override
    public List<DefenseSession> listDefenseSession(){
    	try {
            return listDefenseSessionDAO.getListDefenseSession();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
}
