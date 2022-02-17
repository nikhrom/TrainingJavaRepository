package github.nikhrom.javatraining.spring.boot.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadDepartmentDto {
    int id;
    String name;
    String minSalary;
    String maxSalary;
}
