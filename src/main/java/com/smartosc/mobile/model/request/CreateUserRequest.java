package com.smartosc.mobile.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @Size(min = 6, max = 20)
    @ApiModelProperty(
            example = "Dieu Nguyen",
            notes = "from 6 characters",
            required = true
    )
//    @JsonProperty("full_name")
    private String name;

    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Size(min = 6, max = 12)
    @ApiModelProperty(
            example = "verryserectpassword",
            notes = "from 6 characres",
            required = true
    )
    private String password;

}
