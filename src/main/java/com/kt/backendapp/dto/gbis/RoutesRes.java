package com.kt.backendapp.dto.gbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * GBIS 노선 검색 API 응답 DTO
 * JSON 응답을 Java 객체로 매핑
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class RoutesRes {
    @JsonIgnoreProperties(ignoreUnknown = true) 
    public static class Response {
        public String comMsgHeader; // 공통 메시지 헤더
        
        @JsonIgnoreProperties(ignoreUnknown = true) 
        public static class MsgHeader {
            public String queryTime;     // 쿼리 실행 시간
            public Integer resultCode;   // 결과 코드 (0: 성공)
            public String resultMessage; // 결과 메시지
        }
        public MsgHeader msgHeader; // 메시지 헤더
        
        @JsonIgnoreProperties(ignoreUnknown = true) 
        public static class MsgBody {
            @JsonIgnoreProperties(ignoreUnknown = true) 
            public static class BusRouteList {
                public String busRouteList;      // 노선번호목록
                public String startStationName;  // 기점정류소명
                public String adminName;         // 인면허기관명
                public Integer districtCd;       // 관할지역코드 (1:서울,2:경기,3:인천)
                public Integer endStationId;     // 종점정류소아이디
                public String endStationName;    // 종점정류소명
                public String regionName;        // 운행지역
                public Integer routeId;          // 노선아이디
                public String routeName;         // 노선번호
                public Integer routeTypeCd;      // 노선유형코드
                public String routeTypeName;     // 노선유형명
                public Integer startStationId;   // 기점정류소아이디
            }
            public List<BusRouteList> busRouteList; // 노선 목록
        }
        public MsgBody msgBody; // 메시지 본문
    }
    public Response response; // 최상위 응답 객체
}
