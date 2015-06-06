package org.maynard.mitch.messenger.exception;

import org.maynard.mitch.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>
{
    /*Blanket catch so we always return JSON. Explicitly mapped exceptions will be handled by
    their mappers, and everything else will be handled by this mapper.*/
    @Override
    public Response toResponse( Throwable throwable )
    {
        ErrorMessage errorMessage = new ErrorMessage( throwable.getMessage(), 500, "http://www.facebook.com/" );
        return Response.status( Response.Status.INTERNAL_SERVER_ERROR )
                .entity( errorMessage )
                .build();
    }
}
