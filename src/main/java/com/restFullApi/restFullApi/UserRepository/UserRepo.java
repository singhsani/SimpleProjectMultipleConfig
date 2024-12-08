package com.restFullApi.restFullApi.UserRepository;

import com.restFullApi.restFullApi.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

}
