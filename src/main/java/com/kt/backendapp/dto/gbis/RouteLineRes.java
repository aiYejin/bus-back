package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteLineRes {
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
            public static class BusRouteLineList {
                public Integer lineSeq;    // 형상 좌표 순서
                public Double x;           // X좌표
                public Double y;           // Y좌표
            }
            public List<BusRouteLineList> busRouteLineList;
        }
        public MsgBody msgBody;
    }
    public Response response;
}
