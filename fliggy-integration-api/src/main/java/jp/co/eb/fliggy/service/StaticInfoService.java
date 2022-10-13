package jp.co.eb.fliggy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.eb.fliggy.api.staticinfo.param.HotelBasicParam;
import jp.co.eb.fliggy.api.staticinfo.vm.HotelBasic;
import jp.co.eb.fliggy.api.staticinfo.vm.HotelBasic.Hotel;

@Service
public class StaticInfoService {
    
    public HotelBasic getHotelList(HotelBasicParam hotelBasicParam) {
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel = new Hotel();
        hotel.setHotelCode("aaa");
        hotel.setName("name");
        hotels.add(hotel);

        hotel = new Hotel();
        hotel.setHotelCode("bbb");
        hotel.setName("namebb");
        hotels.add(hotel);
        HotelBasic hotelBasic = new HotelBasic(hotels);

        return hotelBasic;
    }
}
