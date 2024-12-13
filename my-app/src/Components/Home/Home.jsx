import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Card } from "react-bootstrap";
import "./Home.css";
import studyImage from "../Home/study.jpg";
import { getQuiz } from "../../Services/UseService";

const Home = () => {
  const [quizTitles, setQuizTitles] = useState([]);
  const token = localStorage.getItem("token");
  const decodedToken = JSON.parse(atob(token.split(".")[1]));
  const userId = decodedToken.userId;
  console.log(userId);

  useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const data = await getQuiz(userId); // Truyền userId vào API
        if (data?.data?.length > 0) {
          setQuizTitles(data.data); // Cập nhật danh sách quiz
        }
      } catch (error) {
        console.error("Error fetching quiz:", error);
      }
    };

    fetchQuiz();
  }, [userId]); // Thêm userId làm dependency

  return (
    <main className="container">
      <div className="body">
        <div className="card-container">
          {quizTitles.length > 0 ? (
            quizTitles.map((quiz) => (
              <Card className="card" key={quiz.id}>
                <Card.Img variant="top" src={studyImage} />
                <Card.Body>
                  <Link to={`/Info/${quiz.id}`} className="card-title">
                    <Card.Title>{quiz.title}</Card.Title>
                  </Link>
                </Card.Body>
              </Card>
            ))
          ) : (
            <p>Loading...</p>
          )}
        </div>
      </div>
    </main>
  );
};

export default Home;
