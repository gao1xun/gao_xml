package jp.co.eb.fliggy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "site-controller")
public class SiteControllerProperties {
    private Temairaze temairaze;
    private TlLicoln tlLicoln;

    @Data
    public static class Temairaze {
        private String partnerId;
        private String password;
        private String endpoint;
    }

    @Data
    public static class TlLicoln {
        private Authentication authentication;
        private Endpoint endpoint;

        @Data
        public static class Authentication {
            private String username;
            private String password;
        }

        @Data
        public static class Endpoint {
            private String hotelInfo;
            private String availableInquiry;
            private String reservation;
        }
    }
}
