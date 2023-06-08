package com.labelvie.lablecious.backend.models.dto;


import com.labelvie.lablecious.backend.models.entity.Plate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlateDto {
    private  long id;

    @NotBlank(message = "Name is required")
    @Min(value = 3, message = "Name must be at least 3 characters")
    private String name;

    @Min(value = 15, message = "Description must be at least 15 characters")
    private String description;

    private String image;

    @NotBlank(message = "Category is required")
    private CategoryDto categoryDto;


    public  static  PlateDto fromPlate(Plate plate){
        return PlateDto.builder()
                .id(plate.getId())
                .name(plate.getName())
                .description(plate.getDescription())
                .image(plate.getImage())
                .categoryDto(CategoryDto.fromCategory(plate.getCategory()))
                .build();
    }

    public  static List<PlateDto> fromPlates(List<Plate> plates){
        return plates.stream()
                .map(PlateDto::fromPlate)
                .collect(Collectors.toList());
    }


    public Plate toPlate(){
        return Plate.builder()
                .name(this.getName())
                .description(this.getDescription())
                .image(this.getImage())
                .build();
    }

}
