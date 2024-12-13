import React from "react";
import "./Loading.css"; // Optional styling for the loader

const LoadingPage = () => {
  return (
    <div className="loading-container">
      <div className="spinner"></div>
      <span className="loading-text">Loading...</span>
    </div>
  );
};

export default LoadingPage;
