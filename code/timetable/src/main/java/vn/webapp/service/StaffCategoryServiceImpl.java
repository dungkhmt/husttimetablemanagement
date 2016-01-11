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
import org.springframework.stereotype.Service;

import vn.webapp.dao.StaffCategoryDAO;
import vn.webapp.model.StaffCategory;

@Service("staffCategoryService")
public class StaffCategoryServiceImpl implements StaffCategoryService{

    @Autowired
    private StaffCategoryDAO staffCategoryDAO;

    public void setStaffCategoryDAO(StaffCategoryDAO staffCategoryDAO) {
        this.staffCategoryDAO = staffCategoryDAO;
    }
    
    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<StaffCategory> list(){
    	return staffCategoryDAO.getList();
    }
    
    public StaffCategory getByCode(String staffCategoryCode){
    	return staffCategoryDAO.getByCode(staffCategoryCode);
    }
}
