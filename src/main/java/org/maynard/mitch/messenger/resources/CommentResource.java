package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Comment;
import org.maynard.mitch.messenger.service.CommentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/") //NOTE: This path annotation is optional for sub resources
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource
{
    private CommentService commentService = new CommentService();

    /*@GET
    public String test()
    {
        return "new sub resource";
    }*/

    @GET
    public List<Comment> getAllComments( @PathParam( "messageId" ) long messageId )
    {
        return commentService.getAllComments( messageId );
    }

    @GET
    @Path( "/{commentId}" )
    public Comment getComment( @PathParam( "commentId" ) long commentId,
                               @PathParam( "messageId" ) long messageId )
    {
        return commentService.getComment( messageId, commentId );
    }

    @POST
    public Comment addComment( @PathParam( "messageId" ) long messageId, Comment comment )
    {
        return commentService.addComment( messageId, comment );
    }

    @PUT
    @Path( "/{commentId}" )
    public Comment updateComment( @PathParam( "commentId" ) long commentId,
                                  @PathParam( "messageId" ) long messageId,
                                  Comment comment )
    {
        comment.setId( commentId );
        return commentService.updateComment( messageId, comment );
    }

    @DELETE
    @Path( "/{commentId}" )
    public Comment removeComment( @PathParam( "commentId" ) long commentId,
                                  @PathParam( "messageId" ) long messageId )
    {
        return commentService.removeComment( messageId, commentId );
    }

}
