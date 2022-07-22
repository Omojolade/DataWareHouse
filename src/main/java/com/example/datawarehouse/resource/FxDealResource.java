package com.example.datawarehouse.resource;

import com.example.datawarehouse.aop.response.ResponseWrapper;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.FxDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ResponseWrapper
@RestController
@RequestMapping("/api/")
public class FxDealResource {
    private final Logger log = LoggerFactory.getLogger(FxDealResource.class);

    private static final String APPLICATION_NAME = "DataWarehouse";

    private final FxDealService fxDealService;

    public FxDealResource(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/create/fx-deal")
    public ResponseEntity<FxDealDTO> saveFxDeal(@Valid @RequestBody FxDealDTO fxDealDTO) throws URISyntaxException {
        log.debug("REST request to save fxDeal : {}", fxDealDTO);
        FxDealDTO fxDeal = fxDealService.saveFxDeal(fxDealDTO);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", APPLICATION_NAME, "/fx-deal/", fxDeal.getId())))
                .body(fxDeal);
    }

    @PostMapping("/fx-deals")
    public ResponseEntity<List<FxDealDTO>> saveMultipleFxDeals(@Valid @RequestBody List<FxDealDTO> fxDealDTOs) throws URISyntaxException {
        log.debug("REST request to save fxDeals : {}", fxDealDTOs);
        List<FxDealDTO> fxDeals = fxDealService.saveMultipleFxDeals(fxDealDTOs);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", APPLICATION_NAME, "/fx-deal/", fxDeals.get(0).getId())))
                .body(fxDeals);
    }

    @GetMapping("/fx-deal/{id}")
    public ResponseEntity<FxDealDTO> getFxDealById( @PathVariable("id") Long id) {
        log.debug("REST request to get fxDeal by : {}", id);
        FxDealDTO fxDealDTO = fxDealService.getFxDealById(id);
        return ResponseEntity.ok(fxDealDTO);
    }

    @GetMapping("/fx-deals")
    public ResponseEntity<List<FxDealDTO>> getAllFxDeal() {
        log.debug("REST request to get list of fxDeals");
        return ResponseEntity.ok().body(fxDealService.getAllFxDeals());
    }

}
