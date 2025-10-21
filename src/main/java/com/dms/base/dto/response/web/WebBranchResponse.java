package com.dms.base.dto.response.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebBranchResponse {
    private Long id;
    private String name;
    private String address;
    private Long companyId;
    private String status;
    private LocalDateTime createdAt;
}