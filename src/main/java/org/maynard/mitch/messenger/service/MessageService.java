package org.maynard.mitch.messenger.service;

import org.maynard.mitch.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService
{
    public List<Message> getAllMessages()
    {
        Message message1 = new Message( 1, "Hello World1", "Mitch" );
        Message message2 = new Message( 2, "Hello World2", "Mitch" );
        List<Message> messages = new ArrayList<>(  );
        messages.add( message1 );
        messages.add( message2 );
        return messages;
    }
}
