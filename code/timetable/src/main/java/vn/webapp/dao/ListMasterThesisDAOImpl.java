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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.webapp.model.ListMasterThesis;

@Repository("listMasterThesisDAO")
@SuppressWarnings({"unchecked", "rawtypes"})

public class ListMasterThesisDAOImpl extends BaseDao implements ListMasterThesisDAO {

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
    public List<ListMasterThesis> getListMasterThesis(){
    	try {
    		begin();
            Criteria criteria = getSession().createCriteria(ListMasterThesis.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setFirstResult(0);
            List<ListMasterThesis> masterThesis = criteria.list();
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
    
}
