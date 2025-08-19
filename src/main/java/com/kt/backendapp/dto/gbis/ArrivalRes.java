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
                public String routeId;
                public String routeName;
                public String stationId;
                public Integer predictTime1;
                public Integer locationNo1;
                public String plateNo1;
            }
            public List<BusArrivalList> busArrivalList;
        }
        public MsgBody msgBody;
    }
    public Response response;
}