package org.mindera.fur.code.dto.shelter;

import lombok.Data;

import java.util.Date;

@Data
public class ShelterDTO {
    private Long id;
    private String name;
    private Long vat;
    private String email;
    private String address1;
    private String address2;
    private String postCode;
    private Long phone;
    private Long size;
    private Boolean isActive;
    private Date creationDate;


    public ShelterDTO(String name, Long vat, String email, String address1, String address2, String postCode, Long phone, Long size, Boolean isActive, Date creationDate) {
        this.name = name;
        this.vat = vat;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.postCode = postCode;
        this.phone = phone;
        this.size = size;
        this.isActive = isActive;
        this.creationDate = creationDate;
    }
}
