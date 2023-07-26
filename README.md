# Fitness Recipes

Our app has been carefully developed to meet the needs of those seeking a healthy lifestyle without compromising the taste and variety of their meals. One of the main features is an intuitive search engine that allows users to easily find fitness recipes tailored to their specific dietary preferences and nutritional requirements. With this feature, your customers will be able to discover new culinary ideas and enjoy a wide range of healthy options.

In addition, we have created a customisable weekly plan, which gives users the opportunity to add recipes to their calendar and organise their meals easily. This plan will allow them to keep track of their diet and maintain proper control of their calorie intake. With this application, customers will be able to design their own meal programme and adapt it to their lifestyle, taking into account their individual goals.

But that's not all. We have added a CRUD (Create, Read, Update and Delete) system so that customers themselves can create their own recipes, modify them and delete them according to their preferences. This will give them the freedom to experiment with new ingredients, adjust portions and customise their favourite dishes according to their dietary needs. With this feature, your app will become an interactive and personalised tool that empowers your users to take control of their diet.

##  How to use

If you want to use it with [Docker](https://www.docker.com/), follow up the next steps:

1. Clone the [repository](https://github.com/AnaOstio/fitness-recipes):

```
git clone https://github.com/AnaOstio/fitness-recipes.git
```

2. Start the project

```
 cd webapp
 docker-compose up --build -d
```

3. Shut down the project

```
docker-compose down 
```

There is another way to be able to run the project, for this, it is necessary to have the [Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.17/docker.html) image.

1. Clone the [repository](https://github.com/AnaOstio/fitness-recipes):

```
git clone https://github.com/AnaOstio/fitness-recipes.git
```

2. Change the path of the csv that will be indexed. This change has to be done in the class that is located on:

```
restapi/scr/main/java/com/emapthy/restapi/util/RecipeDB.java
```

3. Start up the Elasticsearch iamge and execute the Restapi project:

```
restapi/scr/main/java/com/emapthy/restapi/RestapiApplication.java
```

4. Start frontend project:

```
cd webapp
npm install
npm run serve
```

For use this aplication, you can login with one of the following users:
| Email                 | Password             |
|-----------------------|----------------------|
| user1@email.com       | user1                |
| user2@email.com       | user2                |
| user3@email.com       | user3                |
| user4@email.com       | user4                |
| user5@email.com       | user5                |


##  Developers

- [Carlos Domínguez Álcazar](https://github.com/C4rlos99)
- [Ana Fernández Ostio](https://github.com/AnaOstio)
