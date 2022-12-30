package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

public interface RequestRepository {
	public List<Map<String, Object>> selectRequestByDIdx(Integer dIdx);
	
	public boolean updateAccepted(Integer drIdx, Integer pIdx);

	public boolean updateRefused(Integer drIdx, Integer pIdx);
}
