-- 사용자 데이터 삽입
INSERT INTO users (id, username, email, password, current_lat, current_lng, location_updated_at, created_at) VALUES 
(101, '홍길동', 'hong@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 37.5665, 126.9780, NOW(), NOW()),
(102, '임꺽정', 'lim@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 37.5665, 126.9780, NOW(), NOW()),
(103, '이순신', 'lee@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 37.5665, 126.9780, NOW(), NOW()),
(104, '김철수', 'kim@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 37.5665, 126.9780, NOW(), NOW()),
(105, '박영희', 'park@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 37.5665, 126.9780, NOW(), NOW());

-- 버스 신고 데이터 삽입
INSERT INTO bus_reports (id, user_id, title, content, email, report_type, created_at) VALUES 
(201, 101, '강남역 정류장 무정차 신고', '오늘 오후 3시경 146번 버스가 강남역 정류장을 무정차로 통과했습니다.', 'hong@example.com', 'SKIP_STOP', NOW()),
(202, 102, '잠실역 정류장 고장 신고', '잠실역 정류장 전광판이 작동하지 않습니다. 확인 부탁드립니다.', 'lim@example.com', 'BROKEN_STATION', NOW()),
(203, 103, '난폭운전 신고', '강남역에서 출발한 146번 버스가 과속으로 운행되고 있습니다.', 'lee@example.com', 'DANGEROUS_DRIVING', NOW()),
(204, 101, '버스 지연 신고', '146번 버스가 30분 이상 지연되고 있습니다.', 'hong@example.com', 'LATE_BUS', NOW()),
(205, 104, '과다혼잡 신고', '강남역에서 출발한 146번 버스가 너무 혼잡합니다.', 'kim@example.com', 'CROWDED_BUS', NOW());

-- 즐겨찾기 데이터 삽입
INSERT INTO favorites (id, ref_name, additional_info, user_id, type, ref_id, alias, created_at) VALUES 
(301, '146번', '강남역 ↔ 잠실역', 101, 'ROUTE', '146', '집가는 버스', NOW()),
(302, '강남역', '02301', 101, 'STOP', '02301', '회사 앞', NOW()),
(303, '잠실역', '02302', 102, 'STOP', '02302', '집 앞', NOW()),
(304, '146번', '강남역 ↔ 잠실역', 102, 'ROUTE', '146', '출근 버스', NOW()),
(305, '강남역', '02301', 103, 'STOP', '02301', '주요 환승역', NOW()),
(306, '360번', '강남역 ↔ 홍대입구', 104, 'ROUTE', '360', '놀러가는 버스', NOW()),
(307, '홍대입구', '02303', 104, 'STOP', '02303', '놀이터', NOW()),
(308, '잠실역', '02302', 105, 'STOP', '02302', '쇼핑', NOW());

-- 알림 설정 데이터 삽입
INSERT INTO notifications (id, user_id, station_id, route_id, alert_minutes, start_time, created_at) VALUES 
(401, 101, '02301', '146', 5, NOW(), NOW()),
(402, 101, '02302', '146', 3, NOW(), NOW()),
(403, 102, '02302', NULL, 10, NOW(), NOW()),
(404, 103, '02301', '146', 7, NOW(), NOW()),
(405, 104, '02303', '360', 5, NOW(), NOW()),
(406, 105, '02302', NULL, 15, NOW(), NOW());

-- 최근 조회 데이터 삽입
INSERT INTO recents (id, user_id, entity_type, ref_id, ref_name, additional_info, viewed_at) VALUES 
(501, 101, 'ROUTE', '146', '146번', '강남역 ↔ 잠실역', NOW() - INTERVAL '1 hour'),
(502, 101, 'STOP', '02301', '강남역', '02301', NOW() - INTERVAL '30 minutes'),
(503, 102, 'STOP', '02302', '잠실역', '02302', NOW() - INTERVAL '2 hours'),
(504, 102, 'ROUTE', '146', '146번', '강남역 ↔ 잠실역', NOW() - INTERVAL '1 hour'),
(505, 103, 'STOP', '02301', '강남역', '02301', NOW() - INTERVAL '45 minutes'),
(506, 104, 'ROUTE', '360', '360번', '강남역 ↔ 홍대입구', NOW() - INTERVAL '3 hours'),
(507, 104, 'STOP', '02303', '홍대입구', '02303', NOW() - INTERVAL '2 hours'),
(508, 105, 'STOP', '02302', '잠실역', '02302', NOW() - INTERVAL '1 hour');

-- 시퀀스 값 업데이트 (다음 INSERT 시 올바른 ID가 생성되도록)
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users), true);
SELECT setval('bus_reports_id_seq', (SELECT MAX(id) FROM bus_reports), true);
SELECT setval('favorites_id_seq', (SELECT MAX(id) FROM favorites), true);
SELECT setval('notifications_id_seq', (SELECT MAX(id) FROM notifications), true);
SELECT setval('recents_id_seq', (SELECT MAX(id) FROM recents), true);