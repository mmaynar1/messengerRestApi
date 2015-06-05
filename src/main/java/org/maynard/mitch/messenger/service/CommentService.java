package org.maynard.mitch.messenger.service;

import org.maynard.mitch.messenger.database.DatabaseClass;
import org.maynard.mitch.messenger.model.Comment;
import org.maynard.mitch.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService
{
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public CommentService()
    {
        long id = 1L;
        for(Message message : messages.values())
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
        return getComments( messageId ).get( commentId );
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
        return messages.get( messageId ).getComments();
    }
}
