import React from "react";

const CheckIcon = ({ width, height, onClick }) => {
  return (
    <div
      className="bg-check-button bg-cover cursor-pointer brightness-0 hover:brightness-50"
      style={{ width, height }}
      onClick={onClick}
    />
  );
};

export default CheckIcon;
