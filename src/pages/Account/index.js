import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import style from "./Account.module.scss";
import classNames from "classnames/bind";
import MyAccount from "./MyAccount";
const cx = classNames.bind(style);
function Account() {
  return (
    <div>
      <Header />
      <div className={cx("content")}>
        <div className={cx("breadcrumb-greeting")}>
          <div className={cx("breadcrumb")}>
            <a href="#">Trang chủ</a> / <strong>Tài khoản</strong>
          </div>
          <div className={cx("greeting")}>
            Chào mừng! <span className={cx("username")}>Md Rimel</span>
          </div>
        </div>
        <div className={cx("account-container")}>
          <div className={cx("account-sidebar")}>
            <ul>
              <div className={cx("account")}> Quản lí tài khoản</div>
              <li>
                <div>Hồ sơ của tôi</div>
              </li>
              <li>
                <div>Sổ địa chỉ</div>
              </li>
              <li>
                <div>Tùy chọn thanh toán của tôi</div>
              </li>
              <div className={cx("order")}>Đơn hàng</div>

              <li>
                <div>Đã trả lại</div>
              </li>
              <li>
                <div>Đã hủy</div>
              </li>
            </ul>
          </div>
          <div className={cx("account-content")}>
            <MyAccount />
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default Account;
