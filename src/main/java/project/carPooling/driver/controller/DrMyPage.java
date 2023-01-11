package project.carPooling.driver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.global.session.SessionManager;

@RequiredArgsConstructor
@RequestMapping("/driver")
@Controller
public class DrMyPage {
	
	private final SessionManager sessionManager;
	
	@GetMapping("/driverCarpool/myPage")
	public String myPage(HttpServletRequest req, Model model) {
		DriverInfo driverInfo = sessionManager.getDrSession(req);
		model.addAttribute("driverInfo", driverInfo);
		return "driver/dMyPage";
	}
}
