# expense-tracker
A Expense tracker created with Java and Spring Boot

## End points
The end points are:

Method | Url                 | Description
------ | ------------------- | -----------
GET    | /expense            | Returns all expenses.
GET    | /expense/total      | Returnes total cost of all expeneses
GET    | /expense/total/type | Returns total cost of expenses by type
POST   | /expense            | Creates a payment, give as body JSON with the optional date, name, type and cost
PUT    | /expense/{id}       | Updates an expense given a expense body JSON containing the new name, type and cost
DELETE | /expense/{id}       | Deletes an expense with the given id
