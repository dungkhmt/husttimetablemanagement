/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import vn.webapp.model.Papers;
import vn.webapp.model.Topics;

public interface TopicService {

    public List<Topics> loadTopicListByStaff(String userRole, String userCode);
    
    public List<Topics> loadTopicListByYear(String userRole, String userCode, String reportingrYear);
    
    public List<Topics> loadTopicSummaryListByYear(String reportingrYear);
    
    public int saveATopic(String userCode, String topicPubName, String topicCategory, int topicConVertedHours, 
    						int topicAutConHours, int topicYear, int topicBudget, String topicReportingAcademicDate);
    
    public Topics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId);
    
    public void editATopic(String userRole, String userCode, int topicId, String topicPubName, String topicCategory, 
    						int topicConVertedHours, Integer topicAutConHours, int topicYear, int topicBudget, String topicReportingAcademicDate);
    
    public int removeATopic(int topicId);
    
}
