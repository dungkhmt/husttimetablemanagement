/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.webapp.model.Department;
import vn.webapp.model.Papers;
import vn.webapp.model.Staff;
import vn.webapp.model.Users;

@Repository("paperDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class PaperDAOImpl extends BaseDao implements PaperDAO {

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
    public List<Papers> loadPaperListByStaff(String userRole, String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Papers.class, "papers");
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            }
            criteria.addOrder(Order.desc("papers.PDECL_ID"));
            List<Papers> papers = criteria.list();
            commit();
            return papers;
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
     * Get papers list by year and user
     * @param null
     * @return List
     */
    @Override
    public List<Papers> loadPaperListByYear(String userRole, String userCode, String reportingrYear){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Papers.class, "papers");
            if(!userRole.equals("SUPER_ADMIN")){
            	criteria.add(Restrictions.eq("papers.PDECL_User_Code", userCode));
            }
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<Papers> papers = criteria.list();
            commit();
            return papers;
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
     * Get papers list by year
     * @param null
     * @return List
     */
    @Override
    public List<Papers> loadPaperSummaryListByYear(String reportingrYear){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Papers.class, "papers");
            criteria.add(Restrictions.eq("papers.PDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("papers.PDECL_PublicationName"));
            List<Papers> papers = criteria.list();
            commit();
            return papers;
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
     * Save a paper
     * @param object
     * @return int
     */
    @Override
    public int saveAPaper(Papers paper) 
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(paper);
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
    
    /**
     * Load A Paper by id and User code
     * @param object
     * @return int
     */
    @Override
    public Papers loadAPaperByIdAndUserCode(String userRole, String userCode, int paperId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Papers.class);
            criteria.add(Restrictions.eq("PDECL_ID", paperId));
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("PDECL_User_Code", userCode));
            }
            Papers paper = (Papers) criteria.uniqueResult();
            commit();
            return paper;
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
     * Edit a paper
     * @param object
     * @return int
     */
    @Override
    public void editAPaper(Papers paper){
        try {
           begin();
           getSession().update(paper);
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
     * Remove a paper
     * @param paperId
     * @return
     */
    @Override
    public int removeAPaper(int paperId){
    	Papers paper = new Papers();
    	paper.setPDECL_ID(paperId);    
        try {
            begin();
            getSession().delete(paper);
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
