package com.sau.ornekwebapp.Repositories;

import com.sau.ornekwebapp.Models.Comment;
import com.sau.ornekwebapp.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);

    @Query(value = "select * from _like where post_id in :postIds limit 5", nativeQuery = true)

    List<Like> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
