import classNames from "classnames/bind";
import style from "./MyAccount.module.scss";
const cx = classNames.bind(style);
function MyAccount() {
  return (
    <div>
      <form className={cx("profile-form")}>
        <div className={cx("edit")}>
          <div className={cx("header")}>Chỉnh sửa hồ sơ</div>
          <div className={cx("_name")}>
            <div className={cx("form-group")}>
              <label for="first-name">Tên</label>
              <input type="text" id="first-name" placeholder="Md" />
            </div>
            <div className={cx("form-group")}>
              <label for="last-name">Họ</label>
              <input type="text" id="last-name" placeholder="Rimel" />
            </div>
          </div>
          <div className={cx("_email")}>
            <div className={cx("form-group")}>
              <label for="email">Email</label>
              <input type="email" id="email" placeholder="rimel111@gmail.com" />
            </div>
            <div className={cx("form-group")}>
              <label for="address">Địa chỉ</label>
              <input
                type="text"
                id="address"
                placeholder="Kingston, 5236, United State"
              />
            </div>
          </div>
        </div>
        <div className={cx("edit-pass")}>
          <div className={cx("change_pass")}>Thay đổi mật khẩu</div>
          <div className={cx("form-group")}>
            <input type="password" placeholder="Mật khẩu hiện tại" />
          </div>
          <div className={cx("form-group")}>
            <input type="password" placeholder="Mật khẩu mới" />
          </div>
          <div className={cx("form-group")}>
            <input type="password" placeholder="Nhập lại mật khẩu mới" />
          </div>
          <div className={cx("form-actions")}>
            <button type="button" className={cx("cancel-btn")}>
              Hủy
            </button>
            <button type="submit" className={cx("save-btn")}>
              Lưu thay đổi
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default MyAccount;
