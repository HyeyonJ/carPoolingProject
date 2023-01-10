package project.carPooling.driver.controller.userInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionManager;
import project.carPooling.global.session.SessionVar;
import retrofit2.http.GET;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvEditController {
	
	private final DriverInfoRepository driverInfoRepository;
	private final SessionManager sessionManager;
	
	
	// 회원 정보 수정 페이지
	@GetMapping("/info")
	public String driverUserInfo(Model model, HttpServletRequest req) {
		
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		driverInfo = driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
		
		model.addAttribute("driverInfo", driverInfo);
		
		return "driver/userInfo/dUserInfo";
	}
	
	// 회원 정보 수정
	@PostMapping("/info")
	public String driverUserInfoEdit(@ModelAttribute DriverInfo driverInfo, Model model
					, HttpServletRequest req) {
		
		System.out.println("driverInfo : " + driverInfo);

		driverInfoRepository.updateDriverInfo(driverInfo);
		
//		HttpSession session = req.getSession();
//		session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		
//		return "driver/userInfo/dEditUserInfo";
//		return "redirect:/driver/userInfo/dUserInfo";
		return "redirect:/driver/info";
//		return "redirect:/";
	}
	
	
	@ModelAttribute("dUserTypes")
	public DUserType[] DUserTypes() {
		return DUserType.values();
	}

}
