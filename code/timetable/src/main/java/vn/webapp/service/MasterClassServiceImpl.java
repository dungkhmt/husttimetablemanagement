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

import vn.webapp.dao.MasterClassDAO;
import vn.webapp.dao.DepartmentDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.MasterClass;
import vn.webapp.model.Department;

@Service("classesService")
public class MasterClassServiceImpl implements MasterClassService {

    @Autowired
    private MasterClassDAO masterClassDAO;

    
    @Override
    public List<MasterClass> loadMasterClassList() {
        try {
            return masterClassDAO.listMasterClass();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public MasterClass loadMasterClassByCode(String masterClassCode){
    	try {
            return masterClassDAO.loadMasterClassByCode(masterClassCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
}
