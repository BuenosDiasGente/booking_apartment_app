package com.example.rent_db.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="integration_info")
@Data
@NoArgsConstructor
public class IntegrationInfo {
    @Id
    @Column(name="unique_id_service")
    private String uniqueId ;

    @Column(name="path_description")
    private String url;

    @Column(name="api_key")
    private String apiKey;
}
