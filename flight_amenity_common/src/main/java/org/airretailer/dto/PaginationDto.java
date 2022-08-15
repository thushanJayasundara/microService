package org.airretailer.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationDto {

    private int pageSize;
    private int pageNumber;
    private int numberOfEntries;
    private long totalEntries;
    private int totalPages;

}