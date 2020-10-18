package ch.fronis.admin.controller;

import ch.fronis.data.repository.SponsorRepository;
import ch.fronis.model.entity.Sponsor;
import ch.fronis.model.reactadmin.DeletedResponse;
import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AdminSponsorController {

    private static final Logger logger = LoggerFactory.getLogger(AdminSponsorController.class);

    private final SponsorRepository sponsorRepository;

    public AdminSponsorController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @GetMapping("/sponsors")
    public ResponseEntity<List<Sponsor>> allSponsors(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer perPage,
            @RequestParam(required = false, defaultValue = "id") String field,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction order) {
        PageRequest request = PageRequest.of(page, perPage, Sort.by(order, field));
        Page<Sponsor> result = sponsorRepository.findAllByOrderByOrderingAsc(request);
        return ResponseEntity.ok()
                .header("Content-Range", perPage + "/" + result.getTotalElements())
                .body(result.getContent());
    }

    @GetMapping("/sponsors/{sponsorId}")
    public Sponsor oneSponsor(@PathVariable Integer sponsorId) throws RuntimeException {
        return sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot handle sponsor GET request. Sponsor with id=%d not found", sponsorId)));
    }

    @PostMapping("/sponsors")
    public Sponsor newSponsor(@RequestBody Sponsor newSponsor) {
        return sponsorRepository.save(newSponsor);
    }

    @PutMapping("/sponsors/{sponsorId}")
    public Sponsor replaceSponsor(@RequestBody Sponsor newSponsor, @PathVariable Integer sponsorId)
            throws PacketTooBigException {
        try {
            return sponsorRepository.findById(sponsorId)
                    .map(sponsor -> {
                        sponsor.setName(newSponsor.getName());
                        sponsor.setUrl(newSponsor.getUrl());
                        sponsor.setImage(newSponsor.getImage());
                        sponsor.setOrdering(newSponsor.getOrdering());
                        return sponsorRepository.save(sponsor);
                    }).orElseThrow(() ->
                            new RuntimeException(String.format(
                                    "Cannot handle sponsor PUT request. Sponsor with id=%d not found", sponsorId)));
        } catch (JpaSystemException e) {
            if (PacketTooBigException.class.equals(e.getCause().getClass())) {
                logger.info("Max image size exceeded. Upload a smaller image. (sponsorId=" + sponsorId + ")");
                throw new PacketTooBigException("Max image size exceeded. Upload a smaller image.");
            }
            throw e;
        }
    }

    @DeleteMapping("/sponsors/{sponsorId}")
    public ResponseEntity<DeletedResponse> deleteSponsor(@PathVariable Integer sponsorId) {
        sponsorRepository.deleteById(sponsorId);
        logger.info("deleted sponsor with id: " + sponsorId);
        return ResponseEntity.ok().body(new DeletedResponse(sponsorId));
    }
}
