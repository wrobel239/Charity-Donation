package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationService(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    public Long getTotalQuantity(){
        return donationRepository.getTotalQuantity();
    }

    public Long getNumberOfAllDonations(){
        return donationRepository.getNumberOfAllDonations();
    }

    public Donation saveDonation (Donation donation, List<Long> categories, Long organization){
        Optional<Institution> institution = institutionRepository.findById(organization);
        if(institution.isEmpty()){
            throw new EntityNotFoundException("Musisz zaznaczyć jedną fundację");
        }
        List<Category> filteredCategories = categories.stream()
                .map(num -> categoryRepository.findById(num))
                .filter(cat -> cat.isPresent())
                .map(cat -> cat.get())
                .collect(Collectors.toList());
        if (filteredCategories.size() <= 0){
            throw new EntityNotFoundException("Musisz zaznaczyć co najmniej jedną kategorię");
        }
        donation.setCategories(filteredCategories);
        donation.setInstitution(institution.get());
        donationRepository.save(donation);
        return donation;
    }
}
