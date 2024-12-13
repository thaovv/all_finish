import React, { useState } from "react";
import "../Login/Login.css";
import { toast } from "react-toastify";
import { loginApi } from "../../Services/UseService"; // Import the updated loginApi
import { useAuth } from "../Context/AuthContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { setIsLoggedIn } = useAuth();
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!username || !password) {
      toast.error("Username and password are required!");
      return;
    }

    try {
      const res = await loginApi({ username, password });
      console.log("Login Response:", res);

      if (res?.result?.token) {
        localStorage.setItem("token", res.result.token); // Store the token
        setIsLoggedIn(true); // Update auth context state
        toast.success("Login successful!");
        navigate("/home"); // Navigate to the home page
      } else {
        toast.error("Unexpected response from server.");
      }
    } catch (error) {
      toast.error(error || "Login failed!");
    }
  };

  return (
    <div className="login">
      <div className="wrapper">
        <div className="title">Login</div>
        <form>
          <div className="field">
            <input
              type="text"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
              placeholder="Enter username"
            />
            <label>Username</label>
          </div>
          <div className="field">
            <input
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              placeholder="Enter password"
            />
            <label>Password</label>
          </div>
          <div className="field">
            <button type="button" onClick={handleLogin}>
              Login
            </button>
          </div>
          <div className="signup-link">
            Not a member? <a href="/Register">Register</a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
