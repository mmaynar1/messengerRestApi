package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//todo is the slash necessary?
@Path( "/messages" )
public class MessageResource
{
    MessageService messageService = new MessageService();

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public List<Message> getMessages()
    {
        return messageService.getAllMessages();
    }

    @GET
    @Path( "/{messageId}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Message test( @PathParam( "messageId" ) long messageId )
    {
        return messageService.getMessage( messageId );
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Message addMessage( Message message )
    {
        return messageService.addMessage( message );
    }
}
