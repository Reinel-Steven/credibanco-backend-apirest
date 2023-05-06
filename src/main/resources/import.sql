INSERT INTO card (pan, cardholder, cedula, type, phone_number, enable, validate_number, created, modified) VALUES ('12345678910111213', 'Reinel Bolaños', '1013623647', 'Debito', '3184090901', 1, '95', '2023-05-06 09:46:23', '2023-05-05 09:46:23' );
INSERT INTO card (pan, cardholder, cedula, type, phone_number, enable, validate_number, created, modified) VALUES ('12345678910222213', 'Reinel Martinez', '1013623648', 'Credito', '3184090902', 1, '55', '2023-05-06 08:46:23', '2023-05-05 08:46:23' );
INSERT INTO card (pan, cardholder, cedula, type, phone_number, enable, validate_number, created, modified) VALUES ('12345678910333213', 'Steven Bolaños', '1013623649', 'Credito', '3184090903', 1, '15', '2023-05-06 10:46:23', '2023-05-05 10:46:23' );
INSERT INTO card (pan, cardholder, cedula, type, phone_number, enable, validate_number, created, modified) VALUES ('12345678910444213', 'Heider Bolaños', '1013623641', 'Debito', '3184090904', 1, '100', '2023-05-06 11:46:23', '2023-05-05 11:46:23' );



INSERT INTO transaction_card (ref, total_pay, adress_pay, approved, created, modified, card_id) VALUES ('123456', '51200', 'alguna direccion4', 1, '2023-05-06 08:46:23', '2023-05-05 08:46:23',1);
INSERT INTO transaction_card (ref, total_pay, adress_pay, approved, created, modified, card_id) VALUES ('123457', '55200', 'alguna direccion3', 1, '2023-05-06 08:46:23', '2023-05-05 08:46:23',2);
INSERT INTO transaction_card (ref, total_pay, adress_pay, approved, created, modified, card_id) VALUES ('123458', '56200', 'alguna direccion2', 1, '2023-05-06 08:46:23', '2023-05-05 08:46:23',3);
INSERT INTO transaction_card (ref, total_pay, adress_pay, approved, created, modified, card_id) VALUES ('123459', '57200', 'alguna direccion1', 1, '2023-05-06 08:46:23', '2023-05-05 08:46:23',4);