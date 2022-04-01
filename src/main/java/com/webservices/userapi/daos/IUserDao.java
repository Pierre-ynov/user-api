package com.webservices.userapi.daos;

import com.webservices.userapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao extends MongoRepository<User, Integer> {
	@Query("{accountStatus : ACTIVE}")
	List<User> findAllActiveUsers ();
	
	@Query("{id : ?0}")
	User findUserByID (Integer id);
}
