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

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.Department;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.StaffCategory;
import vn.webapp.model.University;
import vn.webapp.model.Users;

public interface StaffService {

	public List<Staff> listStaffs();
	
	public List<Staff> listStaffsByDepartment(String departmentCode);
	
	public List<Staff> listStaffsByUniversity(String universityCode);
	
    public Staff loadStaffByUserCode(final String userCode);
    
    public Staff loadStaffByStaffCode(final String staffCode);
    
    public Staff loadStaffById(String userRole, int staff_ID);
    
    public void editAStaff(int staff_ID, String staffCode, String staffName, String staffEmail, String staffPhone, University staffUniversity, Department staffDepartment, Users user, String userRole, StaffCategory staffCategory, HashSet<SpecializationKeyword> specializationKeywords);
    
    public int saveAStaff(String staffCode, String staffName, String staffEmail, String staffPhone, University staffUniversity, Department staffDepartment, Users user, String userRole, StaffCategory staffCategory, HashSet<SpecializationKeyword> specializationKeywords);

    public int removeAStaff(int staffID);
}
