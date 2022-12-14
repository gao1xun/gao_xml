package jp.co.eb.fliggy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1936173262431869669L;

    private RoomSearchInfo searchInfos;
    private List<DayInfo> dayInfos = new ArrayList<>();
    private GuestResult guestResult;
    private List<RoomInfo> roomInfos = new ArrayList<>();
    private List<RefundRule> refundRules = new ArrayList<>();
    

    @Data
    public static class RoomSearchInfo implements Serializable {
        private static final long serialVersionUID = 2855627222746414967L;

        private Integer adults;

        private List<Integer> children;
    }

    @Data
    public static class GuestResult implements Serializable {
        private static final long serialVersionUID = -6753905607065040644L;
        private Integer adults;
        private Map<String, List<Integer>> division = new HashMap<>();
    }

    @Data
    public static class DayInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        private LocalDate date;
        private BigDecimal money;
    }

    @Data
    public static class RoomInfo implements Serializable {
        private static final long serialVersionUID = -3489199468401003116L;
        @NotEmpty
        private List<Name> names = new ArrayList<>();

        @Data
        public static class Name implements Serializable {
            private static final long serialVersionUID = 796677601437108919L;
            private String surName;// ???
            private String givenName;// ???
        }

    }
    
    @Data
    public static class RefundRule implements Serializable {
        private static final long serialVersionUID = 1758454043795105552L;
        private Boolean returnable;// ???,??????????????????FALSE: ?????????
        private Integer refundType;// ???,??????????????????returnable=false??????????????????returnable=true????????????
                                   // 0???????????????1???????????????????????????2?????????????????????
        private Integer fine;// ???,?????????refundType=1????????????????????????????????????0
        private Integer percent;// ???,???????????????refundType=2???????????????20???????????????20%
        private String refundDesc;// ???,??????????????????
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime maxBeforeCheckIn;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime minBeforeCheckIn;
        private Integer maxDayBeforeCheckIn;// ???
                                            // ???????????????????????????checkin?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                                            // ????????????????????????????????????checkin???????????????????????????????????????????????????????????????
                                            // maxDayBeforeCheckIn???null???minHoursBeforeCheckIn???6?????????checkin6????????????????????????
                                            // maxDayBeforeCheckIn???2???minDayBeforeCheckIn???5?????????checkin2????????????????????????
                                            // maxDayBeforeCheckIn???1???minDayBeforeCheckIn???0?????????checkin2?????????????????????
        private Integer minDayBeforeCheckIn;// ???,???????????????????????????checkin???????????????????????????????????????
    }
}
