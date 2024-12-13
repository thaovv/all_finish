import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getQuizbyId, updateQuiz } from "../../../Services/UseService";
import "./AddQues.css"; // Sử dụng chung CSS với AddQues

const ModifyQuiz = () => {
  const { quizId } = useParams();
  const navigate = useNavigate();
  const [quizTitle, setQuizTitle] = useState("");
  const [questions, setQuestions] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const response = await getQuizbyId(quizId);
        const quizData = response.data;

        setQuizTitle(quizData.title);
        setQuestions(
          quizData.questions.map((q) => ({
            question: q.content,
            options: q.answers.map((a) => a.content),
            correctAnswer: q.answers.find((a) => a.correct).content,
          }))
        );
      } catch (error) {
        console.error("Error fetching quiz:", error);
      }
    };

    fetchQuiz();
  }, [quizId]);

  const handleQuizTitleChange = (e) => setQuizTitle(e.target.value);

  const handleQuestionChange = (index, key, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index][key] = value;
    setQuestions(updatedQuestions);
  };

  const handleOptionChange = (qIndex, oIndex, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options[oIndex] = value;
    setQuestions(updatedQuestions);
  };

  const handleRemoveOption = (qIndex, oIndex) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options.splice(oIndex, 1);
    setQuestions(updatedQuestions);
  };

  const handleCorrectAnswerClick = (qIndex, option) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].correctAnswer = option;
    setQuestions(updatedQuestions);
  };

  const handleAddOption = (qIndex) => {
    const updatedQuestions = [...questions];
    updatedQuestions[qIndex].options.push("");
    setQuestions(updatedQuestions);
  };

  const handleAddQuestion = () => {
    setQuestions([
      ...questions,
      { question: "", options: ["", "", "", ""], correctAnswer: "" },
    ]);
  };

  const handleRemoveQuestion = (index) => {
    const updatedQuestions = questions.filter((_, i) => i !== index);
    setQuestions(updatedQuestions);
  };

  const handleSubmit = async () => {
    if (!quizTitle.trim()) {
      alert("Quiz title cannot be empty!");
      return;
    }
  
    if (
      questions.some(
        (q) =>
          !q.question.trim() ||
          q.options.some((opt) => !opt.trim()) ||
          !q.correctAnswer.trim()
      )
    ) {
      alert("Please fill in all the fields and select a correct answer for each question!");
      return;
    }
  
    const updatedQuiz = {
      title: quizTitle,
      questions: questions.map((q) => ({
        content: q.question,
        answers: q.options.map((option) => ({
          content: option,
          correct: option === q.correctAnswer,
        })),
        score: 2, // Bạn có thể thay đổi hoặc xóa nếu không cần
      })),
    };
  
    setIsLoading(true); // Bật trạng thái loading
    try {
      await updateQuiz(quizId, updatedQuiz); // Gửi yêu cầu API
      navigate("/"); // Điều hướng về Home sau khi lưu thành công
    } catch (error) {
      console.error("Error updating quiz:", error);
      alert("Failed to update quiz. Please try again.");
    } finally {
      setIsLoading(false); // Tắt trạng thái loading
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
            Modify <span className="new-quiz-title-highlight">Quiz</span>
          </span>
        </div>
      </div>
      <div>
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
                  onClick={handleSubmit}
                >
                  Save Quiz
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModifyQuiz;
