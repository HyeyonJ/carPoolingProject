package project.carPooling.driver.controller.registration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.RegistrationListRepository;
import project.carPooling.driver.repository.RequestRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.passenger.domain.PassengerInfo;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DrRegistrationListController {

	private final RequestRepository requestRepository;
	private final RegistrationListRepository registrationListRepository;	
	private final SessionManager sessionManager;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/driver/driverCarpool/reqList")
	public String reqList(){
		return "driver/dRequestList";
	}
	
	@ResponseBody
	@PostMapping("/driver/driverCarpool/reqList")
	public List<Map<String, Object>> reqList1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
//		HttpSession session = req.getSession(false);
//		Integer dIdx = (Integer) session.getAttribute("name");
		Integer dIdx = 1;
		
		// 다른것도 js에서 data 받으면 어떻게 들어오는지 날짜 확인해야함
		List<Map<String, Object>> reqList = requestRepository.selectRequestByDIdx(dIdx);
		System.out.println(reqList);
		
		return reqList;
	}
	
	@ResponseBody
	@PutMapping("/driver/driverCarpool/reqList/accept")
	public boolean reqAccept(@RequestParam Map<Integer, Object> param){
		
		Integer drIdx = Integer.parseInt((String) param.get("drIdx")); 
		Integer pIdx = Integer.parseInt((String) param.get("pIdx")); 
		
		boolean result = requestRepository.updateAccepted(drIdx, pIdx);
		
		return result;
	}
	
	@ResponseBody
	@DeleteMapping("/driver/driverCarpool/reqList/refuse")
	public boolean reqRefuse(@RequestParam Map<Integer, Object> param){
		Integer drIdx = Integer.parseInt((String) param.get("drIdx")); 
		Integer pIdx = Integer.parseInt((String) param.get("pIdx")); 
		
		boolean result = requestRepository.updateRefused(drIdx, pIdx);
		
		return result;
	}
	

	@GetMapping("/driver/driverCarpool/registration/list")
	public String registraionList() {
		return "driver/dRegistrationList";
	}
	
	@ResponseBody
	@GetMapping("/driver/driverCarpool/registration/acceptedList")
	public List<Map<String, Object>> registraionAcceptedList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> acceptedList = registrationListRepository.selectAcceptedRegistrationList(driverInfo.getDIdx());
		System.out.println(acceptedList);
		return acceptedList;
	}
	
	@ResponseBody
	@GetMapping("/driver/driverCarpool/registration/allList")
	public List<Map<String, Object>> registraionAllList(HttpServletRequest req) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		List<Map<String, Object>> allList = registrationListRepository.selectAllRegistrationList(driverInfo.getDIdx());
		System.out.println(allList);
		return allList;
	}
	
	@ResponseBody
	@DeleteMapping("/driver/driverCarpool/registration/delete")
	public boolean deleteRegistration(@RequestParam Integer drIdx) {
		boolean result = registrationListRepository.deleteRegistration(drIdx);
		return result;
	}
	
}
