package org.mindera.fur.code.dto.Person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonCreationDTO {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    private Integer nif;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String address1;

    private String address2;

    @NotNull
    @NotBlank
    private Integer postalCode;

    private Integer cellPhone;

    public PersonCreationDTO() {
    }

    public PersonCreationDTO(String firstName, String lastName, Integer nif, String email, String password, String address1, String address2, Integer postalCode, Integer cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.email = email;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.cellPhone = cellPhone;
    }
}