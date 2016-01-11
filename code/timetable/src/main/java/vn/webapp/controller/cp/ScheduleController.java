/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.controller.cp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;








/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.model.DefenseSession;
import vn.webapp.model.JuryMember;
import vn.webapp.model.JuryRoom;
import vn.webapp.model.JurySlot;
import vn.webapp.model.ListMasterThesis;
import vn.webapp.model.Rooms;
import vn.webapp.model.ShowedViewMasterDefenseThesis;
import vn.webapp.model.Staff;
import vn.webapp.service.JuryMemberService;
import vn.webapp.service.JuryRoomService;
import vn.webapp.service.JurySlotService;
import vn.webapp.service.MasterClassService;
import vn.webapp.service.MasterDefenseJuryService;
import vn.webapp.service.MasterThesisService;
import vn.webapp.service.PatentService;
import vn.webapp.service.RoomsService;
import vn.webapp.service.StaffService;

@Controller("cpSchedule")
@RequestMapping(value = {"/cp"})
public class ScheduleController extends BaseWeb {

    @Autowired
    private PatentService patentService;
    
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private MasterClassService masterClassService;
    
    @Autowired
    private MasterThesisService masterThesisService;
    
    @Autowired
    private MasterDefenseJuryService masterDefenseJuryService;
    
    @Autowired
    private RoomsService roomsService;
 
    @Autowired 
    private JuryMemberService juryMemberService;
 
    @Autowired
    private JuryRoomService juryRoomService;
    
    @Autowired
    private JurySlotService jurySlotService;

    static final String scheduling = "active";
    static final boolean disableHeader = true;
    
    /**
    * Scheduling
    * @param model
    * @param session
    * @return
    */
    
    @RequestMapping(value = "/jurymembers", method = RequestMethod.GET)
    public String juryMember(ModelMap model, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
    	List<Staff> listMembers = staffService.listStaffs();
    	List<JuryMember> listJuryMembers = juryMemberService.listJuryMembers(userCode);
    	
    	model.put("listDefenseSessions", listDefenseSessions);
    	model.put("listMembers", listMembers);
    	model.put("listJuryMembers", listJuryMembers);
    	return "cp.juryMembers";
    }
    @RequestMapping(value = "/juryrooms", method = RequestMethod.GET)
    public String juryRooms(ModelMap model, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
    	List<JuryRoom> listJuryRooms = juryRoomService.listJuryRooms(userCode);
    	List<Rooms> listRooms = roomsService.listRooms();
    	
    	model.put("listDefenseSessions", listDefenseSessions);
    	model.put("listJuryRooms", listJuryRooms);
    	model.put("listRooms", listRooms);
    	return "cp.juryRooms";
    }
    @RequestMapping(value = "/juryslots", method = RequestMethod.GET)
    public String jurySlots(ModelMap model, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
    	List<JurySlot> listJurySlots = jurySlotService.listJurySlots(userCode);
    	
    	model.put("listDefenseSessions", listDefenseSessions);
    	model.put("listJurySlots", listJurySlots);
    	return "cp.jurySlots";
    }
 
   @RequestMapping(value = "/scheduling", method = RequestMethod.GET)
   public String sheduling(ModelMap model, HttpSession session) { 
	   String userCode = session.getAttribute("currentUserCode").toString();
	   List<DefenseSession> listDefenseSession  = masterThesisService.listDefenseSession();
	   List<ListMasterThesis> listMasterThesis= masterThesisService.getListMasterThesis();
	   List<ShowedViewMasterDefenseThesis> listMasterDefenseJuryThesis = masterDefenseJuryService.getListMasterDefenseJuryThesisByOwner(userCode);
	   
	   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
	   model.put("listMasterThesis", listMasterThesis);
	   model.put("listDefenseSession", listDefenseSession);
	   model.put("scheduling", ScheduleController.scheduling);
	   model.put("disableHeader", ScheduleController.disableHeader);
	   return "cp.scheduling";
   }
   
   /**
    * Setting juries
    * @param model
    * @param session
    * @return
    */
   @RequestMapping(value = "/settingjuries", method = RequestMethod.GET)
   public String settingJuries(ModelMap model, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   List<Staff> listStaffs = staffService.listStaffs();
	   //List<MasterClass> listClasses = masterClassService.loadMasterClassList();
	   List<Rooms> listRooms = roomsService.listRooms();
	   List<ListMasterThesis> listMasterThesis  = masterThesisService.getListMasterThesis();
	   List<ShowedViewMasterDefenseThesis> listMasterDefenseJuryThesis = masterDefenseJuryService.getListMasterDefenseJuryThesis();
	   
	   model.put("listStaffs", listStaffs);
	   //model.put("listClasses", listClasses);
	   model.put("listRooms", listRooms);
	   model.put("listMasterThesis", listMasterThesis);
	   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
	   
	   model.put("scheduling", ScheduleController.scheduling);
	   model.put("disableHeader", ScheduleController.disableHeader);
	   return "cp.settingjuries";
   }
   
