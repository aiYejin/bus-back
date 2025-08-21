-- 테이블 삭제 (기존 테이블이 있다면)
DROP TABLE IF EXISTS recents CASCADE;
DROP TABLE IF EXISTS notifications CASCADE;
DROP TABLE IF EXISTS favorites CASCADE;
DROP TABLE IF EXISTS bus_reports CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- 사용자 테이블 생성
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    current_lat DOUBLE PRECISION,
    current_lng DOUBLE PRECISION,
    location_updated_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- 사용자 테이블 인덱스 생성
CREATE UNIQUE INDEX ux_users_email ON users (email);

-- 버스 신고 테이블 생성
CREATE TABLE bus_reports (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    email VARCHAR(100) NOT NULL,
    report_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 즐겨찾기 테이블 생성
CREATE TABLE favorites (
    id BIGSERIAL PRIMARY KEY,
    ref_name VARCHAR(100) NOT NULL,
    additional_info VARCHAR(200),
    user_id BIGINT NOT NULL,
    type VARCHAR(10) NOT NULL,
    ref_id VARCHAR(64) NOT NULL,
    alias VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 즐겨찾기 테이블 제약조건 및 인덱스 생성
CREATE UNIQUE INDEX ux_fav_user_type_ref ON favorites (user_id, type, ref_id);
CREATE INDEX ix_fav_user ON favorites (user_id);
CREATE INDEX ix_fav_user_created ON favorites (user_id, created_at);
CREATE INDEX ix_fav_user_type_created ON favorites (user_id, type, created_at);

-- 알림 테이블 생성
CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    station_id VARCHAR(64) NOT NULL,
    route_id VARCHAR(64),
    alert_minutes INTEGER NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 알림 테이블 인덱스 생성
CREATE INDEX ix_notif_user ON notifications (user_id);
CREATE INDEX ix_notif_user_station ON notifications (user_id, station_id);

-- 최근 조회 테이블 생성
CREATE TABLE recents (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    entity_type VARCHAR(10) NOT NULL,
    ref_id VARCHAR(64) NOT NULL,
    ref_name VARCHAR(100) NOT NULL,
    additional_info VARCHAR(200),
    viewed_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 최근 조회 테이블 인덱스 생성
CREATE INDEX ix_recent_user ON recents (user_id);
CREATE INDEX ix_recent_user_type_time ON recents (user_id, entity_type, viewed_at);