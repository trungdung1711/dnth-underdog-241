import React, { useState } from "react";
import Card from "../Home/Card";
import shirtImage from '../../assets/img/shirt.png';
import Footer from "../../components/UserLayout/DefaultLayout/Footer";
import Header from "../../components/UserLayout/DefaultLayout/Header";
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const products = [
  { id: 1, name: "Smartphone", category: "Nam" },
  { id: 2, name: "T-Shirt", category: "Nam" },
  { id: 3, name: "Book", category: "Nữ" },
  { id: 4, name: "Laptop", category: "Nữ" },
  { id: 5, name: "Shoes", category: "Giày&Dép" },
  { id: 6, name: "Shoes", category: "Giày&Dép" },
  { id: 7, name: "Shoes", category: "Ba lô & Túi Sách" },
  { id: 8, name: "Shoes", category: "Ba lô & Túi Sách" },
  { id: 9, name: "Shoes", category: "Trang sức & Phụ kiện" },
  { id: 10, name: "Shoes", category: "Trang sức & Phụ kiện" },
  { id: 11, name: "Shoes", category: "Nước Hoa" },
  { id: 12, name: "Shoes", category: "Nước Hoa" },
];

const categories = ["Nam", "Nữ", "Giày&Dép", "Ba lô & Túi Sách", "Trang sức & Phụ kiện", "Nước Hoa"];

const Product = () => {
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;

  // Hàm xử lý chọn/deselect checkbox
  const handleCategoryChange = (category) => {
    setSelectedCategories((prevSelected) =>
      prevSelected.includes(category)
        ? prevSelected.filter((c) => c !== category) // Bỏ nếu đã chọn
        : [...prevSelected, category] // Thêm nếu chưa chọn
    );
  };

  // Lọc sản phẩm theo danh mục được chọn
  const filteredProducts = selectedCategories.length
    ? products.filter((product) => selectedCategories.includes(product.category))
    : products;

  // Phân trang sản phẩm
  const startIndex = (currentPage - 1) * itemsPerPage;
  const currentProducts = filteredProducts.slice(startIndex, startIndex + itemsPerPage);

  // Quản lý trang hiện tại
  const totalPages = Math.ceil(filteredProducts.length / itemsPerPage);
  

  return (
    <>
      <Header/>
      <div className="container py-5">
      <h2 className="mb-lg-5 text-left">Filter Products</h2>

      <div className="row">
        {/* Sidebar Filters */}
        <div className="col-md-2">
          <div className="mb-4">
            <h4>Categories</h4>
            {categories.map((category) => (
              <div key={category} className="form-check">
                <input
                  type="checkbox"
                  id={category}
                  value={category}
                  className="form-check-input"
                  onChange={() => handleCategoryChange(category)}
                  checked={selectedCategories.includes(category)}
                />
                <label htmlFor={category} className="form-check-label">
                  {category.charAt(0).toUpperCase() + category.slice(1)}
                </label>
              </div>
            ))}
          </div>
        </div>

        {/* Product List */}
        <div className="col-md-10">
          <div className="row">
            {currentProducts.map((product) => (
              <div key={product.id} className="col-lg-6 col-md-12 mb-4 w-25">
                <Card image={shirtImage} title="Áo ấm fa" price="99.000vnđ" name={"Áo ấm fa"} />
              </div>
            ))}
            {!currentProducts.length && (
              <div className="text-center w-100">
                <p>No products found!</p>
              </div>
            )}
          </div>

          {/* Pagination */}
          <nav aria-label="Page navigation example">
            <ul className="pagination justify-content-center">
              <li className="page-item">
                <button
                  className="page-link"
                  disabled={currentPage === 1}
                  onClick={() => setCurrentPage(prevPage => prevPage - 1)}
                >
                  &laquo;
                </button>
              </li>
              {Array.from({ length: totalPages }, (_, index) => (
                <li key={index} className="page-item">
                  <button
                    className={`page-link ${currentPage === index + 1 ? 'active' : ''}`}
                    onClick={() => setCurrentPage(index + 1)}
                  >
                    {index + 1}
                  </button>
                </li>
              ))}
              <li className="page-item">
                <button
                  className="page-link"
                  disabled={currentPage === totalPages}
                  onClick={() => setCurrentPage(prevPage => prevPage + 1)}
                >
                  &raquo;
                </button>
              </li>
            </ul>
          </nav>
        </div>
      </div>
      </div>
      <Footer/>
    </>
  );
};

export default Product;
