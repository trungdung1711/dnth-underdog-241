import Img from "../../assets/img/productpic/7025a90f-3dcd-49c5-8082-9a0f364e9de2.png";
import MImg from "../../assets/img/productpic/dd2a33cd-cfed-4dd7-b315-a0fbd3e8c7e9.jpg";
import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import { Link } from "react-router-dom";
import style from "./ProductDetails.module.scss";
import classNames from "classnames/bind";
const cx = classNames.bind(style);
function ProductDetails() {
  return (
    <div>
      <Header />
      <div className={cx("content")}>
        <div className={cx("breadcrumb")}>
          <Link to="/">Trang chủ</Link> / <a href="#">Bộ sưu tập</a> / Classic
          Zipper Hoodie Boxy 2.0
        </div>

        <div className={cx("product-page")}>
          <div className={cx("product-images")}>
            <img src={Img} alt="Hoodie Gray Thumbnail" />
            <img src={Img} alt="Hoodie Gray Thumbnail" />
            <img src={Img} alt="Hoodie Gray Thumbnail" />
            <img src={Img} alt="Hoodie Gray Thumbnail" />
          </div>

          <div className={cx("product-main-image")}>
            <img
              src={MImg}
              alt="Classic
        Zipper Hoodie Boxy 2.0"
            />
          </div>

          <div className={cx("product-details")}>
            <h1>Classic Zipper Hoodie Boxy 2.0</h1>
            <div className={cx("rating")}>⭐️⭐️⭐️⭐️☆ (150 Reviews)</div>
            <div className={cx("price")}>620.000 đ</div>

            <div className={cx("color")}>
              <span>Màu sắc:</span>
              <button className={cx("color-option", { black: true })}></button>
              <button
                className={cx("color-option", { beige: true, selected: true })}
              ></button>
            </div>

            <div className={cx("size")}>
              <span>Size:</span>
              {["XS", "S", "M", "L", "XL"].map((size) => (
                <button
                  key={size}
                  className={cx("size-option", { selected: size === "M" })}
                >
                  {size}
                </button>
              ))}
            </div>

            <div className={cx("quantity")}>
              <span>Số lượng:</span>
              <button className={cx("quantity-btn")}>-</button>
              <input type="number" value="2" min="1" />
              <button className={cx("quantity-btn")}>+</button>
            </div>

            <div className={cx("actions")}>
              <button className={cx("buy-now")}>Mua ngay</button>
              <button className={cx("add-to-cart")}>Thêm vào giỏ hàng</button>
            </div>

            <div className={cx("policies")}>
              <div className={cx("policy")}>
                <span>🚚 Chính sách vận chuyển</span>
                <p>Miễn phí vận chuyển cho hóa đơn từ 300.000đ</p>
              </div>
              <div className={cx("policy")}>
                <span>🔄 Chính sách đổi trả</span>
                <p>Miễn phí đổi trả trong vòng 7 ngày</p>
              </div>
            </div>
          </div>
        </div>
        <div className={cx("product-info")}>
          <h2>Thông tin sản phẩm</h2>
          <p>
            FORM BOXY 2.0 VẪN GIỮ ĐƯỢC SỰ RỘNG RÃI, THOẢI MÁI, NHƯNG VỚI ĐỘ DÀI
            ÁO ĐÃ ĐƯỢC CẮT TỈA - NGẮN HƠN THẮT LƯNG, GIÚP DÁNG NGƯỜI TRÔNG THON
            GỌN VÀ CAO HƠN. DỄ DÀNG KẾT HỢP VỚI CÁC LOẠI TRANG PHỤC KHÁC NHAU.
            CÓ PHẦN TÚI ẨN NHỎ PHÍA TRONG, PHÙ HỢP ĐỂ MANG THEO NHỮNG VẬT DỤNG
            CÁ NHÂN NHỎ - GỌN. HOODIE ĐƯỢC TRANG BỊ KHÓA KÉO DÀI, TẠO SỰ TIỆN
            LỢI CÙNG NHƯ ĐẢM BẢO TÍNH THỜI TRANG. HAI TÚI Ở PHÍA TRƯỚC ĐỂ ĐỰNG
            CÁC ĐỒ VẬT CẦN THIẾT HOẶC GIỮ ẤM TAY KHI TRỜI TIẾT SE LẠNH.
          </p>
        </div>
        <div className={cx("best-seller")}>
          <h2>BEST SELLER</h2>
          <div className={cx("product-grid")}>
            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>

            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>
            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>
            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>
            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>
            <div className={cx("product-card")}>
              <img src={MImg} alt="Striped Baseball Jersey" />
              <h3>Striped Baseball Jersey</h3>
              <div className={cx("rating")}>⭐⭐⭐⭐⭐</div>
              <div className={cx("price")}>
                <span className={cx("current-price")}>589.000 VNĐ</span>
                <span className={cx("original-price")}>620.000 VNĐ</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ProductDetails;
