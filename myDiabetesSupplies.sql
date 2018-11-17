
CREATE DATABASE IF NOT EXISTS myDiabetesSupplies;

USE myDiabetesSupplies;

CREATE TABLE IF NOT EXISTS user(
	name VARCHAR(30) NOT NULL,
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(50) NOT NULL
);

INSERT INTO user (name, username, password)
VALUES ('Luca', 'Lollators', 'test');

CREATE TABLE IF NOT EXISTS product(
	serialNumber VARCHAR(255) NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	expDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS product_user(
	username VARCHAR(50) NOT NULL,
	serialNumber VARCHAR(255) NOT NULL,
	PRIMARY KEY (username, serialNumber),
	KEY pkey (serialNumber),
	FOREIGN KEY(username) REFERENCES user(username)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY(serialNumber) REFERENCES product(serialNumber)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

INSERT INTO product (serialNumber, name, expDate) VALUES 
('LN000001', 'Contour Link Lancet', '2019-03-11'),
('LN000002', 'Contour Link Lancet', '2019-03-11'),
('LN000003', 'Contour Link Lancet', '2019-03-11'),
('LN000004', 'Contour Link Lancet', '2019-07-11'), 
('LN000005', 'Contour Link Lancet', '2019-05-11'),
('TS000001', 'Contour Link Test Strip', '2019-03-11'),
('TS000002', 'Contour Link Test Strip', '2019-11-11'),
('TS000003', 'Contour Link Test Strip', '2019-03-11'),
('ST000001', 'Medtronic Insertion Site', '2019-07-11'),
('ST000002', 'Medtronic Insertion Site', '2019-07-11'),
('ST000003', 'Medtronic Insertion Site', '2019-07-11'),
('TN000001', 'Medtronic Tank 300ml', '2019-07-11'),
('TN000002', 'Medtronic Tank 300ml', '2019-07-11'),
('TN000003', 'Medtronic Tank 300ml', '2019-08-11'),
('IN000001', 'NovoLog NovoRapid Insulin 100UI/ml', '2019-07-11'),
('IN000002', 'NovoLog NovoRapid Insulin 100UI/ml', '2019-12-11'),
('IN000003', 'NovoLog NovoRapid Insulin 100UI/ml', '2019-07-11');

INSERT INTO product_user (username, serialNumber) VALUES
('Lollators', 'LN000001'),
('Lollators', 'LN000002'),
('Lollators', 'LN000003'),
('Lollators', 'TS000001'),
('Lollators', 'TS000002'),
('Lollators', 'TS000003'),
('Lollators', 'ST000001'),
('Lollators', 'ST000002'),
('Lollators', 'ST000003'),
('Lollators', 'TN000001'),
('Lollators', 'TN000002'),
('Lollators', 'TN000003'),
('Lollators', 'IN000001'),
('Lollators', 'IN000002'),
('Lollators', 'IN000003');

