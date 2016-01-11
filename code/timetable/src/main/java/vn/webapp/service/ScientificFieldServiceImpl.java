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

import vn.webapp.dao.ScientificFieldDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.ScientificField;

@Service("scientificFieldService")
public class ScientificFieldServiceImpl implements ScientificFieldService {

    @Autowired
    private ScientificFieldDAO scientificFieldDAO;

    
    /**
     * @param String
     * @return object
     */
    @Override
    public List<ScientificField> loadScientificFieldList() {
        try {
            return scientificFieldDAO.loadScientificFieldList();
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
        
}
