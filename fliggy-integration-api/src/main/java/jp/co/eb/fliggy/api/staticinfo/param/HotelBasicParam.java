package jp.co.eb.fliggy.api.staticinfo.param;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jp.co.eb.fliggy.converter.AbstractParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

@Data
@EqualsAndHashCode(callSuper = false)
@JacksonXmlRootElement(localName = "HotelListRequest")
public class HotelBasicParam extends AbstractParameter {


    private static final long serialVersionUID = 1594891588276602367L;

    @JacksonXmlElementWrapper(localName = "HotelCodes")
    @JacksonXmlProperty(localName = "HotelCode")
    private List<String> HotelCodes;

    @NotNull(message = "CityCode不能为空")
    @JacksonXmlProperty(localName = "CityCode")
    private String CityCode;

    @NotNull(message = "CheckIn不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "入住日期(CheckIn)，格式为YYYY-MM-DD")
    @JacksonXmlProperty(localName = "CheckIn")
    private String CheckIn;

    @NotNull(message = "CheckOut不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "离店日期(CheckOut)，格式为YYYY-MM-DD")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String CheckOut;


    @NotNull(message = "PaymentType不能为空")
    @Range(min = 1, max = 5, message = "Payment type: 1: Prepay; 5: Pay at hotel")
    @JacksonXmlProperty(localName = "PaymentType")
    private Integer PaymentType;

//    @JacksonXmlElementWrapper(localName = "PaxRooms")
    @JacksonXmlProperty(localName = "PaxRoom")
    private List<PaxRoom> PaxRooms;
    

    // @Data
    // public static class HotelCode {
    //     @NotNull(message = "偏移量(HotelCodes.HotelCode)不能为空")
    //     private String HotelCode;
    // }

    @Data
    public static class PaxRoom {

        @NotNull(message = "房间序号(RoomIndex)不能为空")
        @Range(min = 1, max = 5, message = "房间序号(RoomIndex)范围1 - 5")
        @JacksonXmlProperty(localName = "RoomIndex")
        private Integer RoomIndex;

        @NotNull(message = "成年人数(Adults)不能为空")
        @Range(min = 1, max = 5, message = "成年人数(Adults)范围1 - 5")
        @JacksonXmlProperty(localName = "Adults")
        private Integer Adults;

        @NotNull(message = "儿童人数(Children)不能为空")
        @Range(min = 1, max = 5, message = "儿童人数(Children)范围1 - 3")
        @JacksonXmlProperty(localName = "Children")
        private Integer Children;

        @NotNull(message = "儿童年龄列表(ChildrenAges)不能为空")
        @JacksonXmlElementWrapper(localName = "ChildrenAges")
        @JacksonXmlProperty(localName = "Age")
        private List<Integer> ChildrenAges;
    }








    // PaxRooms.PaxRoom
    // PaxRooms.PaxRoom.RoomIndex
    // PaxRooms.PaxRoom.Adults
    // PaxRooms.PaxRoom.Children
    // PaxRooms.PaxRoom.ChildrenAges
    // PaxRooms.PaxRoom.ChildrenAges.age


}
