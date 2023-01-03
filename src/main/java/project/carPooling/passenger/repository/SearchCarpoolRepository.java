package project.carPooling.passenger.repository;

import java.util.List;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

public interface SearchCarpoolRepository {
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool);
	
	public DRegistration selectCarpoolByDrIdx(Integer drIdx);
	
	public DRegistration insert(Integer pIdx, Integer drIdx);
	
	public boolean selectPassenger(Integer pIdx, Integer drIdx);

}
