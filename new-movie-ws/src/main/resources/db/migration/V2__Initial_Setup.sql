-- src/main/resources/db/migration/V1__Initial_Setup.sql

INSERT INTO Movie (title, year, description, score, director, producer, languages) VALUES
('The Great Adventure', 2021, 'An epic journey of discovery and adventure.', 8.5, 'John Doe', 'Jane Smith', 'English, Spanish'),
('Mystery of the Lost City', 2019, 'A thrilling mystery set in an ancient city.', 7.8, 'Alice Johnson', 'Bob Brown', 'English, French'),
('Space Odyssey', 2022, 'A breathtaking voyage through the cosmos.', 9.1, 'Chris Evans', 'Diana Prince', 'English, German'),
('The Last Stand', 2020, 'A gripping tale of survival and courage.', 8.0, 'Bruce Wayne', 'Clark Kent', 'English, Japanese'),
('Romance in Paris', 2018, 'A heartwarming love story set in the city of lights.', 7.5, 'Peter Parker', 'Mary Jane', 'English, Italian');