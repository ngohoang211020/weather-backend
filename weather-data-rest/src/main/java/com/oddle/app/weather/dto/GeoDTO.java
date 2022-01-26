package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeoDTO {
    @JsonProperty("lon")
    private double longitude;
    @JsonProperty("lat")
    private double latitude;
}
