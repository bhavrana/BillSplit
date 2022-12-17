Bill Split
-------------
------------------------------------
--------------------------------------

User API's:
------------

1. Create new User :

   Request: curl --location --request POST 'http://localhost:8080/v1/user?name=Sahi&groupID=1' \
   --header 'Content-Type: application/json'

   Response :
   {
   "User": {
   "id": 10,
   "name": "Sahi",
   "userGroup": "DisneyLand"
   }
   }

2. Get user with user ID:
   Request: curl --location --request GET 'http://localhost:8080/v1/user/1'
   
   response: {
   "User": {
   "id": 1,
   "name": "Mohit",
   "userGroup": "DisneyLand"
   }
   }

3. Delete User for user ID:
   Request: curl --location --request DELETE 'http://localhost:8080/v1/user/delete/1'
   
   Response : 1

--------------------------------------------

User Group API's
----------------

1. Create new User Group:

   Request: curl --location --request POST 'http://localhost:8080/v1/group' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "title": "Korea trip",
   "description": "Korea trip in winter",
   "users": [
   1,
   5,
   6,
   3
   ]
   }'

   Response :
   {
   "UserGroup": {
   "title": "Korea trip",
   "description": "Korea trip in winter",
   "users": [
   "Mohit",
   "Prachi",
   "Twinkle",
   "Aman"
   ],
   "expenseOutputs": [],
   "id": 3
   }
   }

2. Get user Group with group ID:
   Request: curl --location --request GET 'http://localhost:8080/v1/group/1'

   response: {
   "UserGroup": {
   "title": "DisneyLand",
   "description": "Trip to DisneyLand tokyo",
   "users": [
   "Mohit",
   "Bhavna",
   "Aman",
   "Priya"
   ],
   "expenseOutputs": [],
   "id": 1
   }
   }

3. Delete User group for group ID:
   Request: curl --location --request DELETE 'http://localhost:8080/v1/group/delete/1'

   Response : 1

4. Settle Group Expense:

   Request: curl --location --request POST 'http://localhost:8080/v1/group/payment/2'

   Response: {
   "Settlement": [
   {
   "payee": "Mimi",
   "payer": "Prachi",
   "amount": 7000.0
   },
   {
   "payee": "Mimi",
   "payer": "Alok",
   "amount": 4000.0
   },
   {
   "payee": "Twinkle",
   "payer": "Alok",
   "amount": 3000.0
   }
   ]
   }

-----------------------------------------------------

Expense APIs
-------------

1. Add/Create Expense

   Request: curl --location --request POST 'http://localhost:8080/v1/expense?groupID=2&userID=6&paid=10000&currency=1&title=Drinks vegas' \
   --header 'Content-Type: application/json'

   Response:
   {
   "Expense": {
   "title": "Drinks vegas",
   "timestamp": 1671275618910,
   "settled": false,
   "userGroup": "LasVegas",
   "userBalanceMap": {
   "Prachi": -2500.0,
   "Alok": -2500.0,
   "Twinkle": 7500.0,
   "Mimi": -2500.0
   },
   "id": 2
   }
   }

2. Get Expense

   Request: curl --location --request GET 'http://localhost:8080/v1/expense/2'

   Response: {
   "Expense": {
   "title": "Drinks vegas",
   "timestamp": 1671275618910,
   "settled": true,
   "userGroup": "LasVegas",
   "userBalanceMap": {
   "Prachi": -2500.0,
   "Alok": -2500.0,
   "Twinkle": 7500.0,
   "Mimi": -2500.0
   },
   "id": 2
   }
   }

3. Delete Expense:

   Request: curl --location --request DELETE 'http://localhost:8080/v1/expense/delete/2'

   Response: 1

----------------------------------------------------------

Exception Handling:
--------------------

All the API's for user, user group and expense have input validation for the incoming requests. 
Below is an example:

Request: curl --location --request GET 'http://localhost:8080/v1/user/1'

Response: The user 1 doesn't exist.
