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
            private String surName;// 姓
            private String givenName;// 名
        }

    }
    
    @Data
    public static class RefundRule implements Serializable {
        private static final long serialVersionUID = 1758454043795105552L;
        private Boolean returnable;// 是,是否可退订，FALSE: 不可退
        private Integer refundType;// 是,罚金类型，如returnable=false，非必填；如returnable=true，必填。
                                   // 0：不扣款；1：按固定金额扣款；2：按比例扣款；
        private Integer fine;// 否,罚金，refundType=1时必填，单位：分，没有填0
        private Integer percent;// 否,罚金比例，refundType=2时必填，如20表示罚金为20%
        private String refundDesc;// 否,退订规则描述
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime maxBeforeCheckIn;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime minBeforeCheckIn;
        private Integer maxDayBeforeCheckIn;// 是
                                            // 退订规则开始时间，checkin前小时数。如果存在多重规则（先免费后付费最后不可取消），需分三段时间单独列出取消规则，且每一段开始时间需与前一段结束时间一致。
                                            // 需注意：规则时间需要覆盖checkin前的所有时间段，不允许遗漏或者重合。比如：
                                            // maxDayBeforeCheckIn：null，minHoursBeforeCheckIn：6，代表checkin6天前取消的规则；
                                            // maxDayBeforeCheckIn：2，minDayBeforeCheckIn：5，代表checkin2天前取消的规则；
                                            // maxDayBeforeCheckIn：1，minDayBeforeCheckIn：0，代表checkin2天内取消的规则
        private Integer minDayBeforeCheckIn;// 是,退订规则结束时间，checkin前小时数，规则到此时间结束
    }
}
