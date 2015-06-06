package org.maynard.mitch.messenger.service;

import org.maynard.mitch.messenger.database.DatabaseClass;
import org.maynard.mitch.messenger.model.Comment;
import org.maynard.mitch.messenger.model.ErrorMessage;
import org.maynard.mitch.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService
{
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public CommentService()
    {
        long id = 1L;
        for ( Message message : messages.values() )
        {
            message.getComments().put( id, new Comment( id, "This a comment for id " + id, "I AM THE AUTHOR" ) );
            ++id;
        }
    }

    public List<Comment> getAllComments( long messageId )
    {
        Map<Long, Comment> comments = getComments( messageId );
        return new ArrayList<>( comments.values() );
    }

    public Comment getComment( long messageId, long commentId )
    {
        Response response = get404Response();
        Map<Long, Comment> comments = getComments( messageId );
        Comment comment = comments.get( commentId );
        if ( comment == null )
        {
            //Jersey already knows how to map WebApplicationException, no custom mapper needed
            throw new WebApplicationException( response );
        }
        return comment;
    }

    public Comment addComment( long messageId, Comment comment )
    {
        Map<Long, Comment> comments = getComments( messageId );
        comment.setId( comments.size() + 1 );
        comments.put( comment.getId(), comment );
        return comment;
    }

    public Comment updateComment( long messageId, Comment comment )
    {
        Map<Long, Comment> comments = getComments( messageId );
        if ( comment.getId() <= 0 )
        {
            comment = null;
        }
        else
        {
            comments.put( comment.getId(), comment );
        }
        return comment;
    }

    public Comment removeComment( long messageId, long commentId )
    {
        Map<Long, Comment> comments = getComments( messageId );
        return comments.remove( commentId );
    }

    private Map<Long, Comment> getComments( long messageId )
    {
        Message message = messages.get( messageId );
        if ( message == null )
        {
            Response response = get404Response();
            //Many classes extend WebApplicationException
            throw new NotFoundException( response );
        }
        return message.getComments();
    }

    private Response get404Response()
    {
        ErrorMessage errorMessage = new ErrorMessage( "Not Found", 404, "http://www.facebook.com/" );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( errorMessage )
                .build();
    }
}
