package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Message;
import com.example.capstone3.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    // 51
    public List<Message> getMessages(){
        return messageRepository.findAll();
    }
    // 52
    public void addMessage(Message message){
        messageRepository.save(message);
    }
    // 53
    public void updateMessage(Integer id,Message message){
        Message message1 = messageRepository.findMessageById(id);
        if(message1==null){
            throw new ApiException("message id not found");
        }
        message1.setContent(message.getContent());
        message1.setStatus(message.getStatus());
        messageRepository.save(message1);
    }
    // 54
    public void deleteMessage(Integer id){
        Message message = messageRepository.findMessageById(id);
        if(message==null){
            throw new ApiException("message id not found");
        }
        messageRepository.delete(message);
    }
    // 55
    public List<Message> getByUserAndStatus(Integer id,String status){
        List<Message> messages =messageRepository.findMessageByIdAndStatus(id, status);
        return messages;

    }
    // 56
    public Message getMessageById(Integer id){
        Message message = messageRepository.findMessageById(id);
        message.setStatus("seen");
        messageRepository.save(message);
        return message;
    }
}
