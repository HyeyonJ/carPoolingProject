package project.carPooling.driver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.mapper.DriverInfoMapper;
import project.carPooling.driver.mapper.RegistrationMapper;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.repository.MybatisDriverInfoRepository;
import project.carPooling.driver.repository.MybatisRegistrationRepository;
import project.carPooling.driver.repository.RegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class DriverAppConfig {
	
	private final DriverInfoMapper driverMapper;
	
	private final RegistrationMapper registrationMapper;

	@Bean
	public DriverInfoRepository driverRepository() {
		return new MybatisDriverInfoRepository(driverMapper);
	}
	@Bean
	public RegistrationRepository registrationRepository() {
		return new MybatisRegistrationRepository(registrationMapper);
	}
}
