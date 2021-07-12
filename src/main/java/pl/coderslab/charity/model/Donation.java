package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull(message = "Musi być przynajmniej 1 i maksymalnie milion")
    @Min(value = 1, message = "Musi być przynajmniej 1 i maksymalnie milion")
    @Max(value = 1000000, message = "Musi być przynajmniej 1 i maksymalnie milion")
    private Integer quantity;

//    @Size(min = 1, message = "Musi być wybrana co najmniej jedna kategoria")
    @ManyToMany
    @JoinTable(name = "donations_categories")
    private List<Category> categories = new ArrayList<>();

//    @NotNull(message = "Musi być wybrana instytucja")
    @ManyToOne
    private Institution institution;

    @NotBlank(message = "Pole musi być niepuste")
    private String street;

    @NotBlank(message = "Pole musi być niepuste")
    private String city;

    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Kod pocztowy musi być w formacie: dd-ddd")
    private String zipCode;

    @Pattern(regexp = "^[\\+]?\\d{9,}$", message = "Numer telefonu musi być w formacie bez spacji i nawiasów i zawierać co najmniej 9 cyfr: +ddddddddd lub ddddddddd")
    private String phone;

    @NotNull(message = "Musi być w formacie yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull(message = "Musi być w formacie HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    private String pickUpComment;
}
