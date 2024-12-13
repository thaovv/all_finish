import React from "react";
import { useLocation, useNavigate } from "react-router";
import "./ScoreQues.css";

const ScoreQues = () => {
  const location = useLocation(); // Access the state passed from AnswerQuestions
  const navigate = useNavigate();
  const { score, correctAnswers, incorrectAnswers, quizId } =
    location.state || {}; // Destructure data

  const handleSelectAnotherQuiz = () => {
    navigate("/home"); // Navigate back to the home page to select another quiz
  };

  const handleRetry = () => {
    if (quizId) {
      navigate(`/Info/${quizId}`); // Retry the quiz using the same quiz ID
    }
  };

  return (
    <div className="your-result">
      <div className="result-content">
        <i className="fa-solid fa-face-smile result-icon"></i>
        <div className="result-score">
          <span className="score-title">Your Score</span>
          <div className="score-value">
            {score}/{correctAnswers + incorrectAnswers}
          </div>
        </div>

        <div className="stat correct-answer">
          <i className="fa-solid fa-check stat-icon"></i>
          <span>Correct Answers: {correctAnswers}</span>
        </div>
        <div className="stat incorrect-answer">
          <i className="fa-solid fa-xmark incorrect-icon"></i>
          <span>Incorrect Answers: {incorrectAnswers}</span>
        </div>

        <button className="retry-button" onClick={handleRetry}>
          Retry
        </button>
        <button className="retry-button" onClick={handleSelectAnotherQuiz}>
          Select Another Quiz
        </button>
      </div>
    </div>
  );
};

export default ScoreQues;
