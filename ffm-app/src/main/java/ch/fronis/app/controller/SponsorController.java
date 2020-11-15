package ch.fronis.app.controller;

import ch.fronis.app.config.HeaderHelper;
import ch.fronis.data.repository.SponsorRepository;
import ch.fronis.model.entity.Sponsor;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SponsorController {

    private final SponsorRepository sponsorRepository;

    public SponsorController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    /**
     * @return sponsors ordered by ordering field
     */
    @GetMapping("/sponsors")
    public ResponseEntity<List<Sponsor>> sponsors() {
        var sponsors = sponsorRepository.findAllByOrderByOrderingAsc().stream()
                .sorted(Comparator.comparing(Sponsor::getOrdering))
                .collect(Collectors.toList());
        return HeaderHelper.createOKResponseEntityDefaultCacheControl(sponsors);
    }
}
