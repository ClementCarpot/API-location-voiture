-- Insertion dans la table VEHICULES
INSERT INTO VEHICULES (id, marque, modele, couleur, tarif_journalier, kilometrage, actif, retire_du_parc)
VALUES  (1,'Toyota', 'Corolla', 'Rouge', 50.00, 45000, 'false', 'false'),
        (2,'Honda', 'Civic', 'Bleu', 45.00, 60000, 'false', 'false'),
        (3, 'Tesla', 'Model S', 'Blanche', 90.00, 35000, 'false', 'false'),
        (4, 'Yamaha', 'YZF-R1', 'Noire', 70.00, 2000, 'false', 'false'),
        (5, 'Kawasaki', 'Ninja ZX-10R', 'Verte', 65.00, 4500, 'false', 'false'),
        (6, 'Ducati', 'Panigale V4', 'Rouge', 80.00, 1600, 'false', 'false'),
        (7, 'Fiat', 'Ducato', 'Blanc', 100.00, 34000, 'false', 'false'),
        (8, 'Mercedes', 'Sprinter', 'Gris', 120.00, 70000, 'false', 'false'),
        (9, 'Volkswagen', 'California', 'Bleu', 110.00, 80000, 'false', 'false'),
        (10, 'Orbea', 'Ordu', 'Noir', 20.00, 500, 'false', 'false'),
        (11, 'Rockrider', 'E-EXPL 520', 'Jaune', 25.00, 3500, 'false', 'false'),
        (12, 'Canyon', 'Speedmax CFR 8', 'Noir', 45.00, 2400, 'false', 'false'),
        (13, 'Renault', 'Kangoo', 'Partner', 50.00, 110000, 'false', 'false'),
        (14, 'Renault', 'Master', 'Blanc', 60.00, 120000, 'false', 'false'),
        (15, 'Renault', 'Maxity', 'Blanc', 120.00, 13000, 'false', 'false');

-- Insertion dans la table VOITURES
INSERT INTO VOITURES (id, type_energie, nombre_portes, transmission, climatisation, nombre_places, nombre_bagages, type_voiture, permis_necessaire)
VALUES  (1, 'ESSENCE', 4, 'AUTOMATIQUE', 'true', 5, 4, 'BERLINE', 'B'),
        (2, 'ESSENCE', 4, 'MANUELLE', 'true', 5, 4, 'CITADINE', 'B'),
        (3, 'ELECTRIQUE', 4, 'AUTOMATIQUE', 'true', 5, 4, 'BERLINE', 'B');

-- Insertion dans la table MOTOS
INSERT INTO MOTOS (id, cylindree, hauteur_selle, nombre_de_cylindres, poids, puissancekw, permis_necessaire, transmission, type_moto)
VALUES  (4, 1000, 820, 4, 200, 147, 'A', 'MANUELLE', 'SPORTIVE'),
        (5, 1000, 820, 4, 200, 147, 'A', 'MANUELLE', 'SPORTIVE'),
        (6, 1100, 830, 4, 210, 152, 'A', 'MANUELLE', 'SPORTIVE');

-- Insertion dans la table CAMPINGS_CARS
INSERT INTO CAMPINGS_CARS (id, nombre_places, type_energie, transmission, climatisation, poidspatc, hauteur, nombre_couchages, equipement_cuisine, linge_lit, equipement_refregirateur, equipement_douche, type_camping_car, permis_necessaire)
VALUES  (7, 4, 'DIESEL', 'MANUELLE', 'true', 3500, 2.58, 4, 'true', 'true', 'true', 'true', 'FOURGON', 'C1'),
        (8, 2, 'DIESEL', 'MANUELLE', 'true', 3000, 2.76, 2, 'true', 'true', 'true', 'false', 'CAPUCINE', 'B'),
        (9, 4, 'DIESEL', 'MANUELLE', 'true', 3200, 2.00, 4, 'true', 'true', 'true', 'true', 'INTEGRAL', 'B');

-- Insertion dans la table VELOS
INSERT INTO VELOS (id, taille_cadre, poids_kg, is_electrique, autonomie_km, capacite_kwh, freins_disque, type_velo)
VALUES (10, 56, 8, 'false', 0, 0, 'false', 'ROUTE'),
       (11, 54, 24, 'true', 44, 400, 'true', 'VTT'),
       (12, 58, 9, 'false', 0, 0, 'true', 'TRIATHLON');

-- Insertion dans la table UTILITAIRES
INSERT INTO UTILITAIRES (id, nombre_places, type_energie, transmission, climatisation, charge_max, poidspatc, capacite_metre_cube, type_utilitaire, permis_necessaire)
VALUES (13, 3, 'DIESEL', 'MANUELLE', 'true', 1000, 3500, 8, 'FOURGON', 'B'),
       (14, 3, 'ESSENCE', 'AUTOMATIQUE', 'true', 4000, 5500, 8, 'CAMION', 'C1'),
       (15, 3, 'ESSENCE', 'MANUELLE', 'true', 2000, 4500, 8, 'CAMION_NACELLE', 'C1');