import React from "react";

const SearchBar = ({ searchTerm, onSearch }) => {
  return (
    <div style={{ textAlign: "center", margin: "20px" }}>
      <input
        type="text"
        placeholder="Search by name or brand..."
        value={searchTerm}
        onChange={(e) => onSearch(e.target.value)}
        style={{ padding: "8px", width: "300px" }}
      />
    </div>
  );
};

export default SearchBar;
