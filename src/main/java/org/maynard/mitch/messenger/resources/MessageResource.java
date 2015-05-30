package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//todo is the slash necessary?
@Path("/messages")
public class MessageResource
{
    MessageService messageService = new MessageService();
    @GET
    @Produces( MediaType.APPLICATION_XML)
    public List<Message> getMessages()
    {
        return messageService.getAllMessages();
    }
}
