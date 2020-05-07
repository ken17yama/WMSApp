INSERT INTO users (user_name, mail, password, birthday, role, my_place) VALUES('やまだ けんと', 'kento@yamada', '$2a$10$qi3w.mxGwE8I4SxJz2V0q.SR51miCbAkFaeRuMszUrl84DTcKOgIq', '1997-01-07', 'ROLE_ADMIN', '1,2');
INSERT INTO users (user_name, mail, password, birthday, role, my_place) VALUES('やまざき いづみ', 'izumi@yamazaki', '$2a$10$QZLsVTyYd2r/nYY9yEi7jOGRooD1Pee6E0BDkea7Cx7n7F69Btwpa', '1988-07-30', 'ROLE_GENERAL', '1');
INSERT INTO users (user_name, mail, password, birthday, role, my_place) VALUES('やまだ ちくわ', 'chikuwa@yamada', '$2a$10$qi3w.mxGwE8I4SxJz2V0q.SR51miCbAkFaeRuMszUrl84DTcKOgIq', '2020-05-01', 'ROLE_GENERAL', '2');


INSERT INTO groups (group_name, created_at, updated_at) VALUES('やまだの冷蔵庫', '2020-04-30 01:23:45', '2020-04-30 01:23:45');
INSERT INTO groups (group_name, created_at, updated_at) VALUES('近藤の冷蔵庫', '2020-04-30 01:23:45', '2020-04-30 01:23:45');


INSERT INTO stocks (stock_name, quantity, unit, place, created_at, updated_at) VALUES('塩', 300, 'g', 1, '2020-04-30 01:23:45', '2020-04-30 01:23:45');
INSERT INTO stocks (stock_name, quantity, unit, place, created_at, updated_at) VALUES('トマト', 2, '個', 1, '2020-04-30 01:23:45', '2020-04-30 01:23:45');
INSERT INTO stocks (stock_name, quantity, unit, place, created_at, updated_at) VALUES('トマト', 3, '個', 2, '2020-04-30 01:23:45', '2020-04-30 01:23:45');
INSERT INTO stocks (stock_name, quantity, unit, place, created_at, updated_at) VALUES('ピーマン', 2, '個', 1, '2020-04-30 01:23:45', '2020-04-30 01:23:45');