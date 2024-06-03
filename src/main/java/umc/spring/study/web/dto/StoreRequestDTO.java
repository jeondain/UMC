package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDto {
        @NotBlank
        String name;
        @Size(min = 5, max = 30)
        String address;
        @NotNull
        Long region;
    }
}
