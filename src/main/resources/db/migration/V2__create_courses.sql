CREATE TABLE public.courses (
    id UUID DEFAULT random_uuid() NOT NULL,
    description VARCHAR(255),
    postgraduate BOOLEAN NOT NULL,
    recycling BOOLEAN NOT NULL,
    hours INT NOT NULL,
    date DATE NOT NULL,
    teacher_id VARCHAR(36),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

INSERT INTO courses (description, postgraduate, recycling, hours, date, teacher_id)
VALUES
    ('Curso 1', false, false, 80, '2020-01-15', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Curso 2', true, false, 200, '2020-03-20', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Curso 3', false, true, 20, '2020-05-10', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Curso 4', true, false, 150, '2021-06-25', 'e0d20f54-03cb-4a3e-9a76-0d4737e0d85a'),
    ('Curso 5', true, false, 120, '2021-08-12', 'e0d20f54-03cb-4a3e-9a76-0d4737e0d85a'),
    ('Curso 6', false, true, 30, '2021-10-05', '15c62e20-071f-4a08-8800-98f3f60c77c5'),
    ('Curso 7', true, false, 180, '2022-01-30', '7f988dd7-88cc-4343-9a0f-dcf8f9a6ad06'),
    ('Curso 8', false, true, 40, '2022-04-18', '1c07d4ef-d2e8-4ef0-9e38-96f4a89006e1'),
    ('Curso 9', true, false, 220, '2022-06-10', '1c07d4ef-d2e8-4ef0-9e38-96f4a89006e1'),
    ('Curso 10', false, false, 100, '2023-02-05', '4c99aa24-21a6-4e85-bfe7-917a05a2c131');
