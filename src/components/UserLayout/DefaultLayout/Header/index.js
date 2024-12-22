import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCaretDown,
  faCartShopping,
  faHeart,
  faMagnifyingGlass,
  faUserCircle,
} from "@fortawesome/free-solid-svg-icons";
import style from "./Header.module.scss";
import classNames from "classnames/bind";
import { Link, useNavigate} from "react-router-dom";

const cx = classNames.bind(style);

function Header() {
  const userName = sessionStorage.getItem("userPhoneNumber");

  const handleLogout = () => {
    // Xóa thông tin userName khỏi sessionStorage
    sessionStorage.removeItem("userPhoneNumber");
    window.location.reload(); // Tải lại trang để cập nhật trạng thái đăng xuất
  };

  const navigate = useNavigate();


  const handleInfor = () => {
    navigate("/upate");
  };

  return (
    <div className={cx("wrapper")}>
      <div className={cx("top-banner")}>
        <div className={cx("select-lang")}>
          <span>English</span>
          <button>
            <FontAwesomeIcon icon={faCaretDown} />
          </button>
        </div>
        <div className={cx("ad")}>
          <span>
            Summer Sale For All Swim Suits And Free Express Delivery - OFF 50%!
          </span>
          <a href="/about">ShopNow</a>
        </div>
        <div className={cx("log")}>
          {userName ? (
            <div className={cx("dropdown")}>
              <FontAwesomeIcon icon={faUserCircle} className="me-2"/>
              <span data-bs-toggle="dropdown" aria-expanded="false" style={{ cursor: 'pointer' }}>
                {userName}
              </span>
              
              <FontAwesomeIcon icon={faCaretDown} className="ms-2"/>
              <ul className={cx("dropdown-menu")}>
                <li>
                  <span className="dropdown-item" onClick={handleInfor} style={{ cursor: 'pointer' }}>Update Information</span>
                </li>
                <li>
                  <span className="dropdown-item" onClick={handleLogout} style={{ cursor: 'pointer' }}>Logout</span>
                </li>
              </ul>
            </div>




          ) : (
            <>
              <Link to="/register">SignUp</Link>/<Link to="/login">SignIn</Link>
            </>
          )}
        </div>
      </div>
      
      <div className={cx("navbar")}>
        <div className={cx("logo")} >
          <Link to="/" style={{ color: 'inherit', textDecoration: 'none' }}>
            <strong>Exclusive</strong>
          </Link>
        </div>
        <nav className={cx("nav-links")}>
          <Link to="/">HOME</Link>
          <Link to="/product">PRODUCT</Link>
          <Link to="/contact">CONTACT</Link>
          <Link to="/about">ABOUT</Link>
        </nav>
        <div className={cx("search-bar")}>
          <input type="text" placeholder="Which product do you want to find?" />
          <button type="submit">
            <FontAwesomeIcon icon={faMagnifyingGlass} />
          </button>
        </div>
        <div className={cx("icon-links")}>
          <a href="#">
            <FontAwesomeIcon icon={faHeart} />
          </a>
          <a href="#">
            <FontAwesomeIcon icon={faCartShopping} />
          </a>
        </div>
      </div>
    </div>
  );
}

export default Header;
