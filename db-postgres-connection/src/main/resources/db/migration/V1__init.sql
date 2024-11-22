CREATE TABLE students IF NOT EXISTS (
    id SERIAL PRIMARY KEY,
    student_name VARCHAR(90) NOT NULL,
    email VARCHAR(255) NOT NULL,
    age INT
);
