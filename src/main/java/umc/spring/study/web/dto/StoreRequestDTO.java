package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.study.validation.annotation.ExistRegion;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDto {
        @NotBlank
        String name;
        @Size(min = 5, max = 30)
        String address;
        @ExistRegion
        Long region;
    }
}
