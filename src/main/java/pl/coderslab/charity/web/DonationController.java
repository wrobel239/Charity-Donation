package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
public class DonationController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CategoryService categoryService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.categoryService = categoryService;
    }

    @GetMapping("/donation/form")
    public String showFormDonation(Model model){
        List<Category> categories = categoryService.getCategories();
        List<Institution> institutions = institutionService.getInstitutions();
        Donation donation =  new Donation();
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", donation);
        return "donationForm";
    }
}
