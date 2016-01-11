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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.dao.StudentDAO;
import vn.webapp.dto.DataPage;
import vn.webapp.model.Faculty;
import vn.webapp.model.MasterClass;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Student;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO StudentDAO;
    
    
    /**
     * Get Student list
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudents(){
    	try {
            return StudentDAO.listStudents();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get Student list by class
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudentsByClasses(String classesCode){
    	try {
    		if(classesCode != null){
    			return StudentDAO.listStudentsByClasses(classesCode);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get Student list by class
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudentsByStatus(int statusID){
    	try {
    		return StudentDAO.listStudentsByStatus(statusID);    		
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get an professor by id
     * @param String
     * @return object
     */
     
    public Student loadStudentById(int Student_ID){
    	try {
            return StudentDAO.getStudentById(Student_ID);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    public Student loadStudentByCode(String StudentCode){
    	try {
            return StudentDAO.getStudentByCode(StudentCode);
            
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Edit a Student
     * @param String
     * @return int
     */
    @Override
    public void editAStudent(Student Student){    	
    	
    	StudentDAO.editAStudent(Student);
    }
    
    /**
     * Save a Student
     * @param String
     * @return int
     */
    @Override
    public int saveAStudent(String StudentCode, String StudentName, String StudentEmail, String StudentPhone, String studentClassCode, Faculty faculty, int StatusID ){
    	
    	Student Student = new Student();
    	Student.setStudent_Code(StudentCode);
    	Student.setStudent_Name(StudentName);
    	Student.setStudent_Email(StudentEmail);
    	Student.setStudent_Phone(StudentPhone);
    	Student.setStudent_ClassesCode(studentClassCode);
    	Student.setStudent_StatusID(StatusID);
    	
    	return StudentDAO.saveAStudent(Student);
    }
    
    /**
     * Remove a Student
     * @param int
     * @return int
     */
    @Override
    public int removeAStudent(int StudentId){
    	return StudentDAO.removeAStudent(StudentId);
    }
}
