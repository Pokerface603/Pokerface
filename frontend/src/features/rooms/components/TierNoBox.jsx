import React from "react";
import TierNoBoxSvg from "./constants/TierNoBoxSvg.js";

const TierBox = ({ tier, width, height }) => {
  return (
    <img
      className="mt-2"
      src={TierNoBoxSvg[tier]}
      alt={tier}
      width={width}
      height={height}
    />
  );
};

export default TierBox;
