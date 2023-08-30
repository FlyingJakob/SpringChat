package com.example.springchat.Controllers;

import com.example.springchat.Models.Message;
import com.example.springchat.Repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "login";  // If user is not logged in, redirect to login page.
        }
        return "redirect:/chat";  // If user is logged in, redirect to chat page.
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        session.setAttribute("username", username);  // Store username in session
        return "redirect:/chat";
    }

    @GetMapping("/chat")
    public String chat(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/";  // Ensure user is logged in
        }

        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @GetMapping("/getMessages")
    @ResponseBody
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }


    @PostMapping("/send")
    public String sendMessage(@RequestParam String content, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/";  // Ensure user is logged in
        }

        Message message = new Message();
        message.setUsername(username);
        message.setContent(content);
        messageRepository.save(message);
        return "redirect:/chat";
    }
}
