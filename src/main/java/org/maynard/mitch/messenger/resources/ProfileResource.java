package org.maynard.mitch.messenger.resources;

import org.maynard.mitch.messenger.model.Message;
import org.maynard.mitch.messenger.model.Profile;
import org.maynard.mitch.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/profiles" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ProfileResource
{
    private ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles()
    {
        return profileService.getAllProfiles();
    }

    @GET
    @Path( "/{profileName}" )
    public Profile test( @PathParam( "profileName" ) String profileName )
    {
        return profileService.getProfile( profileName );
    }

    @POST
    public Profile addProfile( Profile profile )
    {
        return profileService.addProfile( profile );
    }

    @PUT
    @Path( "/{profileName}" )
    public Profile updateMessage( @PathParam( "profileName" ) String profileName, Profile profile )
    {
        profile.setProfileName( profileName );
        return profileService.updateProfile( profile );
    }

    @DELETE
    @Path( "/{profileName}" )
    public void deleteMessage( @PathParam( "profileName" ) String profileName )
    {
        profileService.removeProfile( profileName );
    }
}
