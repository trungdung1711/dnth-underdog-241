import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import styles from "./css/SiginUp.module.css";

function SignUp() {
  const navigate = useNavigate();
  const [firstName, setFirstname] = useState("");
  const [lastName, setLastname] = useState("");
  const [phoneNumber, setPhonenumber] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    setLoading(true);
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/sign-up", {
        firstName,
        lastName,
        phoneNumber,
        password
      });

      if (response.status === 201) {
        // Thành công
        navigate("/login", { state: { phoneNumber, password } });
      } else {
        setError("Registration failed. Please try again.");
      }

    } catch (error) {
      console.error("Error:", error);
      setError("There was an error registering. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div className={styles.container}>
        <div className="row">
          <div className="bg-white d-flex justify-content-between">
            <Link className="text-start p-3 text-decoration-none" to="/">
              <h1>
                <img src="/logo.png" alt="avatar" style={{ width: "5rem", height: "5rem" }} />
                <strong>Exclusive</strong>
              </h1>
            </Link>
            <div className="text-end text-white p-3 justify-content-between">
              <h1><strong>SignUp</strong></h1>
            </div>
          </div>
        </div>
      </div>

      <div>
        <div className="row align-items-center justify-content-center bg-secondary">
          <div className="col-5 hold-transition register-page p-5">
            <div className="card">
              <div className="card-body register-card-body">
                <h1 className="login-box-msg">Input Information</h1>
                {error && <p className="text-danger text-sm-left">{error}</p>}
                <form
                  onSubmit={(e) => {
                    e.preventDefault(); // Ngừng hành động mặc định của form
                    handleLogin(); // Gọi hàm đăng nhập khi submit form
                  }}
                >
                   
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Firstname"
                      name="firstname"
                      value={firstName}
                      onChange={(e) => setFirstname(e.target.value)}
                      required
                    />
                  </div>

                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Lastname"
                      name="lastname"
                      value={lastName}
                      onChange={(e) => setLastname(e.target.value)}
                      required
                    />
                  </div>

                  <div className="input-group mb-3">
                    <input
                      type="tel"
                      className="form-control"
                      placeholder="Phonenumber"
                      name="phonenumber"
                      value={phoneNumber}
                      onChange={(e) => setPhonenumber(e.target.value)}
                      required
                    />
                  </div>

                  <div className="input-group mb-3">
                    <input
                      type="password"
                      className="form-control"
                      placeholder="Password"
                      name="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </div>

                  <div className="text-center p-2">
                    <button
                      type="submit"
                      className="btn btn-primary btn-block"
                    >
                      {loading ? "Handling..." : "Confirm"}
                    </button>
                  </div>
                  </form>
                {/* </form> */}
                <Link className="text-center" to="/login">
                  You have a account.
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default SignUp;
