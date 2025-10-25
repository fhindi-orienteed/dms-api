package com.dms.base.service;

import com.dms.base.model.Company;
import com.dms.base.model.CompanyUser;
import com.dms.base.model.Profile;
import com.dms.base.model.User;
import com.dms.base.dto.request.web.UpdateProfileRequest;
import com.dms.base.dto.response.web.WebUserProfileResponse;
import com.dms.base.mapper.CompanyMapper;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.repository.ProfileRepository;
import com.dms.base.util.Constant.RoleType;
import com.dms.base.model.Driver;
import com.dms.base.exception.ObjectNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class); 

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final DriverService driverService;
    private final CompanyUserService companyUserService;
    private final CompanyService companyService;
    private final DriverMapper driverMapper;
    private final CompanyMapper companyMapper;

    public ProfileService(ProfileRepository profileRepository, UserService userService,
                          DriverService driverService, CompanyUserService companyUserService,
                          CompanyService companyService, DriverMapper driverMapper, CompanyMapper companyMapper) {
        this.profileRepository = profileRepository;
        this.userService = userService;
        this.driverService = driverService;
        this.companyUserService = companyUserService;
        this.companyService = companyService;
        this.driverMapper = driverMapper;
        this.companyMapper = companyMapper;
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

    @Transactional
    public WebUserProfileResponse updateProfile(UpdateProfileRequest request) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No authenticated user found.");
        }

        Profile profile = profileRepository.findById(currentUser.getId()).orElseGet(() -> {
            Profile newProfile = new Profile();
            newProfile.setUserId(currentUser);
            newProfile.setId(currentUser.getId());
            return newProfile;
        });

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setMobile(request.getMobile());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());
        
        try {
            profileRepository.save(profile);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Optimistic lock conflict for profile ID: {}. Error: {}", profile.getId(), e.getMessage());
            
            throw new IllegalStateException("فشل حفظ التغييرات. قام مستخدم آخر بتحديث هذه البيانات. يرجى تحديث الصفحة والمحاولة مرة أخرى.");
        }

        return buildUserProfileResponse();
    }

    public WebUserProfileResponse buildUserProfileResponse() {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new ObjectNotFoundException("No authenticated user found.");
        }

        Profile profile = findByUserId(user.getId());

        WebUserProfileResponse response = new WebUserProfileResponse();
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        if (profile != null) {
            response.setFirstName(profile.getFirstName());
            response.setLastName(profile.getLastName());
            response.setMobile(profile.getMobile());
            response.setAddress(profile.getAddress());
        }

        if (RoleType.ROLE_DRIVER.name().equals(user.getRole())) {
            Driver driver = driverService.findByUserId(user.getId());
            response.setDriverDetails(driverMapper.mapToWebResponse(driver));
        } else if (RoleType.ROLE_COMPANY_USER.name().equals(user.getRole()) || RoleType.ROLE_COMPANY_ADMIN.name().equals(user.getRole())) {
            CompanyUser companyUser = companyUserService.findByUserId(user.getId());
            if (companyUser != null) {
                Company company = companyService.findByCompanyId(companyUser.getCompanyId());
                response.setCompanyDetails(companyMapper.mapToWebResponse(company));
            }
        }

        return response;
    }
}