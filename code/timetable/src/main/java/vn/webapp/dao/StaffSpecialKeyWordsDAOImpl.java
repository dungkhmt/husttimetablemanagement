/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.webapp.model.StaffSpecializationKeywords;

@Repository("staffSpecialKeywordsDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class StaffSpecialKeyWordsDAOImpl extends BaseDao implements StaffSpecialKeyWordsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Load staffSpecializationKeywords
     * @param int
     * @return object
     */
    @Override
    public StaffSpecializationKeywords getStaffSpecializationKeywordsByStaffAndCode(String sUserCode, String sKeywordCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(StaffSpecializationKeywords.class);
            criteria.add(Restrictions.eq("STFKW_StaffCode", sUserCode));
            criteria.add(Restrictions.eq("STFKW_KeywordCode", sKeywordCode));
            StaffSpecializationKeywords staffSpecializationKeywords = (StaffSpecializationKeywords) criteria.uniqueResult();
            commit();
            return staffSpecializationKeywords;
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
     * Save a staffSpecializationKeywords
     * @param Object
     * @return int
     */
    @Override
    public int saveAStaffSpecializationKeywords(StaffSpecializationKeywords staffSpecializationKeywords){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(staffSpecializationKeywords);
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
    
    /*@Override
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
    }*/
    
}
