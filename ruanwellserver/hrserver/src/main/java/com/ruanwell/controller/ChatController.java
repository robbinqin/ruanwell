package com.ruanwell.controller;

import com.ruanwell.bean.ChatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by robbinqin on 2018/4/14.
 */
@Controller
public class ChatController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    /**
     * @param principal 表示消息发送者
     * @param msg 发送来的消息，一般来说可能是一个json字符串
     *
     *  这里假设消息协议为：
     *            这是消息内容，哈哈哈哈;zhangsan
     */
    @MessageMapping("/ws/chat")//这个是聊天地址
    public void handleChat(Principal principal, String msg) {
        String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String msgContent = msg.substring(0, msg.lastIndexOf(";"));
        messagingTemplate.convertAndSendToUser(destUser,"/queue/chat",new ChatResp(msgContent,principal.getName()));
    }
}
