package project.carPooling.driver.repository;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.carPooling.driver.domain.DriverInfo;

@Repository
public class KakaoMemRepository {

	// mapper를 호출하기 위한 클래스 주입.
		@Autowired
		private SqlSessionTemplate sql;
		
		// 정보 저장
		public void kakaoinsert(HashMap<String, Object> userInfo) {
			sql.insert("Member.kakaoInsert",userInfo);
		}

		// 정보 확인
		public DriverInfo findkakao(HashMap<String, Object> userInfo) {
			System.out.println("gender:"+userInfo.get("gender"));
			System.out.println("email:"+userInfo.get("email"));
			return sql.selectOne("Member.findKakao", userInfo);
		}

}
