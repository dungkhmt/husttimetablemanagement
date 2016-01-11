/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.DepartmentDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.Department;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    
    /**
     * Get an user by username
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Department> loadDepartmentList() {
        try {
            return departmentDAO.loadDepartmentList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Department loadDepartmentByCode(String departmentCode){
    	try {
    		if(departmentCode.equals("")){
    			return null;
    		}
            return departmentDAO.loadDepartmentByCode(departmentCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public Department loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
    		if(departmentCode.equals("") || falcutyCode.equals("")){
    			return null;
    		}
            return departmentDAO.loadADepartmentByCodes(departmentCode, falcutyCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
