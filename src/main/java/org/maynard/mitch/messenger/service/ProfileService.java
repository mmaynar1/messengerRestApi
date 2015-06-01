package org.maynard.mitch.messenger.service;

import org.maynard.mitch.messenger.database.DatabaseClass;
import org.maynard.mitch.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService
{
    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService()
    {
        profiles.put( "mitch", new Profile( 1L, "mitch", "Mitch", "Maynard" ) );
        profiles.put( "jimmy", new Profile( 2L, "jimmy", "Jimmy", "Fallon" ) );
    }

    public List<Profile> getAllProfiles()
    {
        return new ArrayList<>( profiles.values() );
    }

    public Profile getProfile( String profileName )
    {
        return profiles.get( profileName );
    }

    public Profile addProfile( Profile profile )
    {
        profile.setId( profiles.size() + 1 );
        profiles.put( profile.getProfileName(), profile );
        return profile;
    }

    public Profile updateProfile( Profile profile )
    {
        if ( profile.getProfileName().isEmpty() )
        {
            profile = null;
        }
        else
        {
            profiles.put( profile.getProfileName(), profile );
        }

        return profile;
    }

    public Profile removeProfile( String profileName )
    {
        return profiles.remove( profileName );
    }

}
