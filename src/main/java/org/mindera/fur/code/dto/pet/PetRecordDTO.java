package org.mindera.fur.code.dto.pet;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PetRecordDTO {

    private Long id;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @NotNull(message = "Vaccination status is required")
    private Boolean isVaccinated;

    // Criar um enum de intervenções
    @NotBlank(message = "Intervention type is required")
    // @NotNull(message = "Intervention type is required")
    private String petRecordsStatus;

    @Valid
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private Date date;

    @NotNull(message = "Observation is required")
    @Size(max = 999, message = "Observation cannot be longer than 999 characters")
    private String observation;
}