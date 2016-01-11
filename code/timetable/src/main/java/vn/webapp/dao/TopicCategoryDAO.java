/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;
import vn.webapp.model.TopicCategory;

public interface TopicCategoryDAO {

    public List<TopicCategory> getList();
    
    public TopicCategory getTopicCategoryByCode(String topicCategoryCode);
    
}
