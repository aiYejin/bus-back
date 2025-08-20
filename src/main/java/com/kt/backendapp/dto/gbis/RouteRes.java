package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteRes {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        public String comMsgHeader;
        
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class MsgHeader {
            public String queryTime;
            public Integer resultCode;
            public String resultMessage;
        }
        public MsgHeader msgHeader;
        
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class MsgBody {
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class BusRouteInfoItem {
                public String busRouteInfoItem;
                public Integer sunPeekAlloc;
                public String adminName;
                public Integer companyId;
                public String companyName;
                public String companyTel;
                public Integer districtCd;
                public String downFirstTime;
                public String downLastTime;
                public Integer endMobileNo;
                public Integer endStationId;
                public String endStationName;
                public String garageName;
                public String garageTel;
                public String multiFlag;
                public Integer peekAlloc;
                public String regionName;
                public Integer routeId;
                public String routeName;
                public Integer routeTypeCd;
                public String routeTypeName;
                public String satDownFirstTime;
                public String satDownLastTime;
                public Integer satNPeekAlloc;
                public Integer satPeekAlloc;
                public String satUpFirstTime;
                public String satUpLastTime;
                public Integer startStationId;
                public String startStationName;
                public Integer startMobileNo;
                public String sunDownFirstTime;
                public String sunDownLastTime;
                public Integer sunNPeekAlloc;
                public String sunUpFirstTime;
                public String sunUpLastTime;
                public Integer turnStID;
                public String turnStNm;
                public String upFirstTime;
                public String upLastTime;
                public String weDownFirstTime;
                public String weDownLastTime;
                public Integer weNPeekAlloc;
                public Integer wePeekAlloc;
                public String weUpFirstTime;
                public String weUpLastTime;
                public Integer nPeekAlloc;
            }
            public BusRouteInfoItem busRouteInfoItem;
        }
        public MsgBody msgBody;
    }
    public Response response;
}