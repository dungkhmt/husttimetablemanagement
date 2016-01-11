/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Classes;

public interface ClassesDAO {

	public List<Classes> getListClasses();
	
	public List<Classes> getListAllClasses();
	
	public void editAClass(Classes aClass);
    
    public int saveAClass(Classes aClass);
    
    public int removeAClass(int iClassId);
    
    public Classes getClassById(int iClassId);
}
