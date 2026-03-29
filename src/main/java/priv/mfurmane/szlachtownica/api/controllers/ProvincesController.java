package priv.mfurmane.szlachtownica.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.mfurmane.szlachtownica.api.dto.Province;
import priv.mfurmane.szlachtownica.api.services.ProvinceService;

import java.util.List;

@RestController
@RequestMapping("/world/provinces")
public class ProvincesController {

    @Autowired
    ProvinceService service;

    @GetMapping("/all")
    public ResponseEntity<List<Province>> all() {
        return ResponseEntity.ok(service.getAll());
    }

}
