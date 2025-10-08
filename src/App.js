import React, { useState, useEffect } from "react";
import axios from "axios";
import ProductList from "./components/ProductList";
import Pagination from "./components/Pagination";
import SearchBar from "./components/SearchBar";

const App = () => {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const fetchProducts = async (search = "", page = 0) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/products?search=${search}&page=${page}&size=10`
      );
      setProducts(res.data.products);
      setTotalPages(res.data.totalPages);
      setCurrentPage(res.data.currentPage);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchProducts(searchTerm, currentPage);
  }, [searchTerm, currentPage]);

  return (
    <div>
      <h1 style={{ textAlign: "center" }}>Product List</h1>
      <SearchBar searchTerm={searchTerm} onSearch={setSearchTerm} />
      <ProductList products={products} />
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={setCurrentPage}
      />
    </div>
  );
};

export default App;
