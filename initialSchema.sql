BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS recipes(
    id serial,
    title text,
    content_link text,
    CONSTRAINT unique_recipe_id UNIQUE(id)
);

CREATE TABLE IF NOT EXISTS ingredients(
    id serial,
    name text,
    CONSTRAINT unique_ingredient_id UNIQUE(id)
);

CREATE TABLE recipe_ingredients( -- if we were to consider quantity, it would go here I think
    ingredient_id integer REFERENCES ingredients(id) ON DELETE CASCADE,
    recipe_id integer REFERENCES recipes(id) ON DELETE CASCADE
);

COMMIT;
