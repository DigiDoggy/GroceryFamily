[![BackEnd Build Status](https://github.com/DigiDoggy/GroceryCompare/workflows/BackEnd%20build/badge.svg)](https://github.com/DigiDoggy/GroceryCompare/actions/workflows/build-backend.yml)
[![FrontEnd Build Status](https://github.com/DigiDoggy/GroceryCompare/workflows/FrontEnd%20build/badge.svg)](https://github.com/DigiDoggy/GroceryCompare/actions/workflows/build-frontend.yml)

This program facilitates the process of ordering products by allowing users to compare prices at various stores and select the most advantageous offers based on parameters such as price and quantity. It also speeds up the ordering process by quickly providing information on prices at multiple stores simultaneously and automatically selecting the optimal option.

Through price parsing and saving to a database, the user can view current prices for products at each store and choose the most suitable option for ordering products online.

The program interface is a web page where the user can edit the list of products, change the quantity of products, add new ones, or delete existing ones. After selecting the most suitable option for ordering products, the user can simply click the "Order" button, and the program will automatically send the order to the selected store.

Overall, this program is a useful tool for those who regularly order products online and want to save time and money while doing so.

![image alt](https://github.com/DigiDoggy/GroceryCompare/blob/master/GroceryCompare.drawio.png)

1.Definition of requirements:
 * Decide what information to collect about the products (name, price, website link, etc.).
 * Decide where the product data will be obtained (source sites).
 * Decide how the interface for adding, removing, and viewing products will look like.
2.Database design:
 * Create a data model to store product information.
 * Implement the database schema using the SQL language.
3.Backend implementation:
 * Write code to parse data from source sites and write it to a database.
 * Write code to implement RestAPI that will allow you to add, remove, and view products through a local server.
4.Development of an interface for managing goods and their prices.
 * Develop an interface for managing products and their prices using technologies such as HTML, CSS and JavaScript.
 * Integrate the created interface with the database via API.
 * Interface testing and bug fixes.
5.Project testing and deployment.
 * Testing the project for functionality and correcting possible errors.
 * Deploy the project to the selected web server or application hosting platform.
 * Conducting regular monitoring and updating the project, if necessary.
