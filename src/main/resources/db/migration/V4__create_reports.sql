CREATE TABLE public.reports (
    id UUID DEFAULT random_uuid() NOT NULL,
    result DECIMAL(19, 4) NOT NULL,
    teacher_id VARCHAR(36),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);