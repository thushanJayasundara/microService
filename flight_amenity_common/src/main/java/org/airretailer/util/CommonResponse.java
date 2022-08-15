package org.airretailer.util;

import lombok.*;
import org.airretailer.dto.PaginationDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    private List<Object> payload ;
    private Map<String,Object> request;
    private List<String> messages = new ArrayList<>();
    private PaginationDto pagination;
    private boolean status = false;

}