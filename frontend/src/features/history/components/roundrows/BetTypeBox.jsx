import React from "react";
import { getBetBoxColor } from "./betbox/getBetBoxColor";

const BetTypeBox = ({ betType }) => {
  return (
    <div
      className="w-1/3 h-full text-center ml-5 mr-5 rounded-lg text-white antialiased"
      style={{
        backgroundColor: getBetBoxColor(betType),
        fontFamily: "Unchained-Spaghettuitalic",
        fontSize: "28px",
      }}>
      {betType}
    </div>
  );
};

export default BetTypeBox;
