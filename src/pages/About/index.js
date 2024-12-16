import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import style from "./About.module.scss";
import classNames from "classnames/bind";
import logo from "../../assets/img/aboutpic/logo.png";
import Tom from "../../assets/img/aboutpic/61e0d3ab-c535-4355-9ee5-90867b49d82a.png";
import Emma from "../../assets/img/aboutpic/emma-watson.jpg";
import will from "../../assets/img/aboutpic/will-smith.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCircleCheck,
  faDollarSign,
  faHeadset,
  faMoneyBill1Wave,
  faShoppingBag,
  faStore,
  faTruckFast,
} from "@fortawesome/free-solid-svg-icons";
import {
  faFacebook,
  faLinkedin,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
const cx = classNames.bind(style);
function About() {
  return (
    <div>
      <Header />
      <div className={cx("our-story-section")}>
        <div className={cx("text-content")}>
          <h2>Our Story</h2>
          <p>
            Launched in 2015, Exclusive is South Asia's premier online shopping
            marketplace with an active presence in Bangladesh. Supported by a
            wide range of tailored marketing, data, and service solutions,
            Exclusive has 10,500 sellers and 300 brands and serves 3 million
            customers across the region.
          </p>
          <p>
            Exclusive has more than 1 Million products to offer, growing at a
            very fast pace. Exclusive offers a diverse assortment in categories
            ranging from consumer goods to specialized items.
          </p>
        </div>
        <div className={cx("image-content")}>
          <img src={logo} />
        </div>
      </div>
      <div className={cx("statistics-section")}>
        <div className={cx("stat-card")}>
          <div className={cx("stat-icon")}>
            <FontAwesomeIcon icon={faStore} />
          </div>
          <h3>10.5k</h3>
          <p>Sellers active on our site</p>
        </div>
        <div className={cx("stat-card")}>
          <div className={cx("stat-icon")}>
            <FontAwesomeIcon icon={faDollarSign} />
          </div>
          <h3>33k</h3>
          <p>Monthly Product Sale</p>
        </div>
        <div className={cx("stat-card")}>
          <div className={cx("stat-icon")}>
            <FontAwesomeIcon icon={faShoppingBag} />
          </div>
          <h3>45.5k</h3>
          <p>Customers active on our site</p>
        </div>
        <div className={cx("stat-card")}>
          <div className={cx("stat-icon")}>
            <FontAwesomeIcon icon={faMoneyBill1Wave} />
          </div>
          <h3>25k</h3>
          <p>Annual gross sale on our site</p>
        </div>
      </div>
      <div className={cx("team-section")}>
        <div className={cx("team-member")}>
          <img src={Tom} alt="Tom Cruise" />
          <h3>Tom Cruise</h3>
          <p>Founder & Chairman</p>
          <div className={cx("social-icons")}>
            <a href="#">
              <FontAwesomeIcon icon={faTwitter} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faFacebook} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faLinkedin} />
            </a>
          </div>
        </div>
        <div className={cx("team-member")}>
          <img src={Emma} alt="Emma Watson" />
          <h3>Emma Watson</h3>
          <p>Managing Director</p>
          <div className={cx("social-icons")}>
            <a href="#">
              <FontAwesomeIcon icon={faTwitter} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faFacebook} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faLinkedin} />
            </a>
          </div>
        </div>
        <div className={cx("team-member")}>
          <img src={will} alt="Will Smith" />
          <h3>Will Smith</h3>
          <p>Product Designer</p>
          <div className={cx("social-icons")}>
            <a href="#">
              <FontAwesomeIcon icon={faTwitter} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faFacebook} />
            </a>
            <a href="#">
              <FontAwesomeIcon icon={faLinkedin} />
            </a>
          </div>
        </div>
      </div>
      <div className={cx("benefits-section")}>
        <div className={cx("benefit-card")}>
          <div className={cx("benefit-icon")}>
            <FontAwesomeIcon icon={faTruckFast} />
          </div>
          <h3>FREE AND FAST DELIVERY</h3>
          <p>Free delivery for all orders over $140</p>
        </div>
        <div className={cx("benefit-card")}>
          <div className={cx("benefit-icon")}>
            <FontAwesomeIcon icon={faHeadset} />
          </div>
          <h3>24/7 CUSTOMER SERVICE</h3>
          <p>Friendly 24/7 customer support</p>
        </div>
        <div className={cx("benefit-card")}>
          <div className={cx("benefit-icon")}>
            <FontAwesomeIcon icon={faCircleCheck} />
          </div>
          <h3>MONEY BACK GUARANTEE</h3>
          <p>We return money within 30 days</p>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default About;
