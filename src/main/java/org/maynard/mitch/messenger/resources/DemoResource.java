package org.maynard.mitch.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/demo" )
@Consumes( MediaType.TEXT_PLAIN )
@Produces( MediaType.TEXT_PLAIN )
public class DemoResource
{
    @GET
    @Path( "annotations" )
    public String getParamUsingAnnotations( @MatrixParam( "param" ) String matrixParam,
                                            @HeaderParam( "authSessionId" ) String headerParam,
                                            @CookieParam( "JSESSIONID" ) String cookie )
    {
        //Matrix parameters are separated on the url by semicolons instead of the leading question mark and ampersands.
        //Header parameters are custom values sent in on the request via the header. Can be used for sending authentication tokens.
        //Cookie parameters are used for reading cookies that are set in the request
        //There is also FormParam, which is used when submitting an HTML form. This is rarely used for REST APIs.
        return "Matrix Param: " + matrixParam + "\nHeader Param: " + headerParam + "\nCookie Param: " + cookie;
    }
}
