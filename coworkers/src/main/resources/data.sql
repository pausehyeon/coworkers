-- Meeting Room
insert into meeting_room (mid, name, building_name, floor, usable_from, usable_to, first_registered_at, first_registered_by, last_modified_at, last_modified_by) values
(null, '회의실A', 'xx타워 1동', 2, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실B', 'xx타워 1동', 2, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실C', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실D', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실E', 'xx타워 1동', 3, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실F', 'xx타워 2동', 13, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin'),
(null, '회의실G', 'xx타워 2동', 13, TO_DATE('20190101', 'yyyyMMdd'), TO_DATE('99991231', 'yyyyMMdd'), CURRENT_TIMESTAMP(), 'admin', CURRENT_TIMESTAMP(), 'admin');


-- insert into reservation (rid, end, first_registered_at, first_registered_by, first_registered_through, is_repeated, last_modified_at, last_modified_by, last_modified_through, mid, pin, repeat_count, repeat_interval, representative_rid, start, title, user_name)
-- insert into reservation (rid, mid, start, end, pin, is_repeated, repeat_interval, repeat_count, representative_rid, first_registered_at, first_registered_by, first_registered_through, last_modified_at, last_modified_through, last_modified_by)