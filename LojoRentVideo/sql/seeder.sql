# in the connection class DBConnection package
# there is a seeder () method you better run that before this script 
# as the dates would be inserted updated
use videoclub;
INSERT INTO users (nick, password, name,  surname, mail, saldo, cliente_premium) VALUES
	('Juanito_1998', 'pestillo01', 'Juan', 'Álvarez', 'juanito@juanito.com', 1000, true),
    ('Marisa_0010', 'pestillo_01', 'Marisa', 'Rodríguez','marisa@gmail.com',250, false),
    ('Elena_34', 'pestillo_01', 'Elena', 'Lancho', 'elenica@gmail.com', 20, true);

INSERT INTO films(cod_film, name_film, price, estreno, n_copias, genero) VALUES
	(01, 'Mátrix', 1, false, 5, 'accion'),
    (02, 'Harry Potter', 1, false, 10, 'fantasticas'),
    (03, 'SpiderMan',1, false, 12, 'accion'),
    (04, 'Alone in the dark', 2, true, 12, 'terror'),
    (05, 'Mr Robot', 2, true, 15, 'intriga'),
    (06, 'Love', 1, false, 20, 'romantica'),
	(07, 'Doctor who', 1, true, 7, 'misterio'),
	(08, 'Fast and furius', 1, false, 3, 'conduccion'),
	(09, 'Star Wars', 2, true, 10, 'misterio');
    
INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Juanito_1998', 01, '12-02-2021');
INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Juanito_1998', 03, '11-02-2021');
INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Marisa_0010', 09, '11-02-2021');
INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Elena_34', 01, '09-02-2021');
INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Elena_34', 06, '09-02-2021');

 