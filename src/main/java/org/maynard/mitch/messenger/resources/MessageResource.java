package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.resources.beans.MessageFilterBean;
import org.maynard.mitch.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//todo is the slash necessary?
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource
{
    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages( @BeanParam MessageFilterBean messageFilterBean )
    {
        List<Message> messages;
        if ( messageFilterBean.getYear() > 0 )
        {
            messages = messageService.getAllMessagesForYear( messageFilterBean.getYear() );
        }
        else if ( messageFilterBean.getStart() > -1 && messageFilterBean.getSize() > 0 )
        {
            //NOTE: if condition above was changed to size >= 0 getAllMessages() would never get called.
            //This is because by default they will be initialized to zero.
            messages = messageService.getAllMessagesPaginated( messageFilterBean.getStart(), messageFilterBean.getSize() );
        }
        else
        {
            messages = messageService.getAllMessages();
        }
        return messages;
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage( @PathParam( "messageId" ) long messageId )
    {
        return messageService.getMessage( messageId );
    }

    @POST
    public Message addMessage( Message message )
    {
        return messageService.addMessage( message );
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage( @PathParam("messageId") long messageId, Message message )
    {
        message.setId( messageId );
        return messageService.updateMessage( message );
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage( @PathParam("messageId") long messageId )
    {
        messageService.removeMessage( messageId );
    }
}
