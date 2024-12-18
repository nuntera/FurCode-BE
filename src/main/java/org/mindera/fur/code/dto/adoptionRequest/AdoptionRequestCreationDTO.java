package org.mindera.fur.code.dto.adoptionRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.mindera.fur.code.model.State;

/**
 * DTO for creating an adoption request.
 */
@Data
@AllArgsConstructor
@Schema(description = "An adoption request creation request")
public class AdoptionRequestCreationDTO {

    @Digits(integer = 19, fraction = 0, message = "Pet ID must be an integer")
    @Positive(message = "Adoption request ID must be greater than 0")
    @NotNull(message = "Adoption request ID must be provided")
    @Schema(description = "The unique identifier of the adoption request", example = "1")
    private Long shelterId;

    @Digits(integer = 19, fraction = 0, message = "Person ID must be an integer")
    @Positive(message = "Person ID must be greater than 0")
    @NotNull(message = "Person ID must be provided")
    @Schema(description = "The id of the person", example = "1")
    private Long personId;

    @Digits(integer = 19, fraction = 0, message = "Pet ID must be an integer")
    @Positive(message = "Pet ID must be greater than 0")
    @NotNull(message = "Pet ID must be provided")
    @Schema(description = "The id of the pet", example = "1")
    private Long petId;

    @NotNull(message = "State must be provided")
    @Enumerated(EnumType.STRING)
    @Schema(description = "The state of the adoption request", example = "SENT")
    private State state;

//    @NotNull(message = "Request details must be provided")
//    @Schema(description = "The request details of the adoption request")
//    private Set<RequestDetailDTO> requestDetails;

    //TODO VERIFY HOW TO LEAVE SOMETHING TO SENT BY DEFAULT
}
