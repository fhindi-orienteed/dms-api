package com.dms.base.dto.request.mobile;
import com.dms.base.dto.PackageDTO;
import com.dms.base.util.JsonUtility;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MobileUpdateRequestPackage extends PackageDTO {

    @JsonIgnore
    private String dataJson;
    
    public MobileUpdateRequestPackage() {
        super();
    }
    
    @JsonIgnore
    public String getDataJson() {
        return JsonUtility.toJson(this);
    }
    
    public static MobileUpdateRequestPackage fromJson(String json) {
        return JsonUtility.fromJson(json, MobileUpdateRequestPackage.class);
    }
}