/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.StaffDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.Department;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.StaffCategory;
import vn.webapp.model.University;
import vn.webapp.model.Users;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;    
       
    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffs(){
    	try {
            return staffDAO.listStaffs();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffsByDepartment(String departmentCode){
    	try {
    		if(departmentCode != null){
    			return staffDAO.listStaffsByDepartment(departmentCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffsByUniversity(String universityCode){
    	try {
    		if(universityCode != null){
    			return staffDAO.listStaffsByUniversity(universityCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an user by username
     * @param String
     * @return object
     */
    @Override
    public Staff loadStaffByUserCode(final String userCode) {
        try {
            return staffDAO.getByUserCode(userCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an professor by id
     * @param String
     * @return object
     */
     
    public Staff loadStaffById(String userRole, int staff_ID){
    	try {
            return staffDAO.getStaffById(userRole, staff_ID);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public Staff loadStaffByStaffCode(final String staffCode){
    	try {
            return staffDAO.getByStaffCode(staffCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a staff
     * @param String
     * @return int
     */
    @Override
    public void editAStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, University staffUniversity, Department staffDepartment, Users user, String userRole, StaffCategory staffCategory, HashSet<SpecializationKeyword> specializationKeywords){
    	
    	 Staff staff = staffDAO.getStaffById(userRole, staff_ID);
    	 if(staff != null){
    		 //staff.setUniversity(staffUniversity);
    		 staff.setStaff_University_Code(staffUniversity.getUniversity_Code());
    		 staff.setDepartment(staffDepartment);
	     	 staff.setStaff_AsciiName(staffName);
	     	 staff.setStaff_Name(staffName);
	     	 staff.setStaff_Phone(staffPhone);
	     	 staff.setStaffCategory(staffCategory);
	     	 staff.setUser(user);
	     	 staff.setStaff_Email(staffEmail);
	     	 staff.setStaff_Code(staffCode);
	     	 staff.setListSpecializationKeywords(specializationKeywords);
	     	 
	     	
	    	staffDAO.editAStaff(staff);
	    	return;
	    }
    	else
    		return;
    }
    
    /**
     * Save a staff
     * @param String
     * @return int
     */
    @Override
    public int saveAStaff(String staffCode, String staffName, String staffEmail, String staffPhone, University staffUniversity, Department staffDepartment, Users user, String userRole, StaffCategory staffCategory, HashSet<SpecializationKeyword> specializationKeywords){
    	
    	Staff staff = new Staff();
		//staff.setUniversity(staffUniversity);
		staff.setStaff_University_Code(staffUniversity.getUniversity_Code());
    	staff.setDepartment(staffDepartment);
		staff.setStaff_AsciiName(staffName);
		staff.setStaff_Name(staffName);
		staff.setStaff_Phone(staffPhone);
		staff.setStaffCategory(staffCategory);
		staff.setUser(user);
		staff.setStaff_Email(staffEmail);
		staff.setStaff_Code(staffCode);
		staff.setListSpecializationKeywords(specializationKeywords);

    	return staffDAO.saveAStaff(staff);
    }
    
    /**
     * Remove a staff
     * @param int
     * @return int
     */
    @Override
    public int removeAStaff(int staffId){
    	return staffDAO.removeAStaff(staffId);
    }
}
