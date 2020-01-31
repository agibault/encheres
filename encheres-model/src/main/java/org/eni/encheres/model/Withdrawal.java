package org.eni.encheres.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Withdrawal {
    private String street;
    private String postalCode;
    private String city;

    @NotBlank
    @Size(max = 30)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    @NotBlank
    @Pattern(message = "Le code postal n'est pas au bon format", regexp = "[0-9]{5}")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    @NotBlank
    @Size(min = 30)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
