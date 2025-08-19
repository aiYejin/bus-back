package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class ArrivalRes {
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
            public static class BusArrivalList {
                public String busArrivalList;
                public Integer stateCd1;
                public Integer stateCd2;
                public Integer crowded1;
                public Integer crowded2;
                public String flag;
                public Integer locationNo1;
                public Integer locationNo2;
                public Integer lowPlate1;
                public Integer lowPlate2;
                public String plateNo1;
                public String plateNo2;
                public Integer predictTime1;
                public Integer predictTime2;
                public Integer remainSeatCnt1;
                public Integer remainSeatCnt2;
                public Integer routeDestId;
                public String routeDestName;
                public Integer routeId;
                public String routeName;
                public Integer routeTypeCd;
                public Integer staOrder;
                public Integer stationId;
                public String stationNm1;
                public String stationNm2;
                public Integer taglessCd1;
                public Integer taglessCd2;
                public Integer turnSeq;
                public Integer vehId1;
                public Integer vehId2;
                public Integer predictTimeSec1;
                public Integer predictTimeSec2;
            }
            public List<BusArrivalList> busArrivalList;
        }
        public MsgBody msgBody;
    }
    public Response response;
}