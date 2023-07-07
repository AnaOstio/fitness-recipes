export default {
    async addRecipe(title, ingredients, instructions, timePreparation,
                    typeMeal, protein, carbohydrates, fiber, greases) {
        let aux = {
            title: title,
            ingredients: ingredients,
            imageName: "default.jpg",
            instructions: instructions,
            timeOfPreparation: timePreparation,
            typeOfMeal: typeMeal,
            macronutrientsPercentages: {
                protein: protein,
                carbohydrates: carbohydrates,
                fiber: fiber,
                greases: greases
            },
            userId: 1
        };


        console.log(JSON.stringify(aux));

        const response = await fetch("http://localhost:8080/recipes/add/1", {
            method: "POST",
            credentials: "include",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(aux),
        }).catch((error) => { console.log(error) });
        const data = await response;
        console.log(data);
        return data;
    }
}