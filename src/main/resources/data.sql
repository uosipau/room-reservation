BEGIN;

-- insert buildings
INSERT INTO buildings (id, address)
VALUES (nextval('buildings_id_seq'), 'Minsk, Centralnaya street, 20');
INSERT INTO buildings (id, address)
VALUES (nextval('buildings_id_seq'), 'Minsk, Vostochnaya street, 118');
INSERT INTO buildings (id, address)
VALUES (nextval('buildings_id_seq'), 'Brest, Nezavisimosty street, 2');

--insert rooms
-- building 1, floor 4
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 1, 4, 8, true);
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 1, 4, 12, false);
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 1, 4, 5, false);

-- building 1, floor 3
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 1, 3, 6, true);

-- building 2, floor 1
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 2, 1, 150, true);

-- building 3, floor 3
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 3, 3, 13, true);
INSERT INTO rooms (id, building_id, floor, max_allocation, multimedia)
VALUES (nextval('rooms_id_seq'), 3, 3, 5, true);

-- insert reservations
-- 2022-03-20 room-1 10-11, 12:30-14:00
-- 2022-03-20 room-2 9:30-11, 11 - 12, 16-16:30
-- 2022-03-20 room-4 15-16
-- 2022-03-20 room-5 15:30-16:30
-- 2022-03-20 room-6 11:30-13:30, 15:30-16:00

-- 2022-03-21 room-7 10-12, 14-14:30
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 1, '2022-03-20 10:00', '2022-03-20 11:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 1, '2022-03-20 12:30', '2022-03-20 14:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 2, '2022-03-20 09:30', '2022-03-20 11:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 2, '2022-03-20 11:00', '2022-03-20 12:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 2, '2022-03-20 16:00', '2022-03-20 16:30');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 4, '2022-03-20 15:00', '2022-03-20 16:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 5, '2022-03-20 15:30', '2022-03-20 16:30');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 6, '2022-03-20 11:30', '2022-03-20 13:30');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 6, '2022-03-20 15:30', '2022-03-20 16:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 7, '2022-03-21 10:00', '2022-03-21 12:00');
INSERT INTO reservations (id, room_id, start_datetime, end_datetime)
VALUES (nextval('reservations_id_seq'), 7, '2022-03-21 14:00', '2022-03-21 14:30');

COMMIT;