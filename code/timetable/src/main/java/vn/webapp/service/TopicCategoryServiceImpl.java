/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.TopicCategoryDAO;
import vn.webapp.model.TopicCategory;

@Service("topicCategoryService")
public class TopicCategoryServiceImpl implements TopicCategoryService{

    @Autowired
    private TopicCategoryDAO topicCategoryDAO;

    /**
     * Get all list Paper Category
     * @param null
     * @return List
     */
    @Override
    public List<TopicCategory> list(){
    	return topicCategoryDAO.getList();
    }
    
    /**
     * get a paper category by code
     * @param String
     * @return object
     */
    @Override
    public TopicCategory getTopicCategoryByCode(String topicCategoryCode){
    	if(topicCategoryCode != null){
    		return topicCategoryDAO.getTopicCategoryByCode(topicCategoryCode);
    	}
    	return null;
    }
}
