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

@ResponseWrapper(message = "OPERATION SUCCESSFUL")
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

    @GetMapping("/fx-deal/{id}")
    public ResponseEntity<FxDealDTO> getFxDealById( @PathVariable("id") String id) {
        log.debug("REST request to get fxDeal by : {}", id);
        FxDealDTO fxDealDTO = fxDealService.getFxDealById(id);
        return ResponseEntity.ok(fxDealDTO);
    }

    @GetMapping("/fx-deals")
    public ResponseEntity<List<FxDealDTO>> getAllFxDeals() {
        log.debug("REST request to get list of fxDeals");
        return ResponseEntity.ok().body(fxDealService.getAllFxDeals());
    }

}
