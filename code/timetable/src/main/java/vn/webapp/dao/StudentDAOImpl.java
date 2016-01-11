/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.model.Student;



@Repository("studentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class StudentDAOImpl extends BaseDao implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get Student list
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudents(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Student.class);
            List<Student> Students = criteria.list();
            commit();
            return Students;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Get Student list by department
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudentsByClasses(String classesCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Student.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Student_ClassesCode", classesCode));
            List<Student> Student = criteria.list();
            commit();
            return Student;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Get Student list by department
     * @param String
     * @return object
     */
    @Override
    public List<Student> listStudentsByStatus(int statusID){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Student.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Student_StatusID", statusID));
            List<Student> Student = criteria.list();
            commit();
            return Student;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Load A Student by id 
     * @param int
     * @return object
     */
    @Override
    public Student getStudentById(int Student_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Student.class);
            criteria.add(Restrictions.eq("Student_ID", Student_Id));
            Student professor = (Student) criteria.uniqueResult();
            commit();
            return professor;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    @Override
    public Student getStudentByCode(String StudentCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Student.class);
            criteria.add(Restrictions.eq("Student_Code", StudentCode));
            Student professor = (Student) criteria.uniqueResult();
            commit();
            return professor;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    }
    
    /**
     * Edit a Student
     * @param Object
     * @return int
     */
    @Override
    public void editAStudent(Student Student){
    	try {
            begin();
            getSession().update(Student);
            commit();
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
         } finally {
             flush();
             close();
         }
    }
    
    /**
     * Save a Student
     * @param Object
     * @return int
     */
    @Override
    public int saveAStudent(Student student){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(student);
            commit();
            return id;           
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
             return 0;
         } finally {
             flush();
             close();
         }
    }
    
    @Override
    public int removeAStudent(int StudentId){
    	Student Student = new Student();
    	Student.setStudent_ID(StudentId);
    	try {
            begin();
            getSession().delete(Student);
            commit();
            return 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return 0;
        } finally {
            flush();
            close();
        }
    }
    
}
