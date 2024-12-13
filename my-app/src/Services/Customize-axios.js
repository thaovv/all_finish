import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8081/api", // Set the base URL for your API
});

// Request interceptor to add Authorization header
instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token"); // Retrieve the token from localStorage
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`; // Add token to Authorization header
    }
    return config;
  },
  (error) => {
    // Handle request errors
    console.error("Request Error:", error);
    return Promise.reject(error);
  }
);

// Response interceptor to handle API responses and errors
instance.interceptors.response.use(
  (response) => {
    // Simply return the response data if successful
    return response;
  },
  (error) => {
    if (error.response) {
      // Log and reject errors returned from the server
      console.error("API Error Response:", error.response);
      return Promise.reject(error.response.data); // Pass error response data forward
    } else if (error.request) {
      // Log errors where no response is received
      console.error("No Response Received:", error.request);
      return Promise.reject({
        message: "No response received from the server.",
      });
    } else {
      // Log unexpected errors
      console.error("Unexpected Error:", error.message);
      return Promise.reject({ message: "An unexpected error occurred." });
    }
  }
);

export default instance;
