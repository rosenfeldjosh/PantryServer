import textract
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
import re
import glob
import requests

files = glob.glob("example_recipes/**/*.pdf", recursive=True)
for filename in files:
    recipe = textract.process(filename)
    punctuations = ['(', ')', ';', ':', '[', ']', ',', '•', '*']
    measurements = ['ounce', 'ounces', 'tablespoons', 'tablespoon', 'clove', 'cup', 'teaspoon']
    stop_words = stopwords.words('english')

    recipe_list = recipe.decode('utf-8').split('\n')
    found_ingredients_section = False
    ingredients = []
    reduced_ingredients = []
    title = recipe_list[0]
    for line in recipe_list:
        if line == 'INGREDIENTS:':
            found_ingredients_section = True
            continue

        if found_ingredients_section and line == 'DIRECTIONS:':
            break

        if found_ingredients_section:
            ingredients.append(line)

    for ingredient in ingredients:
        reduced_ingredients.append(' '.join([tokenized_ingredient
                        for tokenized_ingredient in word_tokenize(ingredient)
                        if tokenized_ingredient not in punctuations
                        and tokenized_ingredient not in stop_words
                        and not bool(re.search(r'\d', tokenized_ingredient))
                        and not tokenized_ingredient in measurements]))

    ingredients_post_endpoint = 'http://localhost:8080/postNewRecipe'
    payload = {'title': title, 'ingredients': reduced_ingredients}

    requests.post(ingredients_post_endpoint, json=payload)
