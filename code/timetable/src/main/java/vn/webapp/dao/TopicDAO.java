/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dao;

import java.util.List;

import vn.webapp.model.Topics;

public interface TopicDAO {

    public List<Topics> loadTopicListByStaff(String userRole, String userCode);
    
    public List<Topics> loadTopicListByYear(String userRole, String userCode, String reportingrYear);
    
    public List<Topics> loadTopicSummaryListByYear(String reportingrYear);
    
    public int saveATopic(Topics topic);
    
    public Topics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId);
    
    public void editATopic(Topics topic);
    
    public int removeATopic(int topicId);
    
}
