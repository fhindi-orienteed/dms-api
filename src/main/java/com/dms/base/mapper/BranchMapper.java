package com.dms.base.mapper;

import com.dms.base.dto.response.web.WebBranchResponse;
import com.dms.base.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mapping(source = "companyId", target = "companyId")
    @Mapping(source = "createdDate", target = "createdAt")
    WebBranchResponse toWebBranchResponse(Branch branch);
}
