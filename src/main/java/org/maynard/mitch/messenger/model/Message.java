package org.maynard.mitch.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Message
{
    private long id;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Message()
    {
    }

    public Message( long id, String message, String author )
    {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.author = author;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks( List<Link> links )
    {
        this.links = links;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated( Date created )
    {
        this.created = created;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    @XmlTransient
    public Map<Long, Comment> getComments()
    {
        return comments;
    }

    public void setComments( Map<Long, Comment> comments )
    {
        this.comments = comments;
    }

    public void addLink(String url, String rel)
    {
        Link link = new Link( url, rel );
        getLinks().add( link );
    }
}
