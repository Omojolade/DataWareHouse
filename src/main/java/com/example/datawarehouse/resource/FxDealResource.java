package com.example.datawarehouse.resource;

import com.example.datawarehouse.ResponseWrapper;
import com.example.datawarehouse.service.FxDealDTO;
import com.example.datawarehouse.service.FxDealService;
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

    private static final String APPLICATION_NAME = "DataWarehouse";

    private final FxDealService fxDealService;

    public FxDealResource(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/create/fx-deal")
    public ResponseEntity<FxDealDTO> saveFxDeal(@Valid @RequestBody FxDealDTO fxDealDTO) throws URISyntaxException {
        FxDealDTO fxDeal = fxDealService.saveFxDeal(fxDealDTO);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", APPLICATION_NAME, "/fx-deal/", fxDeal.getId())))
                .body(fxDeal);
    }

    @PostMapping("/fx-deals")
    public ResponseEntity<List<FxDealDTO>> saveMultipleFxDeals(@Valid @RequestBody List<FxDealDTO> fxDealDTOs) throws URISyntaxException {
        List<FxDealDTO> fxDeals = fxDealService.saveMultipleFxDeals(fxDealDTOs);
        return ResponseEntity
                .created(new URI(String.format("%s%s%s", APPLICATION_NAME, "/fx-deal/", fxDeals.get(0).getId())))
                .body(fxDeals);
    }

    @GetMapping("/fx-deal/{id}")
    public ResponseEntity<FxDealDTO> getFxDealById( @PathVariable("id") Long id) {
        FxDealDTO fxDealDTO = fxDealService.getFxDealById(id);
        return ResponseEntity.ok(fxDealDTO);
    }

    @GetMapping("/fx-deals")
    public ResponseEntity<List<FxDealDTO>> getAllFxDeal() {
        return ResponseEntity.ok().body(fxDealService.getAllFxDeals());
    }

}
