package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Student;

public interface StudentDAO {
	
	public List<Student> listStudents();
	
	public List<Student> listStudentsByClasses(String classesCode);
	
	public List<Student> listStudentsByStatus(int statusID);
		
	public Student getStudentById(int Student_Id);
	
	public Student getStudentByCode(String StudentCode);
	
	public void editAStudent(Student Student);
    
    public int saveAStudent(Student Student);
    
    public int removeAStudent(int StudentId);

}
