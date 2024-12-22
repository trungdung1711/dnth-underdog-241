import React from 'react';
import "./css/Card.css";
import { Link } from 'react-router-dom';

function Card({ image, title, price }) {
   return (
      <div className="card m-sm-2 position-relative">
         <img src={image} className="card-img-top" alt={title} />
         <div className="card-body">
            <h5 className="card-title">{title}</h5>
            <p className="card-text">{price}</p>
         </div>
         <div className="hover-buttons">
            <Link to="/productdetail" className="btn btn-danger mb-2">Add To Cart</Link>
            <Link to="/productdetail" className="btn btn-secondary">View Product</Link>
         </div>
      </div>
   );
}

export default Card;