import os
import pandas as pd
import random
import numpy as np

# Get and read the dataSet file
currentDir = os.path.dirname(os.path.abspath(__file__))
filePath = os.path.join(currentDir, 'food.csv')

dataFrame = pd.read_csv(filePath)

# Drop the unnecessary fields
nombre_columna = 'Cleaned_Ingredients'
dataFrame = dataFrame.drop(nombre_columna, axis=1)

# Add missing fields
typeOfMeal = ["Breakfast", "Lunch", "Dinner", "Dessert", "Mid-morning meal"]
randomTypeOfMeal = [random.choice(typeOfMeal) for _ in range(len(dataFrame))]

rating = np.arange(0, 5.5, 0.5).tolist()
randomRating = [random.choice(rating) for _ in range(len(dataFrame))]

macroLabels = ["Carbohydrates", "Greases", "Protein", "Fiber"]
randomMacroPercentage = np.round(np.random.dirichlet(np.ones(4), len(dataFrame))*100, decimals=2)
macroPerentagesSet = [{macroLabels[j]:randomMacroPercentage[i][j] for j in range(len(macroLabels))} for i in range(len(randomMacroPercentage))]

minutes = np.arange(10, 180, 2).tolist()
timeOfPreparation = [str(random.choice(minutes)) + " minutes" for _ in range(len(dataFrame))]

dataFrame["TypeOfMeal"] = randomTypeOfMeal
dataFrame["Rating"] = randomRating
dataFrame["macronutrientsPercentages"] = macroPerentagesSet
dataFrame["timeOfPReparation"] = timeOfPreparation

# Save the cleaned dataSet file
newFilePath = os.path.join(currentDir, 'foodFilled.csv')
dataFrame.to_csv(newFilePath, index=False)