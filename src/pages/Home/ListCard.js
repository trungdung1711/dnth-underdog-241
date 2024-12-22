import Card from "./Card";
import { Link, useLocation } from "react-router-dom";
import shirtImage from '../../assets/img/shirt.png';

function ListCard() {
   return (
      <div class="container">
         <div className="row mt-4">
            <div className="col-1 bg-danger rounded-1 ps-1 ms-lg-2" style={{ width: "1em", height: "2em" }}></div>
            <div className="col-11 text-danger align-content-center"><h4>Sản phẩm</h4></div>
         </div>
         <h3>Khám phá sản phẩm</h3>
         <div class="row row-cols-4 g-0">
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
            <div class="col"><Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" /></div>
         </div>
         <div className="text-center mt-4 mb-4">
            <Link to="/product" className="btn btn-danger p-lg-2 ps-lg-5 pe-lg-5">
               Xem tất cả sản phẩm
            </Link>
         </div>
      </div>
   );
}

export default ListCard;