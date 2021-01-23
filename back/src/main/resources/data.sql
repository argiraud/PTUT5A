INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('COMPANY');
INSERT INTO role (name) VALUES ('ADMIN');

INSERT INTO company (company_id) VALUES ('830c6b79-1855-4232-b581-bd5197a9f10b');
INSERT INTO "user" (user_id, birth_date, etudiant_number, first_name, status) VALUES ('6c2f7f77-ad13-4a81-aa18-a2fe2eeedc5d', '2020-11-10', '00000000', 'user', 'OK');
INSERT INTO "user" (user_id, birth_date, etudiant_number, first_name, status) VALUES ('8c2d7b7d-28d4-4166-bc75-23ac5ec35d4b', '2020-11-09', '00000001', 'admin', 'OK');

INSERT INTO entity (email, enable, name, password, company_company_id) VALUES ('company@company.fr', true, 'company', '$2a$10$TMEN.SNQ4k9ep9ykxidW6eF5/cDkkYnltOhFZsu3NhyPZmN1EOgxG', '830c6b79-1855-4232-b581-bd5197a9f10b');
INSERT INTO entity (email, enable, name, password, user_user_id) VALUES ('user@user.fr', true, 'user', '$2a$10$LQiX1Kk0UAW50HZbDcWmKev8jyTDFjHKpZCK9Cwcai308elqjl712', '6c2f7f77-ad13-4a81-aa18-a2fe2eeedc5d');
INSERT INTO entity (email, enable, name, password, user_user_id) VALUES ('admin@admin.fr', true, 'admin', '$2a$10$P0WHcZjSIzgAt6Q2wVucH.hFy8qKfZCnnpFLJRgeB8.5rn.dV0yUS', '8c2d7b7d-28d4-4166-bc75-23ac5ec35d4b');

INSERT INTO entity_role (entity_id, role_id) VALUES (1, 2);
INSERT INTO entity_role (entity_id, role_id) VALUES (2, 1);
INSERT INTO entity_role (entity_id, role_id) VALUES (3, 1);
INSERT INTO entity_role (entity_id, role_id) VALUES (3, 3);

