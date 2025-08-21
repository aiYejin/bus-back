-- 데이터베이스 스키마 생성 (PostgreSQL)

-- 사용자 테이블
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

-- 사용자 이메일 유니크 인덱스
CREATE UNIQUE INDEX ux_users_email ON users (email);

-- 버스 신고 테이블
CREATE TABLE bus_reports (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    email VARCHAR(100) NOT NULL,
    report_type VARCHAR(50) NOT NULL CHECK (report_type IN ('SKIP_STOP', 'BROKEN_STATION', 'DANGEROUS_DRIVING', 'LATE_BUS', 'CROWDED_BUS', 'OTHER')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 즐겨찾기 테이블
CREATE TABLE favorites (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('ROUTE', 'STOP')),
    ref_id VARCHAR(64) NOT NULL,
    ref_name VARCHAR(100) NOT NULL,
    additional_info VARCHAR(200),
    alias VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 즐겨찾기 유니크 제약조건
ALTER TABLE favorites ADD CONSTRAINT ux_fav_user_type_ref UNIQUE (user_id, type, ref_id);

-- 즐겨찾기 인덱스
CREATE INDEX ix_fav_user ON favorites (user_id);
CREATE INDEX ix_fav_user_created ON favorites (user_id, created_at);
CREATE INDEX ix_fav_user_type_created ON favorites (user_id, type, created_at);

-- 최근 검색 테이블
CREATE TABLE recents (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    entity_type VARCHAR(10) NOT NULL CHECK (entity_type IN ('ROUTE', 'STOP')),
    ref_id VARCHAR(64) NOT NULL,
    ref_name VARCHAR(100) NOT NULL,
    additional_info VARCHAR(200),
    viewed_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 최근 검색 인덱스
CREATE INDEX ix_recent_user ON recents (user_id);
CREATE INDEX ix_recent_user_type_time ON recents (user_id, entity_type, viewed_at);

-- 알림 테이블
CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    station_id VARCHAR(64) NOT NULL,
    route_id VARCHAR(64),
    station_name VARCHAR(128),
    route_name VARCHAR(64),
    alert_minutes INTEGER NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 알림 인덱스
CREATE INDEX ix_notif_user ON notifications (user_id);
CREATE INDEX ix_notif_user_station ON notifications (user_id, station_id);