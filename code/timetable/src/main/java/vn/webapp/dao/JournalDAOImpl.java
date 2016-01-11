/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import vn.webapp.model.Journal;
import vn.webapp.model.PaperCategory;

@Repository("journalDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class JournalDAOImpl extends BaseDao implements JournalDAO {

	/**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    public List<Journal> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(Journal.class);
            criteria.setFirstResult(0);
            List<Journal> journal = criteria.list();
            commit();
            return journal;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
            close();
            return null;
        } finally {
            flush();
            close();
        }
    };
    
}
