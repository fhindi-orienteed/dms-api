package com.dms.base.dto.response.common;

public class TwoFactorAuthResponse {
    private String secretKey;
    private String qrCodeUrl;
    private boolean newlyGenerated;

    public TwoFactorAuthResponse() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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