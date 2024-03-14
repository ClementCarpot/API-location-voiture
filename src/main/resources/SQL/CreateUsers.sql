-- Insertion des clients
INSERT INTO utilisateurs (dtype, prenom, nom, email, mot_de_passe, code_postal, ville, rue, date_naissance, date_inscription, permis,  desactive, role)
VALUES  ('Client', 'Harry', 'Potter', 'harry.potter@hogwarts.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', '80808', 'Poudlard', 'Chemin de Traverse', '1993-07-31', '2020-01-01', '{B, C}', 'false', 'CLIENT'),
        ('Client', 'Hermione', 'Granger', 'hermione.granger@hogwarts.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', '80808', 'Poudlard', 'Chemin de Traverse', '1993-09-19', '2020-01-01', '{B}', 'false', 'CLIENT'),
        ('Client', 'Ron', 'Weasley', 'ron.weasley@hogwarts.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', '80808', 'Poudlard', 'Chemin de Traverse', '1993-03-01', '2020-01-01', '{C}', 'false', 'CLIENT');


-- Insertion des administrateurs
INSERT INTO utilisateurs (dtype, prenom, nom, email, mot_de_passe, fonction, desactive, role)
VALUES ('Administrateur', 'Albus', 'Dumbledore', 'albus.dumbledore@hogwart.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', 'Directeur', 'false', 'ADMIN'),
       ('Administrateur', 'Severus', 'Rogue', 'severus.rogue@hogwart.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', 'Professeur de potion', 'false', 'ADMIN'),
       ('Administrateur', 'Minerva', 'McGonagall', 'minerva.mcgonagall@hogwart.com', '{bcrypt}$2a$10$v8JZpSAlE3AloKq1K33uwO5/SkgCJeFUIgIHGjmfynIDRN26vMhFy', 'Professeur de métamorphose', 'false', 'ADMIN');
