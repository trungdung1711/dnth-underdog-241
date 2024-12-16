import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import style from "./Footer.module.scss";
import classNames from "classnames/bind";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
const cx = classNames.bind(style);
function Footer() {
  return (
    <footer className={cx("footer")}>
      <div className={cx("footer-section")}>
        <h3>Exclusive</h3>
        <p>Subscribe</p>
        <p>Get 10% off your first order</p>
        <div className={cx("subscribe-input")}>
          <input type="email" placeholder="Enter your email" />
          <button type="submit">
            <i className={cx("fas fa-paper-plane")}></i>
          </button>
        </div>
      </div>

      <div className={cx("footer-section")}>
        <h3>Support</h3>
        <p>111 Bijoy sarani, Dhaka, DH 1515, Bangladesh.</p>
        <p>exclusive@gmail.com</p>
        <p>+88015-88888-9999</p>
      </div>

      <div className={cx("footer-section")}>
        <h3>Account</h3>
        <p>
          <a href="#">My Account</a>
        </p>
        <p>
          <a href="#">Login / Register</a>
        </p>
        <p>
          <a href="#">Cart</a>
        </p>
        <p>
          <a href="#">Wishlist</a>
        </p>
        <p>
          <a href="#">Shop</a>
        </p>
      </div>

      <div className={cx("footer-section")}>
        <h3>Quick Link</h3>
        <p>
          <a href="#">Privacy Policy</a>
        </p>
        <p>
          <a href="#">Terms of Use</a>
        </p>
        <p>
          <a href="#">FAQ</a>
        </p>
        <p>
          <a href="#">Contact</a>
        </p>
      </div>

      <div className={cx("footer-bottom")}>
        <p>&copy; Copyright Rimel 2022. All rights reserved</p>
      </div>
    </footer>
  );
}

export default Footer;
