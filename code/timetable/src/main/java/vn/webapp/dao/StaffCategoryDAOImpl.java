/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.model.Department;
import vn.webapp.model.StaffCategory;

@Repository("staffCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class StaffCategoryDAOImpl extends BaseDao implements StaffCategoryDAO {

	/**
     * Get all list Staff Category
     * @param null
     * @return List
     */
    public List<StaffCategory> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(StaffCategory.class);
            criteria.setFirstResult(0);
            List<StaffCategory> staffCategory = criteria.list();
            commit();
            return staffCategory;
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
    
    public StaffCategory getByCode(String staffCategoryCode){
    	
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(StaffCategory.class, "StaffCategory");
            criteria.add(Restrictions.eq("StaffCategory.Staff_Category_Code", staffCategoryCode));
            StaffCategory staffCategory = (StaffCategory) criteria.uniqueResult();
            commit();
            return staffCategory;
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
