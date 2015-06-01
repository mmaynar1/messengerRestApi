package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//todo is the slash necessary?
@Path( "/messages" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class MessageResource
{
    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages()
    {
        return messageService.getAllMessages();
    }

    @GET
    @Path( "/{messageId}" )
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
    @Path( "/{messageId}" )
    public Message updateMessage( @PathParam( "messageId" ) long messageId, Message message )
    {
        message.setId( messageId );
        return messageService.updateMessage( message );
    }

    @DELETE
    @Path( "/{messageId}" )
    public void deleteMessage( @PathParam( "messageId" ) long messageId )
    {
        messageService.removeMessage( messageId );
    }
}
