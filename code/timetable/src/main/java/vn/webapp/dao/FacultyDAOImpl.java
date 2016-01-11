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

import vn.webapp.model.Faculty;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Staff;
import vn.webapp.model.Users;

@Repository("facultyDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class FacultyDAOImpl extends BaseDao implements FacultyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get Faculty list
     * @param null
     * @return List
     */
    @Override
    public List<Faculty> loadFacultyList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Faculty.class);
            List<Faculty> faculty = criteria.list();
            commit();
            return faculty;
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
     * Get a Faculty by falcuty code
     * @param null
     * @return object
     */
    @Override
    public Faculty loadAFacultyByCodes(String facultyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Faculty.class, "Faculty");
            criteria.add(Restrictions.eq("Faculty.Faculty_Code", facultyCode));
            Faculty faculty = (Faculty) criteria.uniqueResult();
            commit();
            return faculty;
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
    
    
}
