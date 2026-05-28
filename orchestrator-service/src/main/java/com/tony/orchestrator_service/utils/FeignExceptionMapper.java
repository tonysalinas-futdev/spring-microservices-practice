package com.tony.orchestrator_service.utils;

import com.tony.orchestrator_service.dtos.ErrorResponseDTO;
import feign.FeignException;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


public class FeignExceptionMapper {

    public static ErrorResponseDTO toErrorResponseDTO(FeignException ex){
        String body=ex.contentUTF8();
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(body, ErrorResponseDTO.class);
    }
}
