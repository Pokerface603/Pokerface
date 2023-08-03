import React from "react";

const Parchment = ({ width, height, children, style }) => {
  return (
    <div
      className="bg-parchment-box bg-cover"
      style={{ width, height, ...style }}>
      {children}
    </div>
  );
};

export default Parchment;
