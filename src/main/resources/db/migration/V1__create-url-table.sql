CREATE TABLE url (
    id Integer PRIMARY KEY,
    url VARCHAR(150) NOT NULL,
    shortCode VARCHAR(20) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    accessCount Integer NOT NULL
);