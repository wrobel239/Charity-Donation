package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donations_categories")
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;
}
