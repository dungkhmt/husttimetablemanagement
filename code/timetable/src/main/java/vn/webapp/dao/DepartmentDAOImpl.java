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

import vn.webapp.model.Department;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Staff;
import vn.webapp.model.Users;

@Repository("departmentDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class DepartmentDAOImpl extends BaseDao implements DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get department list
     * @param null
     * @return List
     */
    @Override
    public List<Department> loadDepartmentList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Department.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Department> department = criteria.list();
            commit();
            return department;
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
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public Department loadDepartmentByCode(String departmentCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Department.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Code", departmentCode));
            Department department = (Department) criteria.uniqueResult();
            commit();
            return department;
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
     * Get a department by its code and falcuty code
     * @param null
     * @return object
     */
    @Override
    public Department loadADepartmentByCodes(String departmentCode, String falcutyCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Department.class, "Department");
            criteria.add(Restrictions.eq("Department.Department_Code", departmentCode));
            criteria.add(Restrictions.eq("Department.Department_Faculty_Code", falcutyCode));
            Department department = (Department) criteria.uniqueResult();
            commit();
            return department;
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
