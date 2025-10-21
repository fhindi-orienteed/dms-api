package com.dms.base.service;

import com.dms.base.model.Profile;
import com.dms.base.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    
   /**
     * Finds a user's profile by their user ID.
     * Since the Profile ID is the same as the User ID, we can use findById.
     * @param userId The ID of the user.
     * @return The Profile object, or null if not found.
     */
    public Profile findByUserId(Long userId) {
        return profileRepository.findById(userId).orElse(null);
    }
}