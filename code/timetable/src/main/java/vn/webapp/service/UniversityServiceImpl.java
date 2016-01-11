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
import vn.webapp.dao.FacultyDAO;
import vn.webapp.dao.UniversityDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.Department;
import vn.webapp.model.Faculty;
import vn.webapp.model.University;

@Service("universityService")
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityDAO universityDAO;

    
    /**
     * Get an user by university
     * @param String
     * @return object
     * @throws UniversityNotFoundException
     */
    @Override
    public List<University> loadUniversityList() {
        try {
            return universityDAO.loadUniversityList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a university by its code
     * @param null
     * @return object
     */
    @Override
    public University loadAUniversityByCodes(String universityCode){
    	try {
    		if(universityCode.equals("")){
    			return null;
    		}
            return universityDAO.loadAUniversityByCodes(universityCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
}
