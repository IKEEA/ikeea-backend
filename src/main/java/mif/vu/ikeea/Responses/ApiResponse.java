package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
