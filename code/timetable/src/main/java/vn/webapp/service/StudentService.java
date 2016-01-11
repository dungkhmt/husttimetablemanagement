/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.webapp.dto.DataPage;
import vn.webapp.model.Faculty;
import vn.webapp.model.MasterClass;
import vn.webapp.model.Student;

public interface StudentService {

	public List<Student> listStudents();
	
	public List<Student> listStudentsByClasses(String classesCode);
	
	public List<Student> listStudentsByStatus(int statusID);
	
	public Student loadStudentByCode(String StudentCode);
	
	public Student loadStudentById(int Student_ID);
    
    public void editAStudent(Student Student);
    
    public int saveAStudent(String StudentCode, String StudentName, String StudentEmail, String StudentPhone, String studentClassCode, Faculty faculty, int StatusID );
    
    public int removeAStudent(int StudentID);
}
