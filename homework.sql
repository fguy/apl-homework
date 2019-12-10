-- SQL statements to create table(s) to store our animals. 
-- Hint: Don’t worry about the actual DBMS or SQL dialect. Generic SQL is fine. 
-- Important: Ensure your database schema can be used to fully persist an instantiated object.
CREATE TABLE pets (
    pet_id TEXT PRIMARY KEY NOT NULL,
    species TEXT NOT NULL,
    age INTEGER NOT NULL,
    favorite_food TEXT,
    name_change_count INTEGER DEFAULT 0
);

CREATE TABLE pet_name_changes (
    pet_name_change_id INTEGER PRIMARY KEY AUTOINCREMENT,
    pet_id TEXT NOT NULL,
    name TEXT NOT NULL,
    FOREIGN KEY(pet_id) REFERENCES pets(id)
);

-- Sample insert statements.
INSERT INTO pets(pet_id, species, age) VALUES('a764c497-2b50-467f-b684-f104595c0d63', 'Cat', 6);
BEGIN TRANSACTION;
INSERT INTO pet_name_changes(pet_id, name) VALUES('a764c497-2b50-467f-b684-f104595c0d63', 'Garfield');
INSERT INTO pet_name_changes(pet_id, name) VALUES('a764c497-2b50-467f-b684-f104595c0d63', 'Felix');
INSERT INTO pet_name_changes(pet_id, name) VALUES('a764c497-2b50-467f-b684-f104595c0d63', 'Simba');
INSERT INTO pet_name_changes(pet_id, name) VALUES('a764c497-2b50-467f-b684-f104595c0d63', 'Figaro');
UPDATE pets SET name_change_count = name_change_count + 4 WHERE pet_id = 'a764c497-2b50-467f-b684-f104595c0d63';
COMMIT;

-- Sample select statement.
SELECT
    species, age, favorite_food, name_change_count, name
FROM pets
LEFT OUTER JOIN pet_name_changes ON pets.pet_id = pet_name_changes.pet_id
GROUP BY pets.pet_id
HAVING pet_name_change_id IS NULL OR pet_name_change_id = MAX(pet_name_change_id);
