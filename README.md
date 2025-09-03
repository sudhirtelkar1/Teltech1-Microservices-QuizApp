# Teltech1-Microservices-QuizApp

Overview: The Quiz Management System is a Java-based web application designed to create and manage quizzes. It consists of two independent microservices: Question Service and Quiz Service. The project leverages Spring Boot for backend services, with a PostgreSQL database used to store and retrieve quiz-related data. The services are built with robust exception handling and logging mechanisms to ensure reliability and maintainability. Quiz Service calls Question Service using REST calls implemented using  Apache HttpClient library

Microservices:

1   Question Microservice:

This microservice is responsible for managing quiz questions, including their creation, retrieval, and categorization.  
Technologies Used:  
    Spring Boot: For building the backend services with RESTful APIs.  
    PostgreSQL: Used as the database to store question data.  
    Exception Handling: Custom exception handling ensures that any errors occurring during database interaction or API request processing are caught and handled gracefully.  
    Logging: Integrated logging mechanism (via SLF4J with Logback) is used to track application activity, debug issues, and monitor service health.  
Endpoints:  
    /allquestions: Retrieves all questions from the database.  
    /category/{categoryName}: Fetches questions based on the specified category.  
    /add: Adds a new question to the database.  
    /update: Updates an existing question.  
    /delete: Deletes a question from the database.  
  
2. Quiz Microservice:  

This microservice handles quiz creation and score calculation. It communicates with the Question Service to fetch questions based on a given category and number of questions.  
Technologies Used:  
    Spring Boot: Handles API endpoints for quiz-related operations.  
    HTTP Client (Apache HttpClient): Communicates with the Question Service to retrieve questions dynamically.  
    Exception Handling: Custom exceptions are thrown for any unexpected errors encountered during quiz operations.  
    Logging: Logs all operations for better monitoring and error tracking.  
Endpoints:  
  /allquestions: Fetches all available questions from the Question Service.  
  /create: Creates a new quiz with a specific category, number of questions, and title.  
  /get/{id}: Retrieves the questions for a given quiz ID.  
  /submit/{id}: Submits quiz responses and calculates the result.  

Key Features:  
Scalable Microservices: Each microservice can be independently scaled and deployed, providing flexibility in terms of updates, maintenance, and scaling based on demand.  
Exception Handling: Comprehensive exception handling ensures that any issues, such as database connectivity errors or invalid inputs, are managed properly with meaningful error messages returned to the client.  
Logging: Detailed logs are maintained for each request, response, and error, aiding in the monitoring of application health, debugging, and performance optimization.  
Database Integration: The Question Service interacts with a PostgreSQL database to retrieve and store questions, ensuring data persistence and consistency.  
API Communication: The Quiz Service uses HTTP calls to interact with the Question Service and fetch question data for quiz creation, ensuring modularity and separation of concerns.  
Technologies Used:  

Spring Boot: Framework for building the microservices.  
PostgreSQL: Relational database used for storing question data.  
Apache HttpClient: Used for HTTP communication between the Quiz Service and Question Service.  
SLF4J with Logback: For logging activities and exception management.  
Future Enhancements:  
