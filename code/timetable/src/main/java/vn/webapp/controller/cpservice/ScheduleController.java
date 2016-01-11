package vn.webapp.controller.cpservice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseWeb;
import vn.webapp.service.JuryMemberService;
import vn.webapp.service.JuryRoomService;
import vn.webapp.service.JurySlotService;
import vn.webapp.service.MasterClassService;
import vn.webapp.service.MasterDefenseJuryService;
import vn.webapp.service.MasterThesisService;
import vn.webapp.service.PatentService;
import vn.webapp.service.StaffService;

@Controller("cpmServiceSchedule")
@RequestMapping(value = { "/cpservice" })
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
    private JuryMemberService juryMemberService;
    
    @Autowired
    private JuryRoomService juryRoomService;
    
    @Autowired
    private JurySlotService jurySlotService;
    
    /**
     * 
     * @param sMasterThesisCode
     * @param sDefenseSessionCode
     * @param session
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/savemasterthesis", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String getThreadDeparmentsList(@RequestParam(value = "sMasterThesisCode", defaultValue = "0") String sMasterThesisCode,
											@RequestParam(value = "sDefenseSessionCode", defaultValue = "0") String sDefenseSessionCode, HttpSession session) {
		String sReturn = "";
		// Get department lists
		if(!sMasterThesisCode.equals("") && !sDefenseSessionCode.equals(""))
		{
			String userCode = session.getAttribute("currentUserCode").toString();
			
			int iSaved = masterDefenseJuryService.saveAMasterThesis(sMasterThesisCode, sDefenseSessionCode, userCode);
			if(iSaved > 0){
				sReturn = "<span>Lưu thành công !</span>";
			}else{
				sReturn = "<span>Không thành công</span>";
			}
		}
		return sReturn;
	}

	/**
	 * 
	 * @param sJuryMemberCode
	 * @param sDefenseSessionCode
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savejurymember", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String saveAJuryMember(@RequestParam(value = "sJuryMemberCode", defaultValue = "0") String sJuryMemberCode,
			@RequestParam(value = "sDefenseSessionCode", defaultValue = "0") String sDefenseSessionCode,
			HttpSession session){
		String sReturn = "";
		if(!sJuryMemberCode.equals("") && !sDefenseSessionCode.equals("")){
			String userCode = session.getAttribute("currentUserCode").toString();
			int iSaved = juryMemberService.saveAJuryMember(sJuryMemberCode, sDefenseSessionCode, userCode);
			if(iSaved > 0){
				sReturn = "<span>Lưu thành công !</span>";
			}else{
				sReturn = "<span>Add A Jury Member --> Không thành công" + 
						"cpservice/ScheduleController::saveAJuryMember, juryMemberCode = " + sJuryMemberCode + ", defenseSessionCode = " + sDefenseSessionCode 
						+ "</span>";
			}
		}
		return sReturn;
	}
	
	/**
	 * 
	 * @param sRoomCode
	 * @param sDefenseSessionCode
	 * @param sOrder
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savejuryaroom", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String saveARoom(@RequestParam(value = "sRoomCode", defaultValue = "0") String sRoomCode,
			@RequestParam(value = "sDefenseSessionCode", defaultValue = "0") String sDefenseSessionCode,
			@RequestParam(value = "sOrder", defaultValue = "0") String sOrder,
			HttpSession session){
		String sReturn = "";
		if(!sRoomCode.equals("") && !sDefenseSessionCode.equals("")){
			String userCode = session.getAttribute("currentUserCode").toString();
			int iOrder = Integer.parseInt(sOrder);
			int iSaved = juryRoomService.saveAJuryRoom(iOrder, sRoomCode, sDefenseSessionCode, userCode);
			if(iSaved > 0){
				sReturn = "<span>Lưu thành công !</span>";
			}else{
				sReturn = "<span>Không thành công. </span>";
			}
		}
		return sReturn;
	}
	
	/**
	 * 
	 * @param sRoomCode
	 * @param sDefenseSessionCode
	 * @param sOrder
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savejuryslot", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String saveASlot(@RequestParam(value = "sTime", defaultValue = "0") String sTime,
			@RequestParam(value = "sDefenseSessionCode", defaultValue = "0") String sDefenseSessionCode,
			@RequestParam(value = "sOrder", defaultValue = "0") String sOrder,
			HttpSession session){
		String sReturn = "";
		if(!sTime.equals("") && !sDefenseSessionCode.equals("")){
			String userCode = session.getAttribute("currentUserCode").toString();
			int iOrder = Integer.parseInt(sOrder);
			int iSaved = jurySlotService.saveAJurySlot(iOrder, sTime, sDefenseSessionCode, userCode);
			if(iSaved > 0){
				sReturn = "<span>Lưu thành công !</span>";
			}else{
				sReturn = "<span>Không thành công. </span>";
			}
		}
		return sReturn;
	}

}