   /**
    * Setting juries
    * @param model
    * @param session
    * @return
    */
   @RequestMapping(value = "/save-defenses", method=RequestMethod.POST)
   public String saveDefences(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
	   
	   String userCode = session.getAttribute("currentUserCode").toString();
	   String userRole = session.getAttribute("currentUserRole").toString();
	   String[] masterDefenseThesis = request.getParameterValues("masterDefenseThesis");
	   String[] defenseder01 = request.getParameterValues("defenseder01");
	   String[] defenseder02 = request.getParameterValues("defenseder02");
	   String[] president = request.getParameterValues("president");
	   String[] secretary = request.getParameterValues("secretary");
	   String[] commissioner = request.getParameterValues("commissioner");
	   String[] slot = request.getParameterValues("slot");
	   String[] room = request.getParameterValues("room");
	   String[] no = request.getParameterValues("no");
	   String sessionCode = "2014-2015";
	   
	   //Update info
	   boolean isUpdated = masterDefenseJuryService.updateAMasterDefenseJuryThesis(masterDefenseThesis, defenseder01, defenseder02, president, secretary, commissioner, slot, room, no, userCode, sessionCode);
	   String status = "";
	   if(isUpdated)
	   {
		   status = "Chỉnh sửa thành công !";
	   }
	   List<Staff> listStaffs = staffService.listStaffs();
	   List<Rooms> listRooms = roomsService.listRooms();
	   List<ListMasterThesis> listMasterThesis  = masterThesisService.getListMasterThesis();
	   List<ShowedViewMasterDefenseThesis> listMasterDefenseJuryThesis = masterDefenseJuryService.getListMasterDefenseJuryThesis();
	   
	   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
	   model.put("status", status);
	   model.put("listStaffs", listStaffs);
	   model.put("listRooms", listRooms);
	   model.put("listMasterThesis", listMasterThesis);
	   model.put("scheduling", ScheduleController.scheduling);
	   model.put("disableHeader", ScheduleController.disableHeader);
	   
	   return "cp.settingjuries";
   }

   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-masterthesis/{code}", method = RequestMethod.GET)
   public String removeAMasterThesis(ModelMap model, @PathVariable("code") String masterThesisCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   model.put("scheduling", ScheduleController.scheduling);
	   model.put("disableHeader", ScheduleController.disableHeader);
 	   int isRemoved = masterDefenseJuryService.removeAMasterThesis(userCode, masterThesisCode);
 	   if(isRemoved > 0 ){
 		   List<DefenseSession> listDefenseSession  = masterThesisService.listDefenseSession();
 		   List<ListMasterThesis> listMasterThesis= masterThesisService.getListMasterThesis();
 		   List<ShowedViewMasterDefenseThesis> listMasterDefenseJuryThesis = masterDefenseJuryService.getListMasterDefenseJuryThesisByOwner(userCode);
 		   
 		   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
 		   model.put("listMasterThesis", listMasterThesis);
 		   model.put("listDefenseSession", listDefenseSession);
 		   
 		   return "cp.scheduling";
 	   }
 	  return "cp.scheduling";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-member/{code}", method = RequestMethod.GET)
   public String removeAJuryMember(ModelMap model, @PathVariable("code") String sJuryMemberCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   int isRemoved = juryMemberService.removeAJuryMember(userCode, sJuryMemberCode);
 	   if(isRemoved > 0 ){
 		   List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
 		  	List<Staff> listMembers = staffService.listStaffs();
 		  	List<JuryMember> listJuryMembers = juryMemberService.listJuryMembers(userCode);
 		  	
 		  	model.put("listDefenseSessions", listDefenseSessions);
 		  	model.put("listMembers", listMembers);
 		  	model.put("listJuryMembers", listJuryMembers);
 		   
 		  return "cp.juryMembers";
 	   }
 	  return "cp.juryMembers";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-room/{code}", method = RequestMethod.GET)
   public String removeAJuryRoom(ModelMap model, @PathVariable("code") String sJuryRoomCode, HttpSession session) {
 	    String userCode = session.getAttribute("currentUserCode").toString();
 	    List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
	   	List<Rooms> listRooms = roomsService.listRooms();
	   	
	   	model.put("listDefenseSessions", listDefenseSessions);
	   	model.put("listRooms", listRooms);
 	   int isRemoved = juryRoomService.removeAJuryRoom(userCode, sJuryRoomCode);
 	   if(isRemoved > 0 ){
 			List<JuryRoom> listJuryRooms = juryRoomService.listJuryRooms(userCode);
 			model.put("listJuryRooms", listJuryRooms);
 		    return "cp.juryRooms";
 	   }
 	  return "cp.juryRooms";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-slot/{code}", method = RequestMethod.GET)
   public String removeAJurySlot(ModelMap model, @PathVariable("code") String sJurySlotCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   List<DefenseSession> listDefenseSessions = masterThesisService.listDefenseSession();
   	
	   model.put("listDefenseSessions", listDefenseSessions);
 	   int isRemoved = jurySlotService.removeAJurySlot(userCode, sJurySlotCode);
 	   if(isRemoved > 0 ){
 		  List<JurySlot> listJurySlots = jurySlotService.listJurySlots(userCode);
 		  model.put("listJurySlots", listJurySlots);
 		  return "cp.jurySlots";
 	   }
 	  return "cp.jurySlots";
   }
   
}
