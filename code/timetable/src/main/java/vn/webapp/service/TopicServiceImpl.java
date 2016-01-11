/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.webapp.dao.TopicDAO;
import vn.webapp.dao.UserDAO;
import vn.webapp.model.Topics;
import vn.webapp.model.Users;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private UserDAO userDAO;
    
    /**
     * Get a list Topics by user code
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Topics> loadTopicListByStaff(String userRole, String userCode) {
        try {
        	return topicDAO.loadTopicListByStaff(userRole, userCode);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Topics by year and user
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Topics> loadTopicListByYear(String userRole, String userCode,  String reportingrYear) {
    	try {
    		if(userCode != null){
    			return topicDAO.loadTopicListByYear(userRole, userCode, reportingrYear);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get a list Topics by year
     * @param String
     * @return object
     * @throws UsernameNotFoundException
     */
    @Override
    public List<Topics> loadTopicSummaryListByYear(String reportingrYear){
    	try {
    		if(reportingrYear != null){
    			return topicDAO.loadTopicSummaryListByYear(reportingrYear);
    		}
    		return null;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Save a topic
     * @param String
     * @param String
     * @param String
     * @param String
     * @return int
     */
    @Override
    public int saveATopic(String userCode, String topicPubName, String topicCategory, int topicConVertedHours, 
    						int topicAutConHours, int topicYear, int topicBudget, String topicReportingAcademicDate)
    {
    	if(userCode != null){
    		Topics topic = new Topics();
    		topic.setPROJDECL_Name(topicPubName);
    		topic.setPROJDECL_ProjCategory_Code(topicCategory);
    		topic.setPROJDECL_User_Code(userCode);
    		topic.setPROJDECL_Year(topicYear);
    		topic.setPROJDECL_ConvertedHours(topicConVertedHours);
    		topic.setPROJDECL_AuthorConvertedHours(topicAutConHours);
    		topic.setPROJDECL_Budget(topicBudget);
    		topic.setPROJDECL_ReportingAcademicDate(topicReportingAcademicDate);
            int i_SaveATopic = topicDAO.saveATopic(topic);
            return i_SaveATopic;
    	}
        return 0;
    }
    
    /**
     * load a paper by usercode and it's id
     * @param String
     * @param int
     * @return object
     */
    @Override
    public Topics loadATopicByIdAndUserCode(String userRole, String userCode, int topicId){
    	try {
    		return topicDAO.loadATopicByIdAndUserCode(userRole, userCode, topicId);
    	} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    /**
     * Edit a topic
     * @param String
     * @param int
     * @return null
     */
    @Override
    public void editATopic(String userRole, String userCode, int topicId, String topicPubName, String topicCategory, 
    						int topicConVertedHours, Integer topicAutConHours, int topicYear, int topicBudget, String topicReportingAcademicDate){
    	Topics topic = topicDAO.loadATopicByIdAndUserCode(userRole, userCode, topicId);
    	if(topic != null){
    		topic.setPROJDECL_ID(topicId);
    		topic.setPROJDECL_Name(topicPubName);
    		topic.setPROJDECL_ProjCategory_Code(topicCategory);
    		topic.setPROJDECL_User_Code(userCode);
    		topic.setPROJDECL_Year(topicYear);
    		topic.setPROJDECL_ConvertedHours(topicConVertedHours);
    		topic.setPROJDECL_AuthorConvertedHours(topicAutConHours);
    		topic.setPROJDECL_Budget(topicBudget);
    		topic.setPROJDECL_ReportingAcademicDate(topicReportingAcademicDate);
    		topicDAO.editATopic(topic);
    	}
    }
    
    /**
     * Remove a topic
     * @param int
     * @return int
     */
    @Override
    public int removeATopic(int topicId){
    	return topicDAO.removeATopic(topicId);
    }
    
}
