package org.sebapresti.mediaservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sebapresti.mediaservices.database.DatabaseClass;
import org.sebapresti.mediaservices.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("sebapresti",new Profile(1L,"sebapresti","Seba","Presti"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());		
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if (profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(long id){
		return profiles.remove(id);
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
}
