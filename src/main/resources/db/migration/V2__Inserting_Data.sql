INSERT INTO city (name) VALUES ('Budapest');
INSERT INTO city (name) VALUES ('Eger');
INSERT INTO city (name) VALUES ('Sülysáp');
INSERT INTO city (name) VALUES ('Miskolc');
INSERT INTO city (name) VALUES ('Balkány');

INSERT INTO color (name) VALUES ('Yellow');
INSERT INTO color (name) VALUES ('Red');
INSERT INTO color (name) VALUES ('Black');
INSERT INTO color (name) VALUES ('Grey');
INSERT INTO color (name) VALUES ('Blue');

INSERT INTO manufacturer (name) VALUES ('Opel');
INSERT INTO manufacturer (name) VALUES ('Skoda');
INSERT INTO manufacturer (name) VALUES ('BMW');
INSERT INTO manufacturer (name) VALUES ('Mercedes');
INSERT INTO manufacturer (name) VALUES ('Nissan');

INSERT INTO company_car (licence_plate_number, color_id, manufacturer_id, model, in_use_since, repair_required)
VALUES ('IIL-215', 4, 1, 'Corsa', '1995-07-12', false);
INSERT INTO company_car (licence_plate_number, color_id, manufacturer_id, model, in_use_since, repair_required)
VALUES ('PRS-689', 5, 2, 'SuperB', '2018-12-11', false);
INSERT INTO company_car (licence_plate_number, color_id, manufacturer_id, model, in_use_since, repair_required)
VALUES ('GJH-333', 4, 5, 'Juke', '2008-01-02', false);
INSERT INTO company_car (licence_plate_number, color_id, manufacturer_id, model, in_use_since, repair_required)
VALUES ('FPP-187', 2, 3, 'X3', '2011-04-23', true);
INSERT INTO company_car (licence_plate_number, color_id, manufacturer_id, model, in_use_since, repair_required)
VALUES ('RRS-964', 3, 4, 'EQC', '2020-06-14', false);

INSERT INTO driver (first_name, last_name, birth_date, city_id, company_car_id)
VALUES ('Eszter Enikő', 'Biró-Kányási', '1993-09-17', 4, 1);
INSERT INTO driver (first_name, last_name, birth_date, city_id, company_car_id)
VALUES ('Zsolt', 'Zemen', '1996-06-18', 3, 5);
INSERT INTO driver (first_name, last_name, birth_date, city_id, company_car_id)
VALUES ('Marcell', 'Krausz', '1993-11-11', 1, 4);