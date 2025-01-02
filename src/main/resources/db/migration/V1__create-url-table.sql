CREATE TABLE url (
    id SERIAL PRIMARY KEY,
    url VARCHAR(150) NOT NULL,
    short_code VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    access_count INTEGER NOT NULL
);