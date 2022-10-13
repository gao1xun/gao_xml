package jp.co.eb.fliggy.api.staticinfo.vm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "HotelListResponse")
public class HotelBasic implements Serializable {

    private static final long serialVersionUID = 3970927238777743820L;

    @JacksonXmlProperty(localName = "Hotel")
    @JacksonXmlElementWrapper(localName = "Hotels")
    private List<Hotel> Hotels = new ArrayList<>();

    @Data
    public static class Hotel implements Serializable {

        private static final long serialVersionUID = -3332531602254094242L;

        @JacksonXmlProperty(localName = "HotelCode")
        private String HotelCode;

        @JacksonXmlProperty(localName = "CheckIn")
        private String CheckIn;

        @JacksonXmlProperty(localName = "CheckOut")
        private String CheckOut;

        @JacksonXmlProperty(localName = "Name")
        private String Name;

        @JacksonXmlProperty(localName = "EnglishName")
        private String EnglishName;

        @JacksonXmlProperty(localName = "Address")
        private String Address;

        @JacksonXmlProperty(localName = "CityCode")
        private String CityCode;

        @JacksonXmlProperty(localName = "PaymentType")
        private Integer PaymentType;

        @JacksonXmlProperty(localName = "MinPrice")
        private BigDecimal MinPrice;

        @JacksonXmlProperty(localName = "MinTaxPrice")
        private BigDecimal MinTaxPrice;

        @JacksonXmlProperty(localName = "CurrencyCode")
        private String CurrencyCode;

    }
}
