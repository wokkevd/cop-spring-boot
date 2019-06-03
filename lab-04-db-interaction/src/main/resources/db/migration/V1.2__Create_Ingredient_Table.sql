CREATE TABLE ingredient
(
    id           UNIQUEIDENTIFIER PRIMARY KEY,
    recipe_fk    UNIQUEIDENTIFIER NOT NULL REFERENCES recipe (id),
    quantity     NVARCHAR(100)    NOT NULL,
    name         NVARCHAR(100)    NOT NULL
);