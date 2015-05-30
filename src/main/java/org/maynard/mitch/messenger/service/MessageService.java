package org.maynard.mitch.messenger.service;

import org.maynard.mitch.messenger.database.DatabaseClass;
import org.maynard.mitch.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageService
{
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService()
    {
        messages.put( 1L, new Message( 1, "Message 1", "Mitch" ) );
        messages.put( 2L, new Message( 2, "Message 2", "Mitch" ) );
    }

    public List<Message> getAllMessages()
    {
        return new ArrayList<>( messages.values() );
    }

    public Message getMessage( long id )
    {
        return messages.get( id );
    }

    public Message addMessage( Message message )
    {
        message.setId( messages.size() + 1 );
        messages.put( message.getId(), message );
        return message;
    }

    public Message updateMessage( Message message )
    {
        if ( message.getId() <= 0 )
        {
            message = null;
        }
        else
        {
            messages.put( message.getId(), message );
        }
        return message;
    }

    public Message removeMessage( long id )
    {
        return messages.remove( id );
    }
}
