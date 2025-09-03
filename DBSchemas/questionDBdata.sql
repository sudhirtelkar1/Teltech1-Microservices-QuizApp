-- Insert sample data into the 'question' table
INSERT INTO public.question (
    id, category, difficulty_level, option1, option2, option3, option4, question_title, right_answer
) VALUES
(1, 'Science', 'Easy', 'Mercury', 'Venus', 'Earth', 'Mars', 'Which planet is closest to the Sun?', 'Mercury'),
(2, 'Math', 'Medium', '2', '3', '4', '5', 'What is 2 + 2?', '4'),
(3, 'Geography', 'Hard', 'Nile', 'Amazon', 'Yangtze', 'Mississippi', 'Which is the longest river in the world?', 'Nile'),
(4, 'History', 'Easy', '1776', '1492', '1945', '1914', 'In which year did the American Revolution start?', '1776'),
(5, 'Science', 'Medium', 'Oxygen', 'Hydrogen', 'Nitrogen', 'Carbon', 'What is the most abundant gas in Earth’s atmosphere?', 'Nitrogen'),
(6, 'Math', 'Hard', '12', '15', '20', '25', 'What is the square root of 225?', '15'),
(7, 'Literature', 'Medium', 'Shakespeare', 'Hemingway', 'Dickens', 'Austen', 'Who wrote "Romeo and Juliet"?', 'Shakespeare'),
(8, 'Sports', 'Easy', 'Soccer', 'Basketball', 'Tennis', 'Cricket', 'Which sport uses a round ball and goals?', 'Soccer'),
(9, 'Technology', 'Hard', 'Alan Turing', 'Bill Gates', 'Steve Jobs', 'Tim Berners-Lee', 'Who is considered the father of modern computing?', 'Alan Turing'),
(10, 'Movies', 'Medium', 'Titanic', 'Avatar', 'Inception', 'The Godfather', 'Which movie won the most Oscars?', 'Titanic');
INSERT INTO public.question_tbl (id, category, difficulty_level, option1, option2, option3, option4, question_title, right_answer)
VALUES
(1, 'Math', 'Easy', '2', '4', '6', '8', 'What is 2 + 2?', '4'),
(2, 'Science', 'Medium', 'Oxygen', 'Hydrogen', 'Carbon', 'Nitrogen', 'Which element is most abundant in Earths atmosphere?', 'Nitrogen'),
(3, 'History', 'Hard', 'Napoleon Bonaparte', 'Julius Caesar', 'Abraham Lincoln', 'George Washington', 'Who was the first Emperor of the Roman Empire?', 'Julius Caesar'),
(4, 'Technology', 'Easy', 'Java', 'Python', 'C++', 'Ruby', 'Which programming language is known for its simplicity and readability?', 'Python'),
(5, 'Geography', 'Medium', 'Amazon River', 'Nile River', 'Yangtze River', 'Mississippi River', 'Which is the longest river in the world?', 'Nile River'),
(6, 'Literature', 'Hard', 'Moby Dick', 'Pride and Prejudice', 'War and Peace', 'The Great Gatsby', 'Which novel was written by Herman Melville?', 'Moby Dick'),
(7, 'Math', 'Medium', '3', '5', '7', '9', 'What is 5 + 2?', '7'),
(8, 'Science', 'Easy', 'Mars', 'Earth', 'Venus', 'Jupiter', 'Which planet is known as the Red Planet?', 'Mars'),
(9, 'History', 'Easy', 'WWI', 'WWII', 'Cold War', 'Vietnam War', 'Which war lasted from 1939 to 1945?', 'WWII'),
(10, 'Technology', 'Hard', 'Linux', 'Windows', 'MacOS', 'Android', 'Which operating system is open-source and widely used by developers?', 'Linux');
INSERT INTO public.question_tbl (id, category, difficulty_level, option1, option2, option3, option4, question_title, right_answer) VALUES
(11, 'Science', 'Easy', 'Mercury', 'Venus', 'Earth', 'Mars', 'Which planet is closest to the Sun?', 'Mercury'),
(12, 'Geography', 'Medium', 'Asia', 'Africa', 'Europe', 'Australia', 'Which is the largest continent by area?', 'Asia'),
(13, 'History', 'Hard', 'Leonardo da Vinci', 'Pablo Picasso', 'Vincent van Gogh', 'Claude Monet', 'Who painted the Mona Lisa?', 'Leonardo da Vinci'),
(14, 'Mathematics', 'Easy', '90', '180', '270', '360', 'What is the sum of angles in a triangle?', '180'),
(15, 'Technology', 'Medium', 'Alan Turing', 'Bill Gates', 'Steve Jobs', 'Mark Zuckerberg', 'Who is considered the father of modern computing?', 'Alan Turing'),
(16, 'Sports', 'Hard', 'Usain Bolt', 'Carl Lewis', 'Michael Phelps', 'Jesse Owens', 'Who holds the world record for the 100m sprint?', 'Usain Bolt'),
(17, 'Science', 'Easy', 'H2O', 'CO2', 'O2', 'CH4', 'What is the chemical formula for water?', 'H2O'),
(18, 'Geography', 'Medium', 'Sahara', 'Gobi', 'Kalahari', 'Atacama', 'Which is the largest desert in the world?', 'Sahara'),
(19, 'Mathematics', 'Hard', '1024', '512', '2048', '4096', 'What is 2 raised to the power of 10?', '1024'),
(20, 'History', 'Medium', 'Rome', 'Athens', 'Cairo', 'Jerusalem', 'Which city is known as the \"Eternal City\"?', 'Rome');
INSERT INTO public.quiz (id, title)
VALUES
(1, 'General Knowledge Quiz'),
(2, 'Math Challenge'),
(3, 'Science and Nature Quiz'),
(4, 'History Trivia'),
(5, 'Technology and Innovations Quiz'),
(6, 'Geography Quiz'),
(7, 'Literature and Arts Quiz'),
(8, 'Pop Culture Quiz'),
(9, 'Sports Quiz'),
(10, 'Current Affairs Quiz');
INSERT INTO public.quiz_questions (quiz_id, questions_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(4, 9),
(4, 10),
(5, 1),
(5, 4),
(5, 7),
(6, 2),
(6, 5),
(6, 8),
(7, 3),
(7, 6),
(7, 9),
(8, 10),
(8, 3),
(8, 7);
