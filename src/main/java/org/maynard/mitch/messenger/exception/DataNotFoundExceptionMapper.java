package org.maynard.mitch.messenger.exception;

import org.maynard.mitch.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider //Registers with JAX-RS
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>
{
    @Override
    public Response toResponse( DataNotFoundException exception )
    {
        ErrorMessage errorMessage = new ErrorMessage( exception.getMessage(), 404, "http://www.facebook.com/" );
        return Response.status( Response.Status.NOT_FOUND )
                .entity( errorMessage )
                .build();
    }
}
