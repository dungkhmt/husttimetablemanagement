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

import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.Staff;

@Repository("staffDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class StaffDAOImpl extends BaseDao implements StaffDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get staff list
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffs(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Staff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<Staff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffsByDepartment(String departmentCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Staff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_Department_Code", departmentCode));
            List<Staff> staff = criteria.list();
            commit();
            return staff;
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
     * Get staff list by department
     * @param String
     * @return object
     */
    @Override
    public List<Staff> listStaffsByUniversity(String universityCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(Staff.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Staff_University", universityCode));
            List<Staff> staff = criteria.list();
            System.out.println("DAO OK");
            commit();
            return staff;
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
     * Load A Professor by id 
     * @param int
     * @return object
     */
    @Override
    public Staff getStaffById(String userRole, int staff_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Staff.class);
            criteria.add(Restrictions.eq("Staff_ID", staff_Id));
            if(!userRole.equals("ROLE_ADMIN")){
            	return null;
            }
            Staff professor = (Staff) criteria.uniqueResult();
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
     * Get an staff by staffname
     * @param staffname
     * @return object
     */
    @Override
    public Staff getByUserCode(String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Staff.class);
            criteria.add(Restrictions.eq("Staff_User_Code", userCode));
            Staff staff = (Staff) criteria.uniqueResult();
            commit();
            return staff;
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
    public Staff getByStaffCode(String staffCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Staff.class);
            criteria.add(Restrictions.eq("Staff_Code", staffCode));
            Staff staff = (Staff) criteria.uniqueResult();
            commit();
            return staff;
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
     * Edit a staff
     * @param Object
     * @return int
     */
    @Override
    public void editAStaff(Staff staff){
    	try {
            begin();
            getSession().update(staff);
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
     * Save a staff
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaff(Staff staff){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(staff);
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
    public int removeAStaff(int staffId){
    	Staff staff = new Staff();
    	staff.setStaff_ID(staffId);
    	try {
            begin();
            getSession().delete(staff);
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
