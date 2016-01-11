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

import vn.webapp.model.MasterThesis;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.RawMasterThesis;
import vn.webapp.model.Staff;

@Repository("masterThesisDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class MasterThesisDAOImpl extends BaseDao implements MasterThesisDAO {

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
    public List<MasterThesis> listMasterThesis(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(MasterThesis.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<MasterThesis> masterThesis = criteria.list();
            commit();
            return masterThesis;
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
    public MasterThesis getMasterThesisById(int masterThesis_Id){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Staff.class);
            criteria.add(Restrictions.eq("Thesis_ID", masterThesis_Id));
            
            MasterThesis masterThesis = (MasterThesis) criteria.uniqueResult();
            commit();
            return masterThesis;
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
     * Edit a master thesis
     * @param Object
     * @return int
     */
    @Override
    public void editAMasterThesis(MasterThesis masterThesis){
    	try {
            begin();
            getSession().update(masterThesis);
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
     * Save a master thesis
     * @param Object
     * @return int
     */
    @Override
    public int saveAMasterThesis(RawMasterThesis masterThesis){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(masterThesis);
            commit();
            System.out.println("OK");
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
    public int removeAMasterThesis(int masterThesisId){
    	MasterThesis masterThesis = new MasterThesis();
    	masterThesis.setThesis_ID(masterThesisId);
    	try {
            begin();
            getSession().delete(masterThesis);
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
