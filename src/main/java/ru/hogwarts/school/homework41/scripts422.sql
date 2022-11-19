CREATE TABLE IF NOT EXISTS cars
(
    id SMALLSERIAL PRIMARY KEY,
    brand VARCHAR(32) NOT NULL,
    model VARCHAR(32) NOT NULL,
    price NUMERIC CHECK ( cars.price > 0 ) NOT NULL
);

CREATE TABLE IF NOT EXISTS owners
(
    id SMALLSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age SMALLINT CHECK ( owners.age > 0 ) NOT NULL,
    driving_license BOOLEAN DEFAULT 'no',
    car_id SMALLSERIAL REFERENCES cars(id)
);