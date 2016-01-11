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
import vn.webapp.model.Topics;
import vn.webapp.model.Users;

@Repository("topicDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class TopicDAOImpl extends BaseDao implements TopicDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get topic list by user
     * @param null
     * @return List
     */
    @Override
    public List<Topics> loadTopicListByStaff(String userRole, String userCode) {
        try {
            begin();
            Criteria criteria = getSession().createCriteria(Topics.class, "topics");
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("topics.PROJDECL_User_Code", userCode));
            }
            criteria.addOrder(Order.desc("topics.PROJDECL_ID"));
            List<Topics> topics = criteria.list();
            commit();
            return topics;
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
     * Get topic list by user and year
     * @param null
     * @return List
     */
    @Override
    public List<Topics> loadTopicListByYear(String userRole, String userCode, String reportingrYear) {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Topics.class, "topics");
            if(!userRole.equals("SUPER_ADMIN")){
            	criteria.add(Restrictions.eq("topics.PROJDECL_User_Code", userCode));
            }
            criteria.add(Restrictions.eq("topics.PROJDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("topics.PROJDECL_Name"));
            List<Topics> topics = criteria.list();
            commit();
            return topics;
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
     * Get topic list by year
     * @param null
     * @return List
     */
    @Override
    public List<Topics> loadTopicSummaryListByYear(String reportingrYear){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Topics.class, "topics");
            criteria.add(Restrictions.eq("topics.PROJDECL_ReportingAcademicDate", reportingrYear));
            criteria.addOrder(Order.asc("topics.PROJDECL_Name"));
            List<Topics> topics = criteria.list();
            commit();
            return topics;
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
     * Save a topic
     * @param object
     * @return int
     */
    @Override
    public int saveATopic(Topics topic) 
    {
        try {
           begin();
           int id = 0; 
           id = (int)getSession().save(topic);
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
     * Load A Topic by id and User code
     * @param object
     * @return int
     */
    @Override
    public Topics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Topics.class);
            criteria.add(Restrictions.eq("PROJDECL_ID", topicId));
            if(!userRole.equals("ROLE_ADMIN")){
            	criteria.add(Restrictions.eq("PROJDECL_User_Code", userCode));
            }
            Topics topic = (Topics) criteria.uniqueResult();
            commit();
            return topic;
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
     * Edit a topic
     * @param object
     * @return int
     */
    @Override
    public void editATopic(Topics topic){
        try {
           begin();
           getSession().update(topic);
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
    public int removeATopic(int topicId){
    	Topics topic = new Topics();
    	topic.setPROJDECL_ID(topicId);    
        try {
            begin();
            getSession().delete(topic);
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
