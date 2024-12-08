package com.restFullApi.restFullApi.UserRepository;

import com.restFullApi.restFullApi.Model.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<PostDetail,Integer> {
}
