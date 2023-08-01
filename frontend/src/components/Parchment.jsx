import React from "react";

const Parchment = ({ width, height, children }) => {
  return (
    <div className="bg-parchment-box" style={{ width, height }}>
      {children}
    </div>
  );
};

export default Parchment;
