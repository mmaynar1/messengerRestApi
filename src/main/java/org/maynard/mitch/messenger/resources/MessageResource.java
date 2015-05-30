package org.maynard.mitch.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//todo is the slash necessary?
@Path("/messages")
public class MessageResource
{
    @GET
    @Produces( MediaType.TEXT_PLAIN)
    public String getMessages()
    {
        return "Hello World";
    }
}
