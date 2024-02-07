package com.example.rent_db.model.dto;

import com.example.rent_db.model.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchApartmentsResponseDto extends AbstractResponse {

    private List<FullApartmentsInfo> fullApartmentsInfoList;
}

