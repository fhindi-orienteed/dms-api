package com.dms.base.mapper;

import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mapping(source = "company.id", target = "companyId")
    WebBranchResponse toWebBranchResponse(Branch branch);
}
