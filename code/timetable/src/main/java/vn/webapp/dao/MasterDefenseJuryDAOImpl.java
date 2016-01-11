/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.model.Department;
import vn.webapp.model.MasterDefenseJuryThesis;
import vn.webapp.model.RawMasterDefenseJuryThesis;
import vn.webapp.model.Staff;
import vn.webapp.model.StaffCategory;

@Repository("masterDefenseJuryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class MasterDefenseJuryDAOImpl extends BaseDao implements MasterDefenseJuryDAO{

	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get masterDefenseJuryThesis list
     * @param String
     * @return object
     */
    @Override
    public List<MasterDefenseJuryThesis> getListMasterDefenseJuryThesis(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(MasterDefenseJuryThesis.class);
            criteria.setFirstResult(0);
            List<MasterDefenseJuryThesis> masterDefenseJuryThesis = criteria.list();
            commit();
            return masterDefenseJuryThesis;
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
     * Get masterDefenseJuryThesis by owner list
     * @param String
     * @return object
     */
    @Override
    public List<MasterDefenseJuryThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(MasterDefenseJuryThesis.class);
            criteria.add(Restrictions.eq("MASDEFJury_StaffCode", ownerCode));
            criteria.setFirstResult(0);
            List<MasterDefenseJuryThesis> masterDefenseJuryThesis = criteria.list();
            commit();
            return masterDefenseJuryThesis;
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
     * 
     */
    @Override
    public MasterDefenseJuryThesis getMasterDefenseJuryThesisByIdAndOwner(String masterDefenseJuryCode, String ownerCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(MasterDefenseJuryThesis.class, "MasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_Code", masterDefenseJuryCode));
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            MasterDefenseJuryThesis masterDefenseJuryThesis = (MasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * 
     */
    @Override
    public MasterDefenseJuryThesis getMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode)
    {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(MasterDefenseJuryThesis.class, "MasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_ThesisCode", masterThesisCode));
            criteria.add(Restrictions.eq("MasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            MasterDefenseJuryThesis masterDefenseJuryThesis = (MasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * Edit a MasterDefenseJuryThesis
     * @param Object
     * @return int
     */
    @Override
    public void updateAMasterDefenseJuryThesis(MasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
            begin();
            getSession().update(masterDefenseJuryThesis);
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
     * Edit a MasterDefenseJuryThesis
     * @param Object
     * @return int
     */
    @Override
    public int saveAMasterThesis(MasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(masterDefenseJuryThesis);
            commit();
            return id;
         } catch (HibernateException e) {
             e.printStackTrace();
             rollback();
             close();
         } finally {
             flush();
             close();
         }
		return 0;
    }
	
    /**
     * 
     */
    @Override
    public RawMasterDefenseJuryThesis getRawMasterDefenseJuryThesisByThesisCodeAndOwner(String masterThesisCode, String ownerCode)
    {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(RawMasterDefenseJuryThesis.class, "RawMasterDefenseJuryThesis");
            criteria.add(Restrictions.eq("RawMasterDefenseJuryThesis.MASDEFJury_Code", masterThesisCode));
            criteria.add(Restrictions.eq("RawMasterDefenseJuryThesis.MASDEFJury_StaffCode", ownerCode));
            RawMasterDefenseJuryThesis masterDefenseJuryThesis = (RawMasterDefenseJuryThesis) criteria.uniqueResult();
            commit();
            return masterDefenseJuryThesis;
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
     * 
     */
    @Override
    public int removeAMasterThesis(RawMasterDefenseJuryThesis masterDefenseJuryThesis){
    	try {
            begin();
            getSession().delete(masterDefenseJuryThesis);
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
