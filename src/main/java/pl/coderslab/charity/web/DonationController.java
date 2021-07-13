package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/donation/add")
    public String addDonation(Model model, @ModelAttribute @Valid Donation donation, BindingResult bindingResult, @RequestParam(required = false) List<Long> categories, @RequestParam(required = false) Long organization){
        if (bindingResult.hasErrors()){
            List<Category> categoriesAvailable = categoryService.getCategories();
            List<Institution> institutions = institutionService.getInstitutions();
            model.addAttribute("categories", categoriesAvailable);
            model.addAttribute("institutions", institutions);
            return "donationForm";
        }
        if (categories == null || organization == null){
            throw new EntityNotFoundException("Musisz zaznaczyć co najmniej jedną kategorię i jedną fundację");
        }
        Donation savedDonation = donationService.saveDonation(donation, categories, organization);
        model.addAttribute("categoriesToString", categoriesNameToString(savedDonation));
        model.addAttribute("donation", savedDonation);
        return "donationSummary";
    }

    public String categoriesNameToString (Donation donation){
        return donation.getCategories().stream()
                .map(category -> category.getName())
                .collect(Collectors.joining(", "));
    }
}
