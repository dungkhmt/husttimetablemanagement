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
import vn.webapp.model.Department;

public interface DepartmentService {

    public List<Department> loadDepartmentList();
    
    public Department loadDepartmentByCode(String departmentCode);
    
    public Department loadADepartmentByCodes(String departmentCode, String falcutyCode);
    
}
