package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionService.getInstitutions();
        Long totalQuantity = donationService.getTotalQuantity();
        Long numberOfAllDonations = donationService.getNumberOfAllDonations();
        if (totalQuantity == null){
            totalQuantity =0L;
        }
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("numberOfAllDonations", numberOfAllDonations);
        model.addAttribute("institutions", institutions);
        return "index";
    }
}
