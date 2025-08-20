package com.kt.backendapp.entity;

/**
 * 신고 유형 enum
 */
public enum ReportType {
    SKIP_STOP,      // 무정차
    BROKEN_STATION, // 정류장 고장
    DANGEROUS_DRIVING, // 난폭운전
    LATE_BUS,       // 지연버스
    CROWDED_BUS,    // 과다혼잡
    OTHER           // 기타
}