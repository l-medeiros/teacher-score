CREATE TABLE public.absences (
    id UUID DEFAULT random_uuid() NOT NULL,
    note VARCHAR(255),
    date DATE NOT NULL,
    teacher_id VARCHAR(36),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

INSERT INTO absences (note, date, teacher_id)
VALUES
    ('Falta 1', '2020-02-10', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Falta 2', '2020-04-05', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Falta 3', '2020-06-20', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Falta 4', '2021-07-15', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Falta 5', '2021-09-03', '3b6e6d45-7af8-47b2-93a6-ec99bfc140b7'),
    ('Falta 6', '2021-11-18', '5014872b-7646-4b7d-8318-fd49a8a6fbad'),
    ('Falta 7', '2022-03-25', '5014872b-7646-4b7d-8318-fd49a8a6fbad'),
    ('Falta 8', '2022-05-10', '7f988dd7-88cc-4343-9a0f-dcf8f9a6ad06'),
    ('Falta 9', '2022-07-08', '1c07d4ef-d2e8-4ef0-9e38-96f4a89006e1'),
    ('Falta 10', '2023-03-15', '9cf69c25-6866-4a57-b6ef-c4a50d0fc9de');
