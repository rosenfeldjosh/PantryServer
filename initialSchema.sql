BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS recipes(
    id serial,
    title text NOT NULL,
    content_link text,
    CONSTRAINT unique_recipe_id UNIQUE(id)
);

CREATE TABLE IF NOT EXISTS ingredients(
    id serial,
    name text NOT NULL,
    recipe_id int REFERENCES recipes(id),
    -- the schema is designed like this to be cognizant of a future where we might
    -- need to add quantity
    CONSTRAINT unique_ingredient_id UNIQUE(id)
);

CREATE TABLE IF NOT EXISTS instructions(
    recipe_id int REFERENCES recipes(id),
    step_number int NOT NULL,
    step_text text NOT NULL,
    PRIMARY KEY(recipe_id, step_number)
);
COMMIT;
