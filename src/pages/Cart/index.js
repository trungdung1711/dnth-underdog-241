import React, { useState, useEffect } from "react";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import styles from "./Cart.module.scss";

function Cart() {
  const [cartItems, setCartItems] = useState([]);

  // Lấy dữ liệu từ localStorage khi trang Cart được load
  useEffect(() => {
    const storedCart = JSON.parse(localStorage.getItem("cart")) || [];
    setCartItems(storedCart);
  }, []);

  return (
    <div>
      {/* Header */}
      <Header />

      {/* Nội dung chính */}
      <div className={styles["cart-container"]}>
        <h1>Giỏ hàng</h1>
        {cartItems.length === 0 ? (
          <p className={styles["empty-cart"]}>Giỏ hàng trống.</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Tổng cộng</th>
              </tr>
            </thead>
            <tbody>
              {cartItems.map((item, index) => (
                <tr key={index}>
                  <td>
                    <img src={item.image} alt={item.name} />
                    {item.name}
                  </td>
                  <td>{item.price.toLocaleString()} VNĐ</td>
                  <td>{item.quantity}</td>
                  <td>{(item.price * item.quantity).toLocaleString()} VNĐ</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      {/* Footer */}
      <Footer />
    </div>
  );
}

export default Cart;
