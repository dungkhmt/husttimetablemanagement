/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;
import vn.webapp.model.TopicCategory;

public interface TopicCategoryService {

    public List<TopicCategory> list();
    
    public TopicCategory getTopicCategoryByCode(String topicCategoryCode);
}
