package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationAroundRes {
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
            public static class BusStationAroundList {
                public String centerYn;         // 중앙차로 여부 (N:일반,Y:중앙차로)
                public String mobileNo;         // 정류소번호
                public String regionName;       // 정류소 위치 지역명
                public Integer stationId;       // 정류소아이디
                public String stationName;      // 정류소명
                public Double x;                // 정류소 X좌표
                public Double y;                // 정류소 Y좌표
                public Integer distance;        // 거리(m)
            }
            public java.util.List<BusStationAroundList> busStationAroundList;  // 수정된 부분
        }
        public MsgBody msgBody;
    }
    public Response response;
}