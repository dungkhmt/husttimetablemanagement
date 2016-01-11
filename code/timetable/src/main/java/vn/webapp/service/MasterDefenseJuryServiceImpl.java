/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.dao.ListMasterThesisDAO;
import vn.webapp.dao.MasterDefenseJuryDAO;
import vn.webapp.model.ListMasterThesis;
import vn.webapp.model.MasterDefenseJuryThesis;
import vn.webapp.model.RawMasterDefenseJuryThesis;
import vn.webapp.model.ShowedViewMasterDefenseThesis;

@Service("masterDefenseJuryService")
public class MasterDefenseJuryServiceImpl implements MasterDefenseJuryService {

    @Autowired
    private MasterDefenseJuryDAO masterDefenseJuryDAO;

    @Autowired
    private ListMasterThesisDAO listMasterThesisDAO;
     
    /**
     * 
     */
    @Override
    public List<ShowedViewMasterDefenseThesis> getListMasterDefenseJuryThesis(){
    	try {
    		List<MasterDefenseJuryThesis> masterDefenseJuryList = masterDefenseJuryDAO.getListMasterDefenseJuryThesis();
    		List<ListMasterThesis> listMasterThesis =  listMasterThesisDAO.getListMasterThesis();
    		
    		return getListShowedViewMasterDefenseThesis(masterDefenseJuryList, listMasterThesis);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     */
    @Override
    public List<ShowedViewMasterDefenseThesis> getListMasterDefenseJuryThesisByOwner(String ownerCode){
    	try {
    		List<MasterDefenseJuryThesis> masterDefenseJuryList = masterDefenseJuryDAO.getListMasterDefenseJuryThesisByOwner(ownerCode);
    		List<ListMasterThesis> listMasterThesis =  listMasterThesisDAO.getListMasterThesis();
    		
    		return getListShowedViewMasterDefenseThesis(masterDefenseJuryList, listMasterThesis);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     * @param masterDefenseJuryList
     * @param listMasterThesis
     * @return
     */
    public List<ShowedViewMasterDefenseThesis> getListShowedViewMasterDefenseThesis(List<MasterDefenseJuryThesis> masterDefenseJuryList, List<ListMasterThesis> listMasterThesis)
    {
    	List<ShowedViewMasterDefenseThesis> combinationListMasterDefense = new ArrayList<ShowedViewMasterDefenseThesis>();
		int iInterator = 0;
		if(masterDefenseJuryList != null && listMasterThesis != null)
		{
			iInterator = 0;
			for (MasterDefenseJuryThesis masterDefense : masterDefenseJuryList) {
				ShowedViewMasterDefenseThesis tempListMasterDefense = new ShowedViewMasterDefenseThesis();
				iInterator++;
				for (ListMasterThesis masterTheis : listMasterThesis) {
					if(masterDefense.getMASDEFJury_ThesisCode().equals(masterTheis.getThesis_Code()))
					{
						tempListMasterDefense.setiKey(iInterator); // Iterator
						
						tempListMasterDefense.setMasterDefenseJuryCode(masterDefense.getMASDEFJury_Code());
						
						tempListMasterDefense.setStudentName(masterTheis.getStudent().getStudent_Name()); // Student name
						tempListMasterDefense.setThesisName(masterTheis.getThesis_Name()); // Thesis name
						tempListMasterDefense.setMentorName(masterTheis.getSupervisor().getStaff_Name()); // Mentor name
						
						tempListMasterDefense.setExaminer1Code(masterDefense.getExaminer1Info().getStaff_Code()); // Examiner 1
						tempListMasterDefense.setExaminer1Name(masterDefense.getExaminer1Info().getStaff_Name());
						
						tempListMasterDefense.setExaminer2Code(masterDefense.getExaminer2Info().getStaff_Code()); // Examiner 2
						tempListMasterDefense.setExaminer2Name(masterDefense.getExaminer2Info().getStaff_Name());
						
						tempListMasterDefense.setPresidentCode(masterDefense.getPresidentInfo().getStaff_Code()); // President
						tempListMasterDefense.setPresidentName(masterDefense.getPresidentInfo().getStaff_Name());
						
						tempListMasterDefense.setSecretaryCode(masterDefense.getSecretaryInfo().getStaff_Code()); // Secretary 
						tempListMasterDefense.setSecretaryName(masterDefense.getSecretaryInfo().getStaff_Name());
						
						tempListMasterDefense.setExternalMemberCode(masterDefense.getExternalMemberInfo().getStaff_Code()); // External Council member
						tempListMasterDefense.setExternalMemberName(masterDefense.getExternalMemberInfo().getStaff_Name());
						
						tempListMasterDefense.setTheTime(masterDefense.getMASDEFJury_SlotCode()); // Time
						
						tempListMasterDefense.setRoomPlaceCode(masterDefense.getRoomInfo().getR_Code()); // Room
						
						tempListMasterDefense.setCoucilNo("1"); // Coucil No
						
						tempListMasterDefense.setClassCode(masterTheis.getStudent().getStudent_ClassesCode()); // Class 
					}
				}
				combinationListMasterDefense.add(tempListMasterDefense);
			}
		}
		return combinationListMasterDefense;
    }
    /**
     * 
     */
    @Override
    public boolean updateAMasterDefenseJuryThesis(String[] masterDefenseThesis, String[] defenseder01, String[] defenseder02, String[] president, String[] secretary, 
											String[] commissioner, String[] slot, String[] room, String[] no, String userCode, String sessionCode){
    	try{
	    	int totalRecordBeingSaved = masterDefenseThesis.length;
	    	for(int iIterator = 0; iIterator < totalRecordBeingSaved; iIterator++)
	    	{
	    		MasterDefenseJuryThesis aRecordDefenseJuryBeingSaved = new MasterDefenseJuryThesis();
	    		MasterDefenseJuryThesis oCheckExistingRecord = masterDefenseJuryDAO.getMasterDefenseJuryThesisByIdAndOwner(masterDefenseThesis[iIterator], userCode);
	    		if(oCheckExistingRecord != null)
	    		{
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ID(oCheckExistingRecord.getMASDEFJury_ID()); // Set Jury's ID
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_Code(oCheckExistingRecord.getMASDEFJury_Code()); // Set Jury's code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ThesisCode(oCheckExistingRecord.getMASDEFJury_ThesisCode()); // Set Jury's DefenseThesis code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_StaffCode(userCode);
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_DefenseSessionCode(sessionCode);
	    			
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner1Code(defenseder01[iIterator]); // Update defender 1
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner2Code(defenseder02[iIterator]); // Update defender 2
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_PresidentCode(president[iIterator]); // Update president
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SecretaryCode(secretary[iIterator]); // Update secreatry
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_MemberCode(commissioner[iIterator]); // Update member
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SlotCode(slot[iIterator]); // Update slot
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_RoomCode(room[iIterator]); // Update room
		    		
		    		masterDefenseJuryDAO.updateAMasterDefenseJuryThesis(aRecordDefenseJuryBeingSaved);
	    		}
	    	}
	    	return true;
    	}catch(Exception exception)
    	{
    		System.out.println("Exception: " + exception.getMessage());
    		return false;
    	}
    }
    
    /**
     * 
     */
    @Override
    public int saveAMasterThesis(String sMasterThesisCode, String sDefenseSessionCode, String userCode){
    	try{
	    		MasterDefenseJuryThesis aRecordDefenseJuryBeingSaved = new MasterDefenseJuryThesis();
	    		MasterDefenseJuryThesis oCheckExistingRecord = masterDefenseJuryDAO.getMasterDefenseJuryThesisByThesisCodeAndOwner(sMasterThesisCode, userCode);
	    		if(oCheckExistingRecord == null)
	    		{
	    			String sDefenseJuryCode = userCode + sMasterThesisCode + sDefenseSessionCode;
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_Code(sDefenseJuryCode); // Set Jury's code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_ThesisCode(sMasterThesisCode); // Set Jury's DefenseThesis code
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_StaffCode(userCode);
	    			aRecordDefenseJuryBeingSaved.setMASDEFJury_DefenseSessionCode(sDefenseSessionCode);
	    			
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner1Code(userCode); // Update defender 1
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_Examiner2Code(userCode); // Update defender 2
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_PresidentCode(userCode); // Update president
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SecretaryCode(userCode); // Update secreatry
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_MemberCode(userCode); // Update member
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_SlotCode("N/A"); // Update slot
		    		aRecordDefenseJuryBeingSaved.setMASDEFJury_RoomCode("D3-201"); // Update room
		    		
		    		return masterDefenseJuryDAO.saveAMasterThesis(aRecordDefenseJuryBeingSaved);
	    		}
	    		return 0;
    	}catch(Exception exception)
    	{
    		System.out.println("Exception: " + exception.getMessage());
    		return 0;
    	}
    }
    
    /**
     * 
     */
    @Override
    public int removeAMasterThesis(String userCode, String sMasterThesisCode){
    	try{
    		RawMasterDefenseJuryThesis oCheckExistingRecord = masterDefenseJuryDAO.getRawMasterDefenseJuryThesisByThesisCodeAndOwner(sMasterThesisCode, userCode);
    		if(oCheckExistingRecord != null)
    		{
	    		return masterDefenseJuryDAO.removeAMasterThesis(oCheckExistingRecord);
    		}
    		return 0;
	}catch(Exception exception)
	{
		System.out.println("Exception: " + exception.getMessage());
		return 0;
	}
    }
}
