package com.brianmorales.chatapplication.repositories;

import com.brianmorales.chatapplication.entity.ChatEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query("SELECT c FROM ChatEntity c WHERE (c.user1Id = :user1 AND c.user2Id = :user2) OR (c.user1Id = :friend1 AND c.user2Id = :friend2)")
    Optional<ChatEntity> findByUser1AndUser2(String user1, String friend2,String friend1, String user2);

    Optional<ChatEntity> findByUser1IdAndUser2Id(String user1Id, String user2Id);

    @Query("SELECT c FROM ChatEntity c WHERE c.user1Id = :userId OR c.user2Id = :userId")
    List<ChatEntity> findByUserId(String userId);
}
