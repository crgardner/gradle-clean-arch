CREATE TABLE IF NOT EXISTS GREETING (
    id BIGINT IDENTITY,
    text VARCHAR(256) NOT NULL,
    originator VARCHAR(256) NOT NULL
);

INSERT INTO GREETING (id, text, originator) VALUES (1,  'I am a greeting that was created from flyway', 'Chris');

COMMIT;