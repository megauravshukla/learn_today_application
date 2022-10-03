package com.dxc.luxoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.UserDetail;

@Repository("userDeatilsRepo")
public interface UserDetailsRepository extends JpaRepository<UserDetail, Integer>{
	
	public List<UserDetail> findByUserName(String userName);
	
	@Modifying
	@Query("update UserDetail set password =?1 where userName=?2")
	public void updatePassword(String password, String userId);

}
