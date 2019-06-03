CREATE TABLE recipe
(
    id           UNIQUEIDENTIFIER PRIMARY KEY,
    recipe_id    NVARCHAR(8)      NOT NULL,
    name         NVARCHAR(100)    NOT NULL,
    category     NVARCHAR(100)    NOT NULL,
    origin       NVARCHAR(100)    NOT NULL,
    instructions NVARCHAR(100000) NOT NULL,
    picture      NVARCHAR(1000),
    video        NVARCHAR(1000)
);