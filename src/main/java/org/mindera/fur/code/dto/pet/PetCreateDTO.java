package org.mindera.fur.code.dto.pet;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.mindera.fur.code.model.enums.pet.PetSizeEnum;

import java.time.LocalDate;
import java.util.Date;

/**
 * A DTO class for creating a pet.
 */
@Data
public class PetCreateDTO {

    @NotBlank(message = "Pet name must be provided")
    @Size(min = 1, max = 30, message = "Pet name must be between 1 and 30 characters")
    @Schema(description = "The name of the pet", example = "Bobby")
    private String name;

    @Positive(message = "Pet type ID must be greater than 0")
    @NotNull(message = "Pet type ID must be provided")
    @Schema(description = "The ID of the pet type", example = "1")
    private Long petTypeId;

    @Positive(message = "Shelter ID must be greater than 0")
    @NotNull(message = "Shelter ID must be provided")
    @Schema(description = "The ID of the shelter", example = "1")
    private Long shelterId;

    @NotNull(message = "Adopted status must be provided")
    @Schema(description = "The adopted status of the pet", example = "false")
    private Boolean isAdopted;

    @NotNull(message = "Vaccination status is required")
    @Schema(description = "The vaccination status of the pet", example = "false")
    private Boolean isVaccinated;

    @NotNull(message = "Size must be provided")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The size of the pet", example = "SMALL")
    private PetSizeEnum size;

    @NotNull(message = "Pet weight must be provided")
    @DecimalMin(value = "0.01", message = "Pet weight must be greater than 0.01 kilos")
    @DecimalMax(value = "999.99", message = "Pet weight must be less than 999.99 kilos")
    @Schema(description = "The weight of the pet", example = "5.5")
    private Double weight;

    @NotBlank(message = "Pet color must be provided")
    @Size(min = 3, max = 99, message = "Pet color must be between 3 and 99 characters")
    @Schema(description = "The color of the pet", example = "Blue")
    private String color;

    @NotNull(message = "Pet age must be provided")
    @PastOrPresent(message = "Pet birth date cannot be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Pet observation must be provided")
    @Size(min = 1, max = 999, message = "Pet observation must be between 1 and 999 characters")
    @Schema(description = "The observations of the pet", example = "I love my pet")
    private String observations;
}
