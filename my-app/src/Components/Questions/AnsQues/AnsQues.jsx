import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router";
import { getQuizbyId } from "../../../Services/UseService"; // Ensure this is the correct API function
import "./AnsQues.css";

const AnswerQuestions = () => {
  const { quizId } = useParams(); // Get quiz ID from URL
  const navigate = useNavigate();
  const [questions, setQuestions] = useState([]); // Questions list
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [selectedAnswers, setSelectedAnswers] = useState([]);
  const [score, setScore] = useState(0);

  // Fetch quiz questions by ID
  useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const data = await getQuizbyId(quizId); // Fetch quiz by ID
        setQuestions(data.data.questions); // Set questions in state
      } catch (error) {
        console.error("Error fetching quiz details:", error);
      }
    };

    fetchQuiz();
  }, [quizId]);

  const handleOptionClick = (option) => {
    setSelectedAnswers((prevAnswers) => {
      const updatedAnswers = [...prevAnswers];
      updatedAnswers[currentQuestionIndex] = option; // Store selected option for current question
      return updatedAnswers;
    });
  };

  const handleNextQuestion = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
    }
  };

  const handlePreviousQuestion = () => {
    if (currentQuestionIndex > 0) {
      setCurrentQuestionIndex((prevIndex) => prevIndex - 1);
    }
  };

  const handleSubmit = () => {
    let calculatedScore = 0;
    let correctAnswers = 0;
    let incorrectAnswers = 0;

    questions.forEach((question, index) => {
      const selectedAnswer = selectedAnswers[index];
      const correctAnswer = question.answers.find((answer) => answer.correct);

      if (selectedAnswer === correctAnswer?.content) {
        calculatedScore++;
        correctAnswers++;
      } else {
        incorrectAnswers++;
      }
    });

    setScore(calculatedScore); // Update score in state
    // Navigate to ScoreQues page with score data
    navigate("/Score", {
      state: {
        score: calculatedScore,
        correctAnswers,
        incorrectAnswers,
        quizId: quizId,
      },
    });
  };

  // Ensure questions data is available
  if (questions.length === 0) {
    return <div>Loading...</div>;
  }

  return (
    <div className="answer-question-form">
      <div className="quiz-container">
        <div className="quiz-info">
          <div className="icon-container">
            <i className="fa-solid fa-code quiz-icon"></i>
          </div>
          <div className="quiz-details">
            <h2 className="quiz-title">{questions[0].quizTitle}</h2>
            <span className="quiz-subtitle">{questions.length} Questions</span>
          </div>
        </div>
        <div className="timer">
          <i className="fa-solid fa-stopwatch timer-icon"></i>
          <span className="timer-text">00:00:29</span>
        </div>
      </div>
      <div>
        <div className="question-block">
          <div className="question-number">{currentQuestionIndex + 1}</div>
          <p>{questions[currentQuestionIndex].content}</p>
        </div>
        <div className="answer-options">
          {questions[currentQuestionIndex].answers.map((option, index) => (
            <div
              key={index}
              className={`answer-option ${
                selectedAnswers[currentQuestionIndex] === option.content
                  ? "selected"
                  : ""
              }`}
              onClick={() => handleOptionClick(option.content)}
            >
              {option.content}
            </div>
          ))}
        </div>
      </div>
      <div className="flex justify-between mt-7">
        <div className="button-group">
          <button
            className="button"
            onClick={handlePreviousQuestion}
            disabled={currentQuestionIndex === 0}
          >
            Previous
          </button>
          {currentQuestionIndex === questions.length - 1 ? (
            <button className="button" onClick={handleSubmit}>
              Submit
            </button>
          ) : (
            <button className="button" onClick={handleNextQuestion}>
              Next
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default AnswerQuestions;
