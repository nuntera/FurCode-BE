package org.mindera.fur.code.dto.shelter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for a shelter.
 */
@Data
@NoArgsConstructor
public class ShelterDTO implements Serializable {

    @Positive(message = "Id must be greater than 0")
    @NotNull(message = "Id must be provided")
    @Schema(description = "The id of the shelter", example = "1")
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(max = 100, message = "Name must be between 1 and 100 characters")
    @Schema(description = "The name of the shelter", example = "Shelter 1")
    private String name;

    @NotBlank(message = "Vat can't be empty")
    @Size(max = 50, message = "Vat must be between 1 and 50 characters")
    @Schema(description = "The vat of the shelter", example = "12345")
    private String vat;

    @NotBlank(message = "Email can't be null")
    @Size(max = 50, message = "Email must be between 1 and 50 characters")
    @Email(message = "Email must be valid")
    @Schema(description = "The email of the shelter", example = "shelter@example.com")
    private String email;

    @NotBlank(message = "Address1 can't be empty")
    @Size(max = 200, message = "Address1 must be between 1 and 200 characters")
    @Schema(description = "The address1 of the shelter", example = "123 Main Street")
    private String address1;

    @NotBlank(message = "Address2 can't be empty")
    @Size(max = 200, message = "Address2 must be between 1 and 200 characters")
    @Schema(description = "The address2 of the shelter", example = "Apartment 1")
    private String address2;

    @NotBlank(message = "Postal code can't be empty")
    @Size(max = 20, message = "Postal code must be between 1 and 20 characters")
    @Schema(description = "The postal code of the shelter", example = "12345")
    private String postalCode;

    @NotBlank(message = "Phone can't be empty")
    @Size(max = 20, message = "Phone must be between 1 and 20 characters")
    @Schema(description = "The phone of the shelter", example = "1234567890")
    private String phone;

    @NotBlank(message = "Size can't be empty")
    @Size(max = 20, message = "Size must be between 1 and 20 characters")
    @Schema(description = "The size of the shelter", example = "10")
    private String size;

    @NotNull(message = "IsActive can't be null")
    @Schema(description = "The is active of the shelter", example = "true")
    private Boolean isActive;

    @NotNull(message = "CreationDate can't be null")
    @Schema(description = "The creation date of the shelter", example = "2023-01-01")
    private LocalDate creationDate;

    @Schema(description = "The description of the shelter", example = "This is a description")
    private String description;

    @Schema(description = "The facebook url of the shelter", example = "https://www.facebook.com/furcode")
    private String facebookUrl;

    @Schema(description = "The instagram url of the shelter", example = "https://www.instagram.com/furcode")
    private String instagramUrl;

    @Schema(description = "The twitter url of the shelter", example = "https://www.twitter.com/furcode")
    private String webPageUrl;
}
