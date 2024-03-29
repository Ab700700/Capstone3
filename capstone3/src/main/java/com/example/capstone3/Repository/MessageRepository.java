package com.example.capstone3.Repository;

import com.example.capstone3.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    Message findMessageById(Integer id);
    List<Message> findMessageByIdAndStatus(Integer id,String status);
}
