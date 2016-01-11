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
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.model.Department;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.ScientificField;
import vn.webapp.model.SpecializationKeyword;
import vn.webapp.model.Staff;
import vn.webapp.model.Users;

@Repository("specializationKeywordDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class SpecializationKeywordDAOImpl extends BaseDao implements SpecializationKeywordDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get specialization keyword list
     * @param null
     * @return List
     */
    @Override
    public List<SpecializationKeyword> loadSpecializationKeywordList() {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(SpecializationKeyword.class);
            List<SpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            return specializationKeywordList;
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
     * Get specialization keyword list by scientific field
     * @param null
     * @return List
     */
    @Override
    public List<SpecializationKeyword> loadSpecializationKeywordByScientificField(String SCIF_Code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(SpecializationKeyword.class, "specializationKeyword");
            criteria.add(Restrictions.eq("specializationKeyword.KW_ScientificFieldCode", SCIF_Code));
            List<SpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            return specializationKeywordList;
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
     * Get specialization keyword list by code
     * @param null
     * @return List
     */
    @Override
    public SpecializationKeyword getSpecializationKeywordByCode(String KW_Code){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(SpecializationKeyword.class, "specializationKeyword");
            criteria.add(Restrictions.eq("specializationKeyword.KW_Code", KW_Code));
            List<SpecializationKeyword> specializationKeywordList = criteria.list();
            commit();
            if(specializationKeywordList != null){
            	return specializationKeywordList.get(0);
            }
            else
            	return null;
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
    public List<SpecializationKeyword> loadStaffSpecializationKeywordList(String staffCode){
    	try {
            begin();
            SQLQuery q = getSession().createSQLQuery("SELECT DISTINCT KW_ID,KW_Code,KW_EngName,KW_VNName,KW_ScientificFieldCode FROM `tblspecializationkeywords` AS t1 JOIN (SELECT * FROM `tblstaffspecializationkeywords` WHERE STFKW_StaffCode = '"+staffCode+"') AS t2 ON t1.KW_Code = t2.STFKW_KeywordCode");
    		q.addEntity(SpecializationKeyword.class);
            commit();
            return q.list();
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
