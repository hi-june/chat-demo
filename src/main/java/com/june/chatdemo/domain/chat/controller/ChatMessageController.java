package com.june.chatdemo.domain.chat.controller;

import com.june.chatdemo.domain.chat.model.ChatMessage;
import com.june.chatdemo.domain.chat.model.RedisPublisher;
import com.june.chatdemo.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatMessageController {
    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
            chatRoomRepository.enterChatRoom(chatMessage.getRoomId());
            chatMessage.setMessage(chatMessage.getSender() + "님 등장!");
        }

        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        // publisher에 topic과 chatMessage를 전달
        redisPublisher.publish(chatRoomRepository.getTopic(chatMessage.getRoomId()), chatMessage);
    }
}
