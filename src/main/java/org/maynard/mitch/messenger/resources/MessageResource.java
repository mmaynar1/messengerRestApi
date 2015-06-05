package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.resources.beans.MessageFilterBean;
import org.maynard.mitch.messenger.service.MessageService;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
    public Message getMessage( @PathParam("messageId") long messageId )
    {
        return messageService.getMessage( messageId );
    }

    @POST
    public Response addMessage( Message message, @Context UriInfo uriInfo )
    {
        Message newMessage = messageService.addMessage( message );

        /*This works but could be better
        return Response.status( Response.Status.CREATED )
                .entity( newMessage )
                .build();*/

       /* Add throws URISyntaxException and you get the same thing + url in response header
            return Response.created( new URI("/webapi/messages/" + newMessage.getId()) )
                .entity( newMessage )
                .build();*/

        /* Best way to do it */
        String newId = String.valueOf( newMessage.getId() );
        URI uri = uriInfo.getAbsolutePathBuilder().path( newId ).build();
        return Response.created( uri )
                .entity( newMessage )
                .build();
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

    @Path( "/{messageId}/comments" )
    public CommentResource getCommentResource()
    {
        return new CommentResource();
    }
}
