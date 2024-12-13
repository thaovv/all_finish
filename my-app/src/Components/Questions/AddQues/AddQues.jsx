import React, { useState } from "react";
import "./AddQues.css";
import { createQuestion } from "../../../Services/UseService";
import { useNavigate } from "react-router-dom";

const AddQues = ({ onSubmit }) => {
  const [quizTitle, setQuizTitle] = useState(""); // Quiz Title state
  const [questions, setQuestions] = useState([
    { question: "", options: ["", "", "", ""], correctAnswer: "" },
  ]);
  const navigate = useNavigate();

  // Handle changes to the quiz title
  const handleQuizTitleChange = (e) => {
    setQuizTitle(e.target.value);
  };

  // Handle question content change
  const handleQuestionChange = (index, key, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index][key] = value;
    setQuestions(updatedQuestions);
  };

  // Handle changes to options
  const handleOptionChange = (qIndex, oIndex, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options[oIndex] = value;
    setQuestions(updatedQuestions);
  };

  // Add a new option to the question
  const handleAddOption = (qIndex) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options.push("");
    setQuestions(updatedQuestions);
  };

  // Set the correct answer for the question
  const handleCorrectAnswerClick = (qIndex, option) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].correctAnswer = option;
    setQuestions(updatedQuestions);
  };

  // Remove an option from a question
  const handleRemoveOption = (qIndex, oIndex) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options.splice(oIndex, 1);
    if (
      updatedQuestions[qIndex].correctAnswer ===
      updatedQuestions[qIndex].options[oIndex]
    ) {
      updatedQuestions[qIndex].correctAnswer = "";
    }
    setQuestions(updatedQuestions);
  };

  // Add a new question to the quiz
  const handleAddQuestion = () => {
    setQuestions([
      ...questions,
      { question: "", options: ["", "", "", ""], correctAnswer: "" },
    ]);
  };

  // Remove a question from the quiz
  const handleRemoveQuestion = (index) => {
    const updatedQuestions = questions.filter((_, i) => i !== index);
    setQuestions(updatedQuestions);
  };

  // Handle the save action (submit the form)
  const handleSubmit = async () => {
    // Validate that all fields are filled correctly
    if (
      questions.some(
        (q) =>
          !q.question || !q.correctAnswer || q.options.some((opt) => opt === "")
      )
    ) {
      alert("Please fill in all the fields correctly.");
      return;
    }

    // Format the quiz data to match the API
    const quizData = {
      title: quizTitle, // The title of the quiz
      published: true,
      questions: questions.map((q) => ({
        content: q.question,
        score: 2, // Default score for each question
        answers: q.options.map((option) => ({
          content: option,
          correct: option === q.correctAnswer,
        })),
      })),
      userId: 1, // Assuming the user is logged in with ID 1
    };

    try {
      // Call API to create the quiz
      const response = await createQuestion(quizData);
      console.log("Quiz Created:", response);

      // After successful submission, reset the form
      setQuizTitle(""); // Clear the quiz title
      setQuestions([
        { question: "", options: ["", "", "", ""], correctAnswer: "" },
      ]);
      navigate("/home");
    } catch (error) {
      console.error("Error creating quiz:", error);
    }
  };

  return (
    <div className="new-quiz-container">
      <div className="new-quiz-nav">
        <div className="new-quiz-title-container">
          <div className="icon-container">
            <i className="fa-solid fa-code quiz-icon"></i>
          </div>
          <span className="new-quiz-title">
            New <span className="new-quiz-title-highlight">Quiz</span>
          </span>
        </div>
      </div>
      <div>
        {" "}
        {/* Removed form submission handler */}
        <div className="quiz-name-frame">
          <div className="quiz-input-container">
            <div className="quiz-label">
              <div className="quiz-number">1</div>
              <span className="quiz-name-label">Quiz Title:</span>
            </div>
            <input
              className="quiz-input"
              placeholder="Enter the Title Of The Quiz ..."
              value={quizTitle}
              onChange={handleQuizTitleChange}
            />
          </div>
        </div>
        <div className="quiz-question-container">
          <div className="quiz-question-content">
            <div className="question-label-container">
              <div className="question-number">2</div>
              <span className="question-label">Quiz Question:</span>
            </div>
            <div>
              {questions.map((q, qIndex) => (
                <div key={qIndex} className="question-area-frame">
                  <div className="question-area">
                    <span className="question-label">
                      Question {qIndex + 1}
                    </span>
                    <input
                      className="ques-input"
                      placeholder="Your Question Here ..."
                      value={q.question}
                      onChange={(e) =>
                        handleQuestionChange(qIndex, "question", e.target.value)
                      }
                    />
                    <i
                      className="fa-solid fa-xmark question-remove-icon"
                      onClick={() => handleRemoveQuestion(qIndex)}
                    ></i>
                  </div>
                  <div className="choice-container">
                    <div className="choice-label">Choices</div>
                    <div className="choice-input-wrapper">
                      {q.options.map((option, oIndex) => (
                        <div key={oIndex} className="choice-input">
                          <span>{String.fromCharCode(65 + oIndex)}:</span>
                          <input
                            className="choice-input-field"
                            placeholder={`Choice ${oIndex + 1}`}
                            value={option}
                            onChange={(e) =>
                              handleOptionChange(qIndex, oIndex, e.target.value)
                            }
                          />
                          <button
                            className="delete-choice-btn"
                            onClick={() => handleRemoveOption(qIndex, oIndex)}
                          >
                            <i className="fa-solid fa-xmark choice-remove-icon"></i>
                          </button>
                          <button
                            className={`correct-choice-btn ${
                              q.correctAnswer === option
                                ? "selected-correct"
                                : q.correctAnswer !== "" &&
                                  q.correctAnswer !== option
                                ? "selected-incorrect"
                                : ""
                            }`}
                            onClick={() =>
                              handleCorrectAnswerClick(qIndex, option)
                            }
                          >
                            <span>
                              {q.correctAnswer === option
                                ? "Correct"
                                : "Incorrect"}
                            </span>
                          </button>
                        </div>
                      ))}
                      <div className="button-add-choice-container">
                        <button
                          className="add-choice-button"
                          onClick={() => handleAddOption(qIndex)}
                        >
                          Add a New Choice
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              ))}
              <div className="button-area-container">
                <button
                  type="button"
                  className="add-question-button"
                  onClick={handleAddQuestion}
                >
                  Add a New Question
                </button>
                <button
                  type="button"
                  className="save-quiz-button"
                  onClick={handleSubmit} // Trigger handleSubmit on button click
                >
                  Save Quiz {/* Save quiz button */}
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddQues;
