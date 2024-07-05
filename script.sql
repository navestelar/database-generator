CREATE DATABASE IF NOT EXISTS teste;
USE teste;
CREATE TABLE IF NOT EXISTS tabela1 (id INT, campo1 VARCHAR(20), campo2 VARCHAR(23), campo3 CHAR(2), pk1 VARCHAR(20), PRIMARY KEY (pk1));
CREATE TABLE IF NOT EXISTS tabela2 (campo4 CHAR(2), pk2 INT, PRIMARY KEY (pk2));


CREATE TABLE IF NOT EXISTS tabela1_tabela2 (
tabela1_pk1 VARCHAR(20), 
tabela2_pk2 INT, 
FOREIGN KEY (tabela1_pk1) REFERENCES tabela1 (pk1),
FOREIGN KEY (tabela2_pk2) REFERENCES tabela2 (pk2),
PRIMARY KEY (tabela1_pk1, tabela2_pk2)
);