package com.oddle.app.weather.dto;

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
public class SysDTO {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
