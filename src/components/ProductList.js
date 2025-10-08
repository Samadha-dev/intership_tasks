import React from "react";

const ProductList = ({ products }) => {
  if (!products || products.length === 0) return <p>No products found.</p>;

  return (
    <table border="1" style={{ width: "80%", margin: "20px auto" }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Category</th>
          <th>Brand</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => (
          <tr key={product.id}>
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>{product.category}</td>
            <td>{product.brand}</td>
            <td>{product.price}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ProductList;
