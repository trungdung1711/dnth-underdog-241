import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCaretDown,
  faCartShopping,
  faHeart,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import style from "./Header.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(style);
function Header() {
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
          <a href="/">Đăng Ký</a>/<a href="/">Đăng nhập</a>
        </div>
      </div>
      <div className={cx("navbar")}>
        <div className={cx("logo")}>
          <strong>Exclusive</strong>
        </div>
        <nav className={cx("nav-links")}>
          <Link to={"/"}>Trang chủ</Link>
          <Link to={"/contact"}>Liên hệ</Link>
          <Link to={"/about"}>Giới thiệu</Link>
        </nav>
        <div className={cx("search-bar")}>
          <input type="text" placeholder="Bạn muốn tìm kiếm sản phẩm nào?" />
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
