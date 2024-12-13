import React, { useState } from "react";
import "./Register.css";
import { registerApi } from "../../Services/UseService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";

const Register = () => {
  const [fullname, setFullname] = useState(""); // Change name to fullname
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleRegister = async () => {
    if (!fullname || !username || !password) {
      // Ensure fullname is checked
      toast.error("All fields are required");
      return;
    }

    try {
      const res = await registerApi({ fullname, username, password }); // Pass fullname here
      console.log("check>>>: ", res);
      if (res) {
        toast.success("Registration successful!");
        navigate("/Login");
      } else {
        toast.error(res || "Registration failed");
      }
    } catch (error) {
      toast.error("An unexpected error occurred.");
      console.error(error);
    }
  };

  return (
    <div className="reigester">
      <div className="wrapper">
        <div className="title">Register</div>
        <form action="#">
          <div className="field">
            <input
              type="text"
              value={fullname}
              onChange={(event) => setFullname(event.target.value)} // Update fullname here
            />
            <label>Fullname</label> {/* Update label to Fullname */}
          </div>
          <div className="field">
            <input
              type="text"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
            />
            <label>Username</label>
          </div>
          <div className="field">
            <input
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
            <label>Password</label>
          </div>
          <div className="field">
            <button type="button" onClick={() => handleRegister()}>
              Register
            </button>
          </div>
          <div className="signup-link">
            Already have an account? <a href="/Login">Login now</a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
