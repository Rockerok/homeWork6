BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('milk', 10),
('bread', 20),
('butter', 100),
('cheese', 50),
('coca-cola', 140);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO users (name) VALUES
('user1'),
('user2'),
('user3');

DROP TABLE IF EXISTS sales CASCADE;
CREATE TABLE sales (id bigserial PRIMARY KEY, date_sales date,  users_id bigint REFERENCES users (id), product_id bigint REFERENCES products (id),price_prod int);
INSERT INTO sales (date_sales,users_id,product_id,price_prod) VALUES
('2022-05-01',1,1,10),
('2022-05-02',1,2,20),
('2022-05-01',1,3,100),
('2022-05-01',2,1,10),
('2022-05-01',3,1,10),
('2022-05-03',2,2,30),
('2022-05-02',2,3,100),
('2022-05-03',3,4,50);


COMMIT;