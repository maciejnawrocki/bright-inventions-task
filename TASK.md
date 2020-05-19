Write a Java backend API that keeps track of books read by a user. The application allows to:

1. Add a new book (title, author, ISBN number, number of pages, 1-5 rating of how much the reader enjoyed it; nice to have: validate ISBN number)

2. List books entered into the system

3. Edit/remove the book entered to the system

Data should be stored in a database. The API requests should be validated. Front-end application is not required, please focus on the backend side. It would be nice though if you create a few unit tests or provide cURL/Postman queries to check the behaviour.

Extra points: 

4. The API should respond in a timely fashion even when 10 million books are entered into the system. 

5. Add API to add a comment to a book. Every book can have multiple comments. The List books API from point 2. should include the latest 5 comments for every book.