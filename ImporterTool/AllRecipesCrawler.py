from bs4 import BeautifulSoup
import requests
import json
import re
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

url = "https://www.allrecipes.com/recipe/16743/roasted-veggies-with-couscous/?internalSource=rotd&referringId=84&referringContentType=Recipe%20Hub"

r = requests.get(url)
soup = BeautifulSoup(r.text, 'html.parser')
first_script = soup.find('script')
reduced_ingredients = set()
instructions = []
image_link = ""
punctuations = ['(', ')', ';', ':', '[', ']', ',', '*']
measurements = ['ounce', 'ounces', 'tablespoons', 'tablespoon', 'clove', 'cup', 'teaspoon']
stop_words = stopwords.words('english')

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
            # before we send it over to the server, let's manually make sure each ingredient we are sending is in valid
            # format. Not ideal, but the real programmatic solution is much more involved.
            user_input = raw_input("Ingredient is [" + ingredient_string + "] enter a correction or leave blank: ")
            if user_input != "":
                reduced_ingredients.add(user_input)
            else:
                reduced_ingredients.add(ingredient_string)

        for instruction in json_object.get('recipeInstructions', []):
            instructions.append(instruction.get('text', ''))
        image_link = json_object.get('image', {}).get('url', 'No image url found')


def post_to_pantry_server(title, i, link, img_link):
    ingredients_post_endpoint = 'http://localhost:8080/postNewRecipe'
    payload = {'title': title, 'ingredients': i, 'contentLink': link, 'imageLink': img_link}

    requests.post(ingredients_post_endpoint, json=payload)
