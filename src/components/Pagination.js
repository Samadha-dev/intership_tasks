import React from "react";

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const pages = [];
  for (let i = 0; i < totalPages; i++) {
    pages.push(i);
  }

  return (
    <div style={{ textAlign: "center", margin: "20px" }}>
      {pages.map((page) => (
        <button
          key={page}
          onClick={() => onPageChange(page)}
          style={{
            margin: "0 5px",
            padding: "5px 10px",
            backgroundColor: currentPage === page ? "#4caf50" : "#eee",
            color: currentPage === page ? "white" : "black",
          }}
        >
          {page + 1}
        </button>
      ))}
    </div>
  );
};

export default Pagination;
