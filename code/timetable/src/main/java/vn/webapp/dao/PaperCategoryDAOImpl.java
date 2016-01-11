/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.webapp.model.PaperCategory;

@Repository("paperCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class PaperCategoryDAOImpl extends BaseDao implements PaperCategoryDAO {

	/**
     * Get all list Paper Category
     * @param null
     * @return List
     */
	@Override
    public List<PaperCategory> getList(){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(PaperCategory.class);
            criteria.setFirstResult(0);
            List<PaperCategory> paperCategory = criteria.list();
            commit();
            return paperCategory;
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
     * Get a paper category by code
     * @param String
     * @return object
     */
	@Override
    public PaperCategory getPaperCateByCode(String paperCateCode){
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(PaperCategory.class, "paperCategory");
            criteria.add(Restrictions.eq("paperCategory.PCAT_Code", paperCateCode));
            PaperCategory paperCategory = (PaperCategory) criteria.uniqueResult();
            commit();
            return paperCategory;
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
