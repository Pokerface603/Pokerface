import React from "react";

const Parchment = ({ style, children }) => {
  return (
    <div className="bg-parchment-box bg-cover" style={{ ...style }}>
      {children}
    </div>
  );
};

export default Parchment;
