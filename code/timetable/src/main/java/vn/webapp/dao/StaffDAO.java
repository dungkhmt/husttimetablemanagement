/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Staff;
import vn.webapp.model.Users;

public interface StaffDAO {

	public List<Staff> listStaffs();
	
	public List<Staff> listStaffsByDepartment(String departmentCode);
	
	public List<Staff> listStaffsByUniversity(String universityCode);
		
	public Staff getStaffById(String userRole, int staff_Id);
	
	public Staff getByStaffCode(String staffCode);
	
	public Staff getByUserCode(String userCode);
    
    public void editAStaff(Staff staff);
    
    public int saveAStaff(Staff staff);
    
    public int removeAStaff(int staffId);
    
}
