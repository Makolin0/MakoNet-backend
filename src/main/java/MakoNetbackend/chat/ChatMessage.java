package MakoNetbackend.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String nick;
    private String message;
    private MessageType type;
}
