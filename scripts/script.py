import os
import csv
import pandas as pd
import random
import numpy as np

# Get and read the dataSet file
currentDir = os.path.dirname(os.path.abspath(__file__))
filePath = os.path.join(currentDir, "food.csv")

dataFrame = pd.read_csv(filePath, delimiter=",")

# Drop the unnecessary fields
nombre_columna = "Cleaned_Ingredients"
dataFrame = dataFrame.drop(nombre_columna, axis=1)

# Rename field Image_Name to ImageName

dataFrame = dataFrame.rename(columns=({"Image_Name" : "imageName", "Ingredients" : "ingredients", "Instructions" : "instruction", "Title" : "title"}))

# Add missing fields
typeOfMeal = ["Breakfast", "Lunch", "Dinner", "Dessert", "Mid-morning meal"]
randomTypeOfMeal = [random.choice(typeOfMeal) for _ in range(len(dataFrame))]

ratingValues = np.arange(0, 5.5, 0.5).tolist()
ids = np.arange(10000, 10005, 1).tolist()
randomRating = [{str(ids[j]):random.choice(ratingValues) for j in range(len(ids))} for _ in range(len(dataFrame))]

macroLabels = ["Carbohydrates", "Greases", "Protein", "Fiber"]
randomMacroPercentage = np.round(np.random.dirichlet(np.ones(4), len(dataFrame))*100, decimals=2)
macroPerentagesSet = [{macroLabels[j]:randomMacroPercentage[i][j] for j in range(len(macroLabels))} for i in range(len(randomMacroPercentage))]

minutes = np.arange(10, 180, 2).tolist()
timeOfPreparation = [str(random.choice(minutes)) + " minutes" for _ in range(len(dataFrame))]

userId = np.arange(0, 5, 1).tolist()
userListId = [str(random.choice(userId))  for _ in range(len(dataFrame))]

dataFrame["typeOfMeal"] = randomTypeOfMeal
dataFrame["rating"] = randomRating
dataFrame["macronutrientsPercentages"] = macroPerentagesSet
dataFrame["timeOfPReparation"] = timeOfPreparation
dataFrame["userId"] = userListId
dataFrame["separator"] = ["---" for _ in range(len(dataFrame))]

# Save the cleaned dataSet file
newFilePath = os.path.join(currentDir, "foodFilled1.csv")
dataFrame.to_csv(newFilePath, index=False)

# Change delimiter , for #
with open("foodFilled1.csv", mode="r") as infile:
    reader = csv.reader(infile, dialect="excel")    
    with open("foodFilled2.csv", mode="w") as outfile:
        writer = csv.writer(outfile, delimiter="#")
        writer.writerows(reader)
