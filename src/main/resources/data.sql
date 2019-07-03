--populate currencies table
INSERT INTO currencies(id, description, iso_name) VALUES
  (1,'Pound Sterling', 'GBP'),
  (2,'US Dollar', 'USD');

--populate currencies_annotations table
INSERT INTO currency_annotations(id, rate, currency_one_id, currency_two_id) VALUES
(1, 1.20, 1, 2);

--populate order_status table
INSERT INTO order_status(id, name) VALUES
(1,'new'),
(2,'cancel'),
(3,'matched');

--populate order_types table
INSERT INTO order_types(id, name) VALUES
(1,'bid'),
(2,'ask');

--populate users table
INSERT INTO users(id, name, updated_at) VALUES
(1,'testUser1','2018-01-02'),
(2,'testUser2','2018-01-03');


--populate orders table
INSERT INTO orders(id, amount, created_at, updated_at, currency_annotation_id, order_status_id, order_type_id, user_id)
 VALUES
(1, 10, '2018-01-01','2018-02-01',1,3,1,1),
(2, 12, '2018-02-01','2018-02-01',1,3,2,2),
(3, 20, '2018-02-02','2018-02-02',1,1,1,1),
(4, 20, '2018-02-03','2018-02-03',1,1,2,2);