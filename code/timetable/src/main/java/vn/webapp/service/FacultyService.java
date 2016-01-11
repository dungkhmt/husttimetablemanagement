/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.Faculty;

public interface FacultyService {

    public List<Faculty> loadFacultyList();
    
    public Faculty loadAFacultyByCodes(String facultyCode);
    
}
