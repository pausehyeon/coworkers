-- Meeting Room
insert into meeting_room (mid, name, building_name, floor, usable_from, usable_to, first_registered_at, first_registered_by, last_modified_at, last_modified_by) values
(null, '회의실A', 'xx타워 1동', 2, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실B', 'xx타워 1동', 2, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실C', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실D', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실E', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실F', 'xx타워 2동', 13, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실G', 'xx타워 2동', 13, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin');

-- Response Code
insert into response_code (code, http_code, message) values
('S001', '200', '정상 처리되었습니다.');