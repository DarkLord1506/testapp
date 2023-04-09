package com.myapp.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    @Id
    @NotNull
    private Long id;
    @NotBlank
    @JsonProperty("city")
    private String city;
    @NotNull
    @JsonProperty("phoneNo")
    private Long phoneNumber;

    @NotNull
    @JsonProperty("age")
    private Integer age;
}
