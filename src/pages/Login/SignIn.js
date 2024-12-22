import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import React, { useState } from "react";
import { useLocation } from "react-router-dom";

function SignIn() {
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const location = useLocation();
  const { phoneNumber1, password1 } = location.state || {};
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    setLoading(true);
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/v1/auth/login", {
        phoneNumber,
        password
      });

      if (response.status === 200) {
        sessionStorage.setItem("authToken", response.data.authToken);  
        sessionStorage.setItem("userPhoneNumber", phoneNumber);  
        navigate("/home"); // Chuyển hướng đến trang chủ
      } else {
        setError("Thông tin đăng nhập không chính xác!");
      }

    } catch (error) {
      console.error("Error:", error);
      setError("Có lỗi khi đăng nhập. Vui lòng thử lại.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div className="container">
        <div className="row">
          <div className="bg-white d-flex justify-content-between"> {/* Sử dụng Flexbox */}
            <Link className="text-start p-3 text-decoration-none" to="/">
              <h1><img src="/logo.png" alt="avatar" style={{width:"5rem", height:"5rem"}}/><strong>Exclusive</strong></h1>
            </Link>
            <div className="text-end text-white p-3 justify-content-between">
              <h1><strong>SignIn</strong></h1>
            </div>
          </div>
        </div>
      </div>

      <div className="row justify-content-center bg-secondary">
        <div className="col-5 hold-transition login-page p-5">
          <div className="login-box">
            <div className="card">
              <div className="card-body login-card-body">
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
                      placeholder="Phone Number"
                      value={phoneNumber}
                      onChange={(e) => setPhoneNumber(e.target.value)} 
                      required
                    />
                  </div>
                  <div className="input-group mb-3">
                    <input
                      type="password"
                      className="form-control"
                      placeholder="Password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)} 
                      required
                    />
                  </div>
                  <div className="col-12">
                    <div className="icheck-primary">
                      <input type="checkbox" id="remember" style={{ width: "1rem", height: "1rem" }} />
                      <label htmlFor="remember" className="ms-2">Save Login</label>
                    </div>
                    <div className="text-center p-2">
                      <button
                        type="submit"
                        className="btn btn-primary btn-block"
                      >
                        {loading ? "Handling..." : "Confirm"}
                      </button>
                    </div>
                  </div>
                </form>

                <div className="social-auth-links text-center mb-3">
                  <p className="mt-3">- Hoặc -</p>
                  <a href="#" className="btn btn-block btn-primary me-3">
                    <i className="fab fa-facebook mr-2"></i>Facebook
                  </a>
                  <a href="#" className="btn btn-block btn-danger">
                    <i className="fab fa-google-plus mr-2"></i>Google+
                  </a>
                </div>

                <p className="mb-1 mt-4">
                  <a href="forgot-password.html">forgot password ?</a>
                </p>
                <p className="mb-0">
                  <Link className="text-center" to="/register">Don't have an account?</Link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default SignIn;
