import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router";
import { getQuizbyId, deleteQuiz } from "../../../Services/UseService";
import { jwtDecode } from "jwt-decode"; // Adjust to named import for newer versions
import "./InfoQues.css";

const InfoQues = () => {
  const { quizId } = useParams(); // Get quiz ID from URL
  const navigate = useNavigate();
  const [quiz, setQuiz] = useState(null); // Store quiz data
  const [showOptions, setShowOptions] = useState(false); // Toggle option menu
  const token = localStorage.getItem("token");
  const decoded = token ? jwtDecode(token) : null;
  const userIdFromToken = decoded?.userId;

  useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const data = await getQuizbyId(quizId); // Fetch quiz by ID
        console.log("Fetched Quiz Data:", data); // Log fetched data
        setQuiz(data.data); // Store the quiz data
      } catch (error) {
        console.error("Error fetching quiz details:", error);
      }
    };

    fetchQuiz();
  }, [quizId]);

  // Check if the logged-in user is the owner of the quiz
  const isOwner = quiz?.userId === userIdFromToken;

  const handleStart = () => {
    navigate(`/Answer/${quizId}`); // Navigate to answer questions page
  };

  const handleSelectAnotherQuiz = () => {
    navigate("/home"); // Navigate back to the home page
  };

  const toggleOptions = () => {
    setShowOptions(!showOptions); // Toggle options menu visibility
  };

  const handleDeleteQuiz = async () => {
    try {
      await deleteQuiz(quizId); // Call the deleteQuiz function
      navigate("/home"); // Redirect to home after successful deletion
    } catch (error) {
      console.error("Error deleting quiz:", error);
      alert("Failed to delete quiz"); // Show error if deletion fails
    }
  };

  const handleModifyQuiz = () => {
    navigate(`/modify/${quizId}`); // Navigate to modify quiz page
  };

  if (!quiz || !quiz.questions) {
    return <div>Loading...</div>; // Show loading until quiz data is available
  }

  return (
    <div className="quiz-overview-content">
      {/* Options Menu Visibility Check */}
      {isOwner && (
        <>
          <i
            className="fa-solid fa-ellipsis threedot-option"
            onClick={toggleOptions}
          ></i>

          {showOptions && (
            <div className="options-menu">
              <button onClick={handleModifyQuiz}>Modify</button>
              <button onClick={handleDeleteQuiz}>Delete</button>
            </div>
          )}
        </>
      )}

      <i className="fa-solid fa-face-smile result-icon"></i>
      <div className="quiz-title">
        <h2 className="quiz-title">{quiz.title}</h2>
        <span className="quiz-subtitle">{quiz.questions.length} Questions</span>
        <div className="timer">
          <span className="timer-text">ID: {quiz.id}</span>{" "}
          {/* Timer Placeholder */}
        </div>
      </div>
      <button className="start-button" onClick={handleStart}>
        Start now
      </button>
      <button className="start-button" onClick={handleSelectAnotherQuiz}>
        Select Another Quiz
      </button>
    </div>
  );
};

export default InfoQues;
