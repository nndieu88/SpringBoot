package com.smartosc.mobile.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UpdateUserRequest {
    @Min(6)
    @ApiModelProperty(
            example ="Dieu Nguyen",
            notes = "from 6 characters",
            required = true
    )
    @JsonProperty("full_name")
    private String name;

    @NotNull
    @NotEmpty
    @ApiModelProperty(
            example = "Ha Noi",
            notes = "not null, not empty",
            required = true
    )
    private String address;

    @Pattern(regexp = "(09|01[2|6|8|9])+([0-9]{8})\\\\b")
    @ApiModelProperty(
            example = "0901234567",
            notes = "cannot be empty",
            required = true
    )
    private String phone;

    private String avatar;

    @NotNull
    @NotEmpty
    @Email
    @ApiModelProperty(
            example = "nndieu@gmail.com",
            notes = "not null, not empty",
            required = true
    )
    private String email;

    @Min(6)
    @ApiModelProperty(
            example = "verryserectpassword",
            notes = "from 6 characres",
            required = true
    )
    private String password;
}
