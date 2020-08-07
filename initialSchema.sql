BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS recipes(
    id serial,
    title text NOT NULL,
    content_link text,
    image_link text,
    CONSTRAINT unique_recipe_id UNIQUE(id),
    CONSTRAINT unique_content_link UNIQUE(content_link)
);

CREATE TABLE IF NOT EXISTS ingredients(
    id serial,
    name text NOT NULL,
    recipe_id int REFERENCES recipes(id),
    numeric_quantity int NOT NULL,
    quantity_type text,
    CONSTRAINT unique_ingredient_id UNIQUE(id)
);

CREATE TABLE IF NOT EXISTS instructions(
    recipe_id int REFERENCES recipes(id),
    step_number int NOT NULL,
    step_text text NOT NULL,
    PRIMARY KEY(recipe_id, step_number)
);
COMMIT;
