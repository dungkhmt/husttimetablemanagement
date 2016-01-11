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

import vn.webapp.dao.AcademicYearDAO;
import vn.webapp.model.AcademicYear;

@Service("academicYearService")
public class AcademicYearServiceImpl implements AcademicYearService {

    @Autowired
    private AcademicYearDAO academicYearDAO;

    /**
     * Get all list of academic year
     * @param null
     * @return List
     */
    @Override
    public List<AcademicYear> list(){
    	return academicYearDAO.getList();
    }
}
