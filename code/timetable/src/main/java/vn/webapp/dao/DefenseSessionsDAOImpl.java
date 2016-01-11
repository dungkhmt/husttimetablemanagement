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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.model.DefenseSession;

@Repository("defenseSessionDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class DefenseSessionsDAOImpl extends BaseDao implements DefenseSessionsDAO{

	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get defenseSession list
     * @param String
     * @return object
     */
    @Override
    public List<DefenseSession> getListDefenseSession(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(DefenseSession.class);
            criteria.setFirstResult(0);
            criteria.add(Restrictions.eq("DEFSESS_Enabled", 1));
            List<DefenseSession> defenseSession = criteria.list();
            commit();
            return defenseSession;
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
     * Get defenseSession list
     * @param String
     * @return object
     */
    @Override
    public List<DefenseSession> getListAllDefenseSession(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(DefenseSession.class);
            criteria.setFirstResult(0);
            List<DefenseSession> defenseSession = criteria.list();
            commit();
            return defenseSession;
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
    public void editADefenseSession(DefenseSession defenseSession){
    	try {
            begin();
            getSession().update(defenseSession);
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
    
    @Override
    public int saveADefenseSession(DefenseSession defenseSession){
    	try {
            begin();
            int id = 0; 
            id = (int)getSession().save(defenseSession);
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
    public int removeADefenseSession(int iDefenseSessionId){
    	DefenseSession defenseSession = new DefenseSession();
    	defenseSession.setDEFSESS_ID(iDefenseSessionId);
    	try {
            begin();
            getSession().delete(defenseSession);
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
    
    /**
     * Load A DefenseSession by id 
     * @param int
     * @return object
     */
    @Override
    public DefenseSession getDefenseSessionById(int iDefenseSessionId){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(DefenseSession.class);
            criteria.add(Restrictions.eq("DEFSESS_ID", iDefenseSessionId));
            DefenseSession professor = (DefenseSession) criteria.uniqueResult();
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
}
