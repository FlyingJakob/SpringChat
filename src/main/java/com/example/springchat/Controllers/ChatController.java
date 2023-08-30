package com.example.springchat.Controllers;

import com.example.springchat.Models.Message;
import com.example.springchat.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String chat(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String username, @RequestParam String content) {
        Message message = new Message();
        message.setUsername(username);
        message.setContent(content);
        messageRepository.save(message);
        return "redirect:/";
    }
}
