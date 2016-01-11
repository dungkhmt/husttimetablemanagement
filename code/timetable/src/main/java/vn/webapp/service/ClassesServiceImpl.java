/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.ClassesDAO;
import vn.webapp.dao.StudentDAO;
import vn.webapp.model.Classes;

@Service("ClassesService")
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	private ClassesDAO classesDAO;

	/**
	 * Get active list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<Classes> listClasses() {
		try {
			return classesDAO.getListClasses();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get all list
	 * 
	 * @param String
	 * @return object
	 */
	@Override
	public List<Classes> getListAllClasses() {
		try {
			return classesDAO.getListAllClasses();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public void editAClass(int iClassId, String sClassCode, String sClassName, String sClassAsciiName,
    		String sClassFacultyCode, String sYear){
		Classes cls = classesDAO.getClassById(iClassId);
		if (cls != null) {
			cls.setClasses_Code(sClassCode);
			cls.setClasses_Name(sClassName);
			cls.setClasses_AsciiName(sClassAsciiName);
			cls.setClasses_FacultyCode(sClassFacultyCode);
			cls.setClasses_Year(sYear);
			classesDAO.editAClass(cls);
			return;
		} else
			return;
	}

	/**
	 * 
	 */
	@Override
	public int saveAClass(String sClassCode, String sClassName, String sClassFacultyCode, String sClassYear){
		Classes cls = new Classes();
		cls.setClasses_Code(sClassCode);
		cls.setClasses_Name(sClassName);
		cls.setClasses_AsciiName(sClassName);
		cls.setClasses_FacultyCode(sClassFacultyCode);
		cls.setClasses_Year(sClassYear);
		//System.out.println("ClassesServiceImpl::saveAClass, year = " + sClassYear);
    	return classesDAO.saveAClass(cls);
	}

	/**
	 * 
	 */
	@Override
	public int removeAClass(int iClassID) {
		return classesDAO.removeAClass(iClassID);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public Classes getClassById(int iClassID)
	{
		return classesDAO.getClassById(iClassID);
	}
}
