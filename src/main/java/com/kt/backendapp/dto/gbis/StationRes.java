package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class StationRes {
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
            public static class BusStationList {
                public String centerYn;
                public String mobileNo;
                public String regionName;
                public Integer stationId;
                public String stationName;
                public Double x;
                public Double y;
            }
            public List<BusStationList> busStationList;
        }
        public MsgBody msgBody;
    }
    public Response response;
}