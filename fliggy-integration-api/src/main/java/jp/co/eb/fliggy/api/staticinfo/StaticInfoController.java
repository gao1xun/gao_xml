package jp.co.eb.fliggy.api.staticinfo;

import java.util.Map;

import jp.co.eb.fliggy.service.ValidatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.eb.fliggy.api.staticinfo.param.HotelBasicParam;
import jp.co.eb.fliggy.api.staticinfo.vm.HotelBasic;
import jp.co.eb.fliggy.service.StaticInfoService;

import javax.validation.Valid;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_XML_VALUE )
public class StaticInfoController {

    @Autowired
    private StaticInfoService staticInfoService;

    @Autowired
    private ValidatingService validatingService;

    // @RequestTrace()
    @PostMapping("/hotelList")
    public HotelBasic getHotelList(@RequestBody HotelBasicParam hotelBasicParam) {
        
        validatingService.validate(hotelBasicParam);
        HotelBasic hotelBasic = staticInfoService.getHotelList(hotelBasicParam);
        return hotelBasic;
    }

    @PostMapping("/hotelList1")
    // @RequestTrace()
    public Map<String, Object> getHotelList1(@RequestBody Map<String, Object> CityCode) {
        
        return CityCode;
    }
}
