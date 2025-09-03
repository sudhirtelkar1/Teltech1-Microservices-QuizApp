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
INSERT INTO public.quiz_question_ids (quiz_id, question_id)
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
