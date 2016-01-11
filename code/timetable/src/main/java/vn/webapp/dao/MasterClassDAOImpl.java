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

import vn.webapp.model.MasterClass;
import vn.webapp.model.PaperCategory;
import vn.webapp.model.Papers;
import vn.webapp.model.Staff;

@Repository("masterClassesDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class MasterClassDAOImpl extends BaseDao implements MasterClassDAO {

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
    public List<MasterClass> listMasterClass(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(MasterClass.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<MasterClass> classes = criteria.list();
            commit();
            return classes;
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
    
    public MasterClass loadMasterClassByCode(String masterClassCode){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(MasterClass.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("Classes_Code", masterClassCode));
            MasterClass classes = (MasterClass) criteria.uniqueResult();
            commit();
            return classes;
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
