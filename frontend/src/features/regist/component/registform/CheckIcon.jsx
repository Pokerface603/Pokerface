import React from "react";

const CheckIcon = ({ width, height, filter }) => {
  return (
    <div
      className="bg-check-button bg-cover cursor-pointer"
      style={{ width, height, filter: filter }}
    />
  );
};

export default CheckIcon;
