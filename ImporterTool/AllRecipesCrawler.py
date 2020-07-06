from bs4 import BeautifulSoup
import requests
import json
import re
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

# url = "https://www.allrecipes.com/recipe/16743/roasted-veggies-with-couscous/?internalSource=rotd&referringId=84&referringContentType=Recipe%20Hub"
punctuations = ['(', ')', ';', ':', '[', ']', ',', '*']
measurements = ['ounce', 'ounces', 'tablespoons', 'tablespoon', 'clove', 'cup', 'teaspoon']
stop_words = stopwords.words('english')


def post_to_pantry_server(title, ing, link, img_link, instr):
    ingredients_post_endpoint = 'http://localhost:8080/postNewRecipe'
    payload = {'title': title, 'ingredients': ing, 'contentLink': link, 'imageLink': img_link, 'instructions': instr}

    requests.post(ingredients_post_endpoint, json=payload)


for recipe_id in range(16743, 16793):
    url = "https://www.allrecipes.com/recipe/" + str(recipe_id)
    r = requests.get(url)
    soup = BeautifulSoup(r.text, 'html.parser')
    first_script = soup.find('script')
    reduced_ingredients = set()
    instructions = []
    image_link = ""
    title = ""
    for json_object in json.loads(first_script.text):
        if json_object.get('@type', '') == 'Recipe':
            for ingredient in (json_object.get('recipeIngredient', [])):
                ingredient_string = ' '.join([tokenized_ingredient
                                                     for tokenized_ingredient
                                                     in word_tokenize(ingredient)
                                                     if tokenized_ingredient not in punctuations
                                                     and tokenized_ingredient not in stop_words
                                                     and not bool(re.search(r'\d', tokenized_ingredient))
                                                     and not tokenized_ingredient in measurements])
                user_input = raw_input("Ingredient is [" + ingredient_string + "] enter a correction or leave blank: ")
                if user_input != "":
                    reduced_ingredients.add(user_input)
                else:
                    reduced_ingredients.add(ingredient_string)

            for instruction in json_object.get('recipeInstructions', []):
                instructions.append(instruction.get('text', ''))
            image_link = json_object.get('image', {}).get('url', 'No image url found')
            title = json_object.get("name", 'No title')
            if title == 'No title':
                continue
            post_to_pantry_server(title, list(reduced_ingredients), url, image_link, instructions)
