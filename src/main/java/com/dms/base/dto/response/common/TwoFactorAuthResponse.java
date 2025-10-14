package com.dms.base.dto.response.common;

public class TwoFactorAuthResponse {
    private String qrCodeUrl;
    private boolean newlyGenerated;

    public TwoFactorAuthResponse() {
    }


    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public boolean isNewlyGenerated() {
        return newlyGenerated;
    }

    public void setNewlyGenerated(boolean newlyGenerated) {
        this.newlyGenerated = newlyGenerated;
    }
}