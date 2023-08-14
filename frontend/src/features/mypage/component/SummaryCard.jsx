import React from "react";
import { getSummaryImg } from "./utils/summary";

const SummaryCard = ({ title, count }) => {
  return (
    <div
      className="grid grid-cols-1 justify-center justify-items-center text-center w-1/3"
      style={{ fontFamily: "Unchained-RegularShadow", fontSize: "40px" }}>
      <span>{title}</span>
      <img src={getSummaryImg(title)} alt="summary" />
      <span>{count}</span>
    </div>
  );
};

export default SummaryCard;
