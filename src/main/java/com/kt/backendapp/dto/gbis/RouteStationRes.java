package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStationRes {
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
            public static class BusRouteStationList {
                public String busRouteStationList;  // 정류소목록
                public String centerYn;             // 중앙차로 여부 (N:일반,Y:중앙차로)
                public String turnYn;               // 회차점여부
                public Integer districtCd;          // 노선의 관할지역코드 (1:서울,2:경기,3:인천)
                public Integer mobileNo;            // 정류소번호
                public String regionName;           // 정류소 위치 지역명
                public Integer stationId;           // 정류소아이디
                public String stationName;          // 정류소명
                public Double x;                    // 정류소 X좌표
                public Double y;                    // 정류소 Y좌표
                public String adminName;            // 정류소 관할기관
                public Integer stationSeq;          // 노선의 정류소순번
                public Integer turnSeq;             // 회차점순번
            }
            public List<BusRouteStationList> busRouteStationList;
        }
        public MsgBody msgBody;
    }
    public Response response;
}