package com.example.springchat.Repository;

import com.example.springchat.Models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
