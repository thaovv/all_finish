import axios from "./Customize-axios";

const loginApi = async (loginData) => {
  try {
    const response = await axios.post("/users/login", loginData);
    return response.data; // Return UserDto
  } catch (error) {
    console.error("API Error:", error); // Log full error for debugging
    throw error.response?.data || "Login failed";
  }
};

const registerApi = async (data) => {
  try {
    const response = await axios.post("/users/register", data);
    // Adjusted to check the response format
    if (response.data && response.data.code === 1000) {
      return response.data.result; // Return the user data (including id, username, etc.)
    }
    return response.data.message || "Registration failed";
  } catch (error) {
    console.error("API Error:", error); // Log full error for debugging
    throw error.response?.data || "Register failed";
  }
};

const getQuiz = async (userId) => {
  try {
    const response = await axios.get(`/quiz/get-quizs/${userId}`); // Sử dụng userId trong URL
    return response.data;
  } catch (error) {
    console.error("Error fetching quizzes by user ID:", error);
    throw error.response?.data || "Failed to fetch quizzes";
  }
};
const getQuizbyId = async (id) => {
  try {
    const response = await axios.get(`/quiz/get-quiz/${id}`);
    return response.data; // Return the quiz data
  } catch (error) {
    console.error("Error fetching quiz by ID:", error);
    throw error;
  }
};
const createQuestion = async (quizQuestion) => {
  try {
    const response = await axios.post("/quiz/create-quiz", quizQuestion);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const deleteQuiz = async (id) => {
  try {
    const response = await axios.delete(`/quiz/delete-quiz/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error deleting quiz:", error);
    throw error.response?.data || "Delete failed";
  }
};

export const updateQuiz = async (quizId, quizData) => {
  try {
    const response = await axios.put(
      `/quiz/update-quiz/${quizId}`, // Đường dẫn API
      quizData, // Dữ liệu cần cập nhật
      {
        headers: {
          "Content-Type": "application/json", // Header định dạng JSON
        },
      }
    );
    return response.data; // Trả về kết quả từ backend
  } catch (error) {
    console.error("Error updating quiz:", error);
    throw error; // Ném lỗi để xử lý ở component
  }
};

export {
  loginApi,
  registerApi,
  getQuiz,
  createQuestion,
  getQuizbyId,
  deleteQuiz,
};
