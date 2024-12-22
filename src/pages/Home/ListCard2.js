import Card from "./Card";
import { Link, useLocation } from "react-router-dom";
import "./css/ListCard2.css"
import shirtImage from '../../assets/img/shirt.png';

function ListCard2() {
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
      <div class="container">
         <div className="row mt-4">
            <div className="col-1 bg-danger rounded-1 ps-1 ms-lg-2" style={{ width: "1em", height: "2em" }}></div>
            <div className="col-11 text-danger align-content-center"><h4>Tháng này</h4></div>
         </div>
         <h3>Best Selling</h3>
         <div id="productCarouse2" className="carousel slide" data-bs-ride="carousel">
            <div className="carousel-inner me-3">
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
               data-bs-target="#productCarouse2"
               data-bs-slide="prev"
            >
               &#8249;
            </button>

            {/* Nút điều hướng phải */}
            <button
               className="btn btn-danger slider-btn right"
               type="button"
               data-bs-target="#productCarouse2"
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

export default ListCard2;