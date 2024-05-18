CREATE TABLE public.teachers (
    id UUID DEFAULT random_uuid() NOT NULL,
    name VARCHAR(100) NOT NULL,
    hire_date DATE NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO teachers (id, name, hire_date)
VALUES 
    ('3b6e6d45-7af8-47b2-93a6-ec99bfc140b7', 'Professor 0', '2020-01-01'),
    ('e0d20f54-03cb-4a3e-9a76-0d4737e0d85a', 'Professor 1', '2020-02-01'),
    ('15c62e20-071f-4a08-8800-98f3f60c77c5', 'Professor 2', '2020-03-01'),
    ('7f988dd7-88cc-4343-9a0f-dcf8f9a6ad06', 'Professor 3', '2021-04-01'),
    ('9cf69c25-6866-4a57-b6ef-c4a50d0fc9de', 'Professor 4', '2021-05-01'),
    ('1c07d4ef-d2e8-4ef0-9e38-96f4a89006e1', 'Professor 5', '2021-05-01'),
    ('e4a6da97-22a0-4d8a-bf8b-1f7ff1db44d1', 'Professor 6', '2022-04-01'),
    ('73052b43-1c5c-4f2c-9db8-1a26d2dc4322', 'Professor 7', '2022-03-01'),
    ('5014872b-7646-4b7d-8318-fd49a8a6fbad', 'Professor 8', '2022-02-01'),
    ('4c99aa24-21a6-4e85-bfe7-917a05a2c131', 'Professor 9', '2023-01-01');
