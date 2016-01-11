/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.ScientificFieldDAO;
import vn.webapp.dao.SpecializationKeywordDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.ScientificField;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;

@Service("specializationKeywordService")
public class SpecializationKeywordServiceImpl implements SpecializationKeywordService {

    @Autowired
    private SpecializationKeywordDAO specializationKeywordDAO;

    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<SpecializationKeyword> loadSpecializationKeywordList() {
        try {
            return specializationKeywordDAO.loadSpecializationKeywordList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<SpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code) {
        try {
            return specializationKeywordDAO.loadSpecializationKeywordByScientificField(SCIF_Code);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }        
    
    /**
     * @param String
     * @return object
     */
     public SpecializationKeyword getSpecializationKeywordByCode(String KW_Code){
    	 try {
             return specializationKeywordDAO.getSpecializationKeywordByCode(KW_Code);
             
         } catch (Exception e) {
             System.out.println("Exception: " + e.getMessage());
             return null;
         }
     }
     public List<SpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode){
    	 return specializationKeywordDAO.loadStaffSpecializationKeywordList(staffCode);
     }
}
