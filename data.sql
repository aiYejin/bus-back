-- 초기 데이터 삽입

-- 테스트 사용자 데이터
INSERT INTO users (username, email, password, created_at) VALUES 
('김테스트', 'test1@example.com', 'password123', NOW()),
('이사용자', 'test2@example.com', 'password123', NOW()),
('박관리자', 'admin@example.com', 'admin123', NOW());

-- 테스트 즐겨찾기 데이터
INSERT INTO favorites (user_id, type, ref_id, ref_name, additional_info, alias, created_at) VALUES 
(1, 'STOP', '228000379', '강남역', '강남구 강남대로 지하 396', '집 앞 정류장', NOW()),
(1, 'ROUTE', '234000026', '146번', '강남역 ↔ 잠실역', '출근길 버스', NOW()),
(2, 'STOP', '228000719', '역삼역', '강남구 테헤란로 지하 151', NULL, NOW()),
(2, 'ROUTE', '234000881', '421번', '강남역 ↔ 잠실역', NULL, NOW());

-- 테스트 최근 검색 데이터
INSERT INTO recents (user_id, entity_type, ref_id, ref_name, additional_info, viewed_at) VALUES 
(1, 'STOP', '228000379', '강남역', '강남구 강남대로 지하 396', NOW() - INTERVAL '1 hour'),
(1, 'ROUTE', '234000026', '146번', '강남역 ↔ 잠실역', NOW() - INTERVAL '2 hours'),
(1, 'STOP', '228000719', '역삼역', '강남구 테헤란로 지하 151', NOW() - INTERVAL '3 hours'),
(2, 'ROUTE', '234000881', '421번', '강남역 ↔ 잠실역', NOW() - INTERVAL '30 minutes'),
(2, 'STOP', '228000052', '선릉역', '강남구 선릉로 지하 427', NOW() - INTERVAL '1 day');

-- 테스트 알림 데이터
INSERT INTO notifications (user_id, station_id, route_id, station_name, route_name, alert_minutes, start_time, created_at) VALUES 
(1, '228000379', '234000026', '강남역', '146번', 5, NOW() + INTERVAL '1 hour', NOW()),
(1, '228000719', '234000881', '역삼역', '421번', 10, NOW() + INTERVAL '2 hours', NOW()),
(2, '228000052', '234000026', '선릉역', '146번', 3, NOW() + INTERVAL '30 minutes', NOW());

-- 테스트 신고 데이터
INSERT INTO bus_reports (user_id, title, content, email, report_type, created_at) VALUES 
(1, '146번 버스 무정차 신고', '오늘 오전 8시경 강남역에서 146번 버스가 승객이 있음에도 불구하고 정차하지 않고 지나갔습니다.', 'test1@example.com', 'SKIP_STOP', NOW()),
(2, '역삼역 정류장 전광판 고장', '역삼역 2번 출구 앞 정류장의 버스 도착 안내 전광판이 작동하지 않습니다.', 'test2@example.com', 'BROKEN_STATION', NOW()),
(1, '421번 버스 과속 운전', '421번 버스 기사님이 과속으로 운전하여 매우 위험했습니다.', 'test1@example.com', 'DANGEROUS_DRIVING', NOW() - INTERVAL '1 day'),
(3, '146번 버스 지연 운행', '평소보다 20분 이상 늦게 도착하여 불편을 겪었습니다.', 'admin@example.com', 'LATE_BUS', NOW() - INTERVAL '2 days');