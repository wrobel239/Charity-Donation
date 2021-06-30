package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @Min(value = 1, message = "Musi być przynajmniej 1 i maksymalnie milion")
    @Max(value = 1000000, message = "Musi być przynajmniej 1 i maksymalnie milion")
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donations_categories")
    private List<Category> categories = new ArrayList<>();

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime pickUpTime;

    private String pickUpComment;
}
