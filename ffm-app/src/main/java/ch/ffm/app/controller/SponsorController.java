package ch.ffm.app.controller;

import ch.ffm.app.config.HeaderHelper;
import ch.ffm.data.repository.SponsorRepository;
import ch.ffm.model.entity.Sponsor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sponsors")
public class SponsorController {

    private final SponsorRepository sponsorRepository;

    public SponsorController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    /**
     * @return sponsors ordered by ordering field
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<Sponsor>> sponsors() {
        var sponsors = sponsorRepository.findAllByOrderByOrderingAsc();
        return HeaderHelper.createOKResponseEntityDefaultCacheControl(sponsors);
    }
}
