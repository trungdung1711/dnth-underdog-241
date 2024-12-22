import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import Card from "./Card";
import "./css/ListCard1.css";
import shirtImage from '../../assets/img/shirt.png';

function ListCard1() {
   const [countdown, setCountdown] = useState("");

   useEffect(() => {
      const targetDate = new Date(); 
      targetDate.setHours(23, 59, 59); // Thời gian kết thúc trong ngày (11:59:59 PM)

      const interval = setInterval(() => {
         const now = new Date();
         const timeDiff = targetDate - now;

         if (timeDiff <= 0) {
            clearInterval(interval);
            setCountdown("Hết giờ");
         } else {
            const hours = Math.floor((timeDiff / (1000 * 60 * 60)) % 24);
            const minutes = Math.floor((timeDiff / (1000 * 60)) % 60);
            const seconds = Math.floor((timeDiff / 1000) % 60);
            setCountdown(`${hours}h ${minutes}m ${seconds}s`);
         }
      }, 1000);

      return () => clearInterval(interval);
   }, []);

   const products = [
      { id: 1, name: "Product 1" },
      { id: 2, name: "Product 2" },
      { id: 3, name: "Product 3" },
      { id: 4, name: "Product 4" },
      { id: 5, name: "Product 5" },
      { id: 6, name: "Product 6" },
      { id: 7, name: "Product 7" },
      { id: 8, name: "Product 8" },
   ];

   return (
      <div className="container">
         <div className="row mt-4">
            <div
               className="col-1 bg-danger rounded-1 ps-1 ms-lg-2"
               style={{ width: "1em", height: "2em" }}
            ></div>
            <div className="col-11 text-danger align-content-center">
               <h4>Hôm nay</h4>
            </div>
         </div>
         <div className="d-flex flex-row align-items-start">
            <h1 className="col-4">Khuyến mại</h1>
            <div className="col-9 text-danger fs-1 mb-2" style={{ fontWeight: "bold", fontSize: "1.25rem" }}>
              {countdown}
            </div>
         </div>

         <div id="productCarousel" className="carousel slide" data-bs-ride="carousel">
            <div className="carousel-inner">
               {products.reduce((acc, product, index) => {
                  const groupIndex = Math.floor(index / 4);
                  if (!acc[groupIndex]) acc[groupIndex] = [];
                  acc[groupIndex].push(product);
                  return acc;
               }, []).map((group, groupIndex) => (
                  <div
                     key={groupIndex}
                     className={`carousel-item ${groupIndex === 0 ? "active" : ""}`}
                  >
                     <div className="row">
                        {group.map((product) => (
                           <div key={product.id} className="col-3">
                              <Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" name={product.name} />
                           </div>
                        ))}
                     </div>
                  </div>
               ))}
            </div>
            
            <button
               className="btn btn-danger slider-btn left"
               type="button"
               data-bs-target="#productCarousel"
               data-bs-slide="prev"
            >
               &#8249;
            </button>

            <button
               className="btn btn-danger slider-btn right"
               type="button"
               data-bs-target="#productCarousel"
               data-bs-slide="next"
            >
               &#8250;
            </button>
         </div>
         <div className="text-center mt-4 mb-4">
            <Link to="/product" className="btn btn-danger p-lg-2 ps-lg-5 pe-lg-5">
               Xem tất cả sản phẩm
            </Link>
         </div>
      </div>
   );
}

export default ListCard1;
