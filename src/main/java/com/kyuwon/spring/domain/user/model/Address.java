package com.kyuwon.spring.domain.user.model;

import lombok.*;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Setter
    private String country;

    @Setter
    private String state;

    @Setter
    private String city;

    @Setter
    private String zipCode;

    @Builder
    public Address(String country, String state, String city, String zipCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }
}