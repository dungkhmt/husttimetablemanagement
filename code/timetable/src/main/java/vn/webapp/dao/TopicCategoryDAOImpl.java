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
import vn.webapp.model.TopicCategory;

@Repository("topicCategoryDAO")
@SuppressWarnings({"unchecked", "rawtypes"})
public class TopicCategoryDAOImpl extends BaseDao implements TopicCategoryDAO {

	/**
     * Get all list Topic Category
     * @param null
     * @return List
     */
	@Override
	public List<TopicCategory> getList() {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(TopicCategory.class);
            criteria.setFirstResult(0);
            List<TopicCategory> topicCategory = criteria.list();
            commit();
            return topicCategory;
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
	public TopicCategory getTopicCategoryByCode(String topicCategoryCode) {
    	try {
            begin();
            Criteria criteria = getSession().createCriteria(TopicCategory.class, "topicCategory");
            criteria.add(Restrictions.eq("topicCategory.PROJCAT_Code", topicCategoryCode));
            TopicCategory topicCategory = (TopicCategory) criteria.uniqueResult();
            commit();
            return topicCategory;
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
