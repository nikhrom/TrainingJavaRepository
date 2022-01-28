package github.nikhrom.javatraining.advanced_hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("programmer")
@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends User{

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, String username, PersonalData personalData, Role role, Company company, List<UserChat> userChats, Language language) {
        super(id, username, personalData, role, company, userChats);
        this.language = language;
    }
}
